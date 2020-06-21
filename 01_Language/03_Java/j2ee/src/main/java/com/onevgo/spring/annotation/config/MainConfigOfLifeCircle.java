package com.onevgo.spring.annotation.config;

import com.onevgo.spring.annotation.bean.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * bean 的生命周期
 * bean 创建 -- 初始化 --- 销毁过程
 * 容器管理bean的生命周期
 * 我们可以自定义初始化和销毁方法，容器在bean进行到当前生命周期的时候来调用我们自定义的初始化和销毁方法
 * 1. bean创建
 * <ul>
 * <li>1. 单实例：在容器启动的时候创建对象</li>
 * <li>2. 多实例：在每次获取的时候创建对象</li>
 * </ul>
 * 2. bean属性赋值
 * 3. 初始化
 * <ul>
 * <li>1. BeanPostProcessor.postProcessBeforeInitialization</li>
 * <li>2. 调用初始化方法</li>
 * <li>3. BeanPostProcessor.postProcessAfterInitialization</li>
 * </ul>
 * 4. 销毁
 * <ul>
 * <li>1. 单实例：容器关闭的时候</li>
 * <li>2. 多实例：容器不会管理这个 bean，容器不会调用销毁方法</li>
 * </ul>
 * <p>
 * BeanPostProcessor 原理
 * populateBean(beanName, mbd, instanceWrapper); 给 bean 进行属性赋值
 * initializeBean(beanName, exposedObject, mbd)
 * 遍历得到容器中所有的 BeanPostProcessor，挨个执行 BeforeInitialization，一旦返回 null，跳出for循环，不会执行后边的 BeanPostProcessor
 * 1. applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName);
 * 2. invokeInitMethods(beanName, wrappedBean, mbd); 执行初始化
 * 3. applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
 * <p>
 * 1. 指定初始化和销毁方法
 * <ul>
 * <li>1. 指定init-method="" destroy-method=""</li>
 * <li>2. 通过让 bean 实现 InitializingBean (定义初始化逻辑)，DisposableBean （定义销毁逻辑）</li>
 * <li>3. 可以使用 JSR250</li>
 * <ul>
 * <li>1. @PostConstruct 在 bean 创建完成并且属性赋值完成，来执行初始化方法</li>
 * <li>2. @PreDestroy 在容器销毁 bean 之前通知我们进行清理工作</li>
 * </ul>
 * <li>4. BeanPostProcessor bean的后置处理器</li>
 * <ul>
 * <li>1. 在 bean 初始化前后进行一些处理工作</li>
 * <li>2. postProcessBeforeInitialization 在初始化之前工作</li>
 * <li>3. postProcessAfterInitialization 在初始化之后工作</li>
 * </ul>
 * </ul>
 * Spring 底层对 BeanPostProcessor 的使用
 * 1. bean赋值，注入其他组件，@Autowired 生命周期注解功能，@Async，xxx BeanPostProcessor
 */
@Configuration
public class MainConfigOfLifeCircle {

    //@Scope(value = "prototype")
    @Bean(initMethod = "init", destroyMethod = "destroy")
    public Car car() {
        return new Car();
    }
}
