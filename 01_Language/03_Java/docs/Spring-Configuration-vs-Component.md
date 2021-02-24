# Spring @Configuration 和 @Component 区别

来源：https://blog.csdn.net/isea533/article/details/78072133

> 一句话概括就是 `@Configuration` 中所有带 `@Bean` 注解的方法都会被动态代理，因此调用该方法返回的都是同一个实例。

下面看看实现的细节。

## `@Configuration` 注解：

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Configuration {
    String value() default "";
}
```

从定义来看， `@Configuration` 注解本质上还是 `@Component`，因此 `<context:component-scan/>` 或者 `@ComponentScan` 都能处理`@Configuration` 注解的类。

`@Configuration` 标记的类必须符合下面的要求：

- 配置类必须以类的形式提供（不能是工厂方法返回的实例），允许通过生成子类在运行时增强（cglib 动态代理）。
- 配置类不能是 `final` 类（没法动态代理）。
- 配置注解通常为了通过 `@Bean` 注解生成 Spring 容器管理的类，
- 配置类必须是非本地的（即不能在方法中声明，不能是 private）。
- 任何嵌套配置类都必须声明为`static`。
- `@Bean` 方法可能不会反过来创建进一步的配置类（也就是返回的 bean 如果带有 `@Configuration`，也不会被特殊处理，只会作为普通的 bean）。

## 加载过程

Spring 容器在启动时，会加载默认的一些 `PostPRocessor`，其中就有 `ConfigurationClassPostProcessor`，这个后置处理程序专门处理带有 `@Configuration` 注解的类，这个程序会在 `bean` 定义加载完成后，在 `bean` 初始化前进行处理。主要处理的过程就是使用 `cglib` 动态代理增强类，而且是对其中带有 `@Bean` 注解的方法进行处理。

在 `ConfigurationClassPostProcessor` 中的 `postProcessBeanFactory` 方法中调用了下面的方法：

```java
/**
 * Post-processes a BeanFactory in search of Configuration class BeanDefinitions;
 * any candidates are then enhanced by a {@link ConfigurationClassEnhancer}.
 * Candidate status is determined by BeanDefinition attribute metadata.
 * @see ConfigurationClassEnhancer
 */
public void enhanceConfigurationClasses(ConfigurableListableBeanFactory beanFactory) {
    Map<String, AbstractBeanDefinition> configBeanDefs = new LinkedHashMap<String, AbstractBeanDefinition>();
    for (String beanName : beanFactory.getBeanDefinitionNames()) {
        BeanDefinition beanDef = beanFactory.getBeanDefinition(beanName);
        if (ConfigurationClassUtils.isFullConfigurationClass(beanDef)) {
            //省略部分代码
            configBeanDefs.put(beanName, (AbstractBeanDefinition) beanDef);
        }
    }
    if (configBeanDefs.isEmpty()) {
        // nothing to enhance -> return immediately
        return;
    }
    ConfigurationClassEnhancer enhancer = new ConfigurationClassEnhancer();
    for (Map.Entry<String, AbstractBeanDefinition> entry : configBeanDefs.entrySet()) {
        AbstractBeanDefinition beanDef = entry.getValue();
        // If a @Configuration class gets proxied, always proxy the target class
        beanDef.setAttribute(AutoProxyUtils.PRESERVE_TARGET_CLASS_ATTRIBUTE, Boolean.TRUE);
        try {
            // Set enhanced subclass of the user-specified bean class
            Class<?> configClass = beanDef.resolveBeanClass(this.beanClassLoader);
            Class<?> enhancedClass = enhancer.enhance(configClass, this.beanClassLoader);
            if (configClass != enhancedClass) {
                //省略部分代码
                beanDef.setBeanClass(enhancedClass);
            }
        }
        catch (Throwable ex) {
            throw new IllegalStateException(
              "Cannot load configuration class: " + beanDef.getBeanClassName(), ex);
        }
    }
}
```

在方法的第一次循环中，查找到所有带有 `@Configuration` 注解的 bean 定义，然后在第二个 for 循环中，通过下面的方法对类进行增强：

```java
Class<?> enhancedClass = enhancer.enhance(configClass, this.beanClassLoader);
```

然后使用增强后的类替换了原有的 `beanClass`：

```java
beanDef.setBeanClass(enhancedClass);
```

所以到此时，所有带有 `@Configuration` 注解的 bean 都已经变成了增强的类。

下面关注上面的 `enhance` 增强方法，多跟一步就能看到下面的方法：

```java
/**
 * Creates a new CGLIB {@link Enhancer} instance.
 */
private Enhancer newEnhancer(Class<?> superclass, ClassLoader classLoader) {
    Enhancer enhancer = new Enhancer();
    enhancer.setSuperclass(superclass);
    enhancer.setInterfaces(new Class<?>[] {EnhancedConfiguration.class});
    enhancer.setUseFactory(false);
    enhancer.setNamingPolicy(SpringNamingPolicy.INSTANCE);
    enhancer.setStrategy(new BeanFactoryAwareGeneratorStrategy(classLoader));
    enhancer.setCallbackFilter(CALLBACK_FILTER);
    enhancer.setCallbackTypes(CALLBACK_FILTER.getCallbackTypes());
    return enhancer;
}
```

通过 cglib 代理的类在调用方法时，会通过 `CallbackFilter` 调用，这里的 `CALLBACK_FILTER` 如下：

```java
// The callbacks to use. Note that these callbacks must be stateless.
private static final Callback[] CALLBACKS = new Callback[] {
        new BeanMethodInterceptor(),
        new BeanFactoryAwareMethodInterceptor(),
        NoOp.INSTANCE
};

private static final ConditionalCallbackFilter CALLBACK_FILTER = 
        new ConditionalCallbackFilter(CALLBACKS);
```

其中 `BeanMethodInterceptor` 匹配方法如下：

```java
@Override
public boolean isMatch(Method candidateMethod) {
    return BeanAnnotationHelper.isBeanAnnotated(candidateMethod);
}

//BeanAnnotationHelper
public static boolean isBeanAnnotated(Method method) {
    return AnnotatedElementUtils.hasAnnotation(method, Bean.class);
}
```

也就是当方法有 `@Bean` 注解的时候，就会执行这个回调方法。

另一个 `BeanFactoryAwareMethodInterceptor` 匹配的方法如下：

```java
@Override
public boolean isMatch(Method candidateMethod) {
    return (candidateMethod.getName().equals("setBeanFactory") &&
            candidateMethod.getParameterTypes().length == 1 &&
            BeanFactory.class == candidateMethod.getParameterTypes()[0] &&
            BeanFactoryAware.class.isAssignableFrom(candidateMethod.getDeclaringClass()));
}
```

当前类还需要实现 `BeanFactoryAware` 接口，上面的 `isMatch` 就是匹配的这个接口的方法。

## `@Bean` 注解方法执行策略

先给一个简单的示例代码：

```java
@Configuration
public class MyBeanConfig {

    @Bean
    public Country country(){
        return new Country();
    }

    @Bean
    public UserInfo userInfo(){
        return new UserInfo(country());
    }

}
```

> 相信大多数人第一次看到上面 `userInfo()` 中调用 `country()` 时，会认为这里的 `Country` 和上面 `@Bean` 方法返回的 `Country` 可能不是同一个对象，因此可能会通过下面的方式来替代这种方式：
>
> ```
> @Autowired
> private Country country;
> ```
>
> 实际上不需要这么做（后面会给出需要这样做的场景），直接调用 country() 方法返回的是同一个实例。
>
> 下面看调用 `country()` 和 `userInfo()` 方法时的逻辑。

现在我们已经知道 `@Configuration` 注解的类是如何被处理的了，现在关注上面的 `BeanMethodInterceptor`，看看带有 `@Bean` 注解的方法执行的逻辑。下面分解来看 `intercept` 方法。

```java
//首先通过反射从增强的 Configuration 注解类中获取 beanFactory
ConfigurableBeanFactory beanFactory = getBeanFactory(enhancedConfigInstance);

//然后通过方法获取 beanName，默认为方法名，可以通过 @Bean 注解指定
String beanName = BeanAnnotationHelper.determineBeanNameFor(beanMethod);

//确定这个 bean 是否指定了代理的范围
//默认下面 if 条件 false 不会执行
Scope scope = AnnotatedElementUtils.findMergedAnnotation(beanMethod, Scope.class);
if (scope != null && scope.proxyMode() != ScopedProxyMode.NO) {
    String scopedBeanName = ScopedProxyCreator.getTargetBeanName(beanName);
    if (beanFactory.isCurrentlyInCreation(scopedBeanName)) {
        beanName = scopedBeanName;
    }
}

//中间跳过一段 Factorybean 相关代码

//判断当前执行的方法是否为正在执行的 @Bean 方法
//因为存在在 userInfo() 方法中调用 country() 方法
//如果 country() 也有 @Bean 注解，那么这个返回值就是 false.
if (isCurrentlyInvokedFactoryMethod(beanMethod)) {
    // 判断返回值类型，如果是 BeanFactoryPostProcessor 就写警告日志
    if (logger.isWarnEnabled() &&
            BeanFactoryPostProcessor.class.isAssignableFrom(beanMethod.getReturnType())) {
        logger.warn(String.format(
            "@Bean method %s.%s is non-static and returns an object " +
            "assignable to Spring's BeanFactoryPostProcessor interface. This will " +
            "result in a failure to process annotations such as @Autowired, " +
            "@Resource and @PostConstruct within the method's declaring " +
            "@Configuration class. Add the 'static' modifier to this method to avoid " +
            "these container lifecycle issues; see @Bean javadoc for complete details.",
            beanMethod.getDeclaringClass().getSimpleName(), beanMethod.getName()));
    }
    //直接调用原方法创建 bean
    return cglibMethodProxy.invokeSuper(enhancedConfigInstance, beanMethodArgs);
}
//如果不满足上面 if，也就是在 userInfo() 中调用的 country() 方法
return obtainBeanInstanceFromFactory(beanMethod, beanMethodArgs, beanFactory, beanName);
```

> **关于 `isCurrentlyInvokedFactoryMethod` 方法**
>
> 可以参考 `SimpleInstantiationStrategy` 中的 `instantiate` 方法，这里先设置的调用方法：

```java
currentlyInvokedFactoryMethod.set(factoryMethod);
return factoryMethod.invoke(factoryBean, args);
```

> 而通过方法内部直接调用 `country()` 方法时，不走上面的逻辑，直接进的代理方法，也就是当前的 `intercept`方法，因此当前的工厂方法和执行的方法就不相同了。

`obtainBeanInstanceFromFactory` 方法比较简单，就是通过 `beanFactory.getBean` 获取 `Country`，如果已经创建了就会直接返回，如果没有执行过，就会通过 `invokeSuper` 首次执行。

因此我们在 `@Configuration` 注解定义的 bean 方法中可以直接调用方法，不需要 `@Autowired` 注入后使用。

## @Component 注意

`@Component` 注解并没有通过 cglib 来代理`@Bean` 方法的调用，因此像下面这样配置时，就是两个不同的 country。

```java
@Component
public class MyBeanConfig {

    @Bean
    public Country country(){
        return new Country();
    }

    @Bean
    public UserInfo userInfo(){
        return new UserInfo(country());
    }

}
```

有些特殊情况下，我们不希望 `MyBeanConfig` 被代理（代理后会变成`WebMvcConfig$$EnhancerBySpringCGLIB$$8bef3235293`）时，就得用 `@Component`，这种情况下，上面的写法就需要改成下面这样：

```java
@Component
public class MyBeanConfig {

    @Autowired
    private Country country;

    @Bean
    public Country country(){
        return new Country();
    }

    @Bean
    public UserInfo userInfo(){
        return new UserInfo(country);
    }

}
```

这种方式可以保证使用的同一个 `Country` 实例。