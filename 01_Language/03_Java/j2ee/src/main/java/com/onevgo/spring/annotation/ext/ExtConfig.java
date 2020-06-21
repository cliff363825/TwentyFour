package com.onevgo.spring.annotation.ext;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 扩展原理：
 * 1. BeanPostProcessor: bean后置处理器，bean创建对象初始化前后进行拦截工作的
 * 2. BeanFactoryPostProcessor: beanFactory的后置处理器；
 * 在BeanFactory标准初始化之后调用，来定制和修改BeanFactory的内容；
 * 所有的bean定义已经保存加载到beanFactory，但是bean的实例还未创建
 * 3. BeanFactoryPostProcessor原理:
 * <ul>
 * <li>1. ioc容器创建对象</li>
 * <li>2. invokeBeanFactoryPostProcessors(beanFactory);</li>
 * <li>如何找到所有的BeanFactoryPostProcessor并执行他们的方法；</li>
 * <ul>
 * <li>1. 直接在BeanFactory中找到所有类型是BeanFactoryPostProcessor的组件，并执行他们的方法</li>
 * <li>2. 在初始化创建其他组件前面执行</li>
 * </ul>
 * </ul>
 * 4. BeanDefinitionRegistryPostProcessor extends BeanFactoryPostProcessor
 * postProcessBeanDefinitionRegistry();
 * 在所有bean定义信息将要被加载，bean实例还未创建的；
 * <p>
 * 优先于BeanFactoryPostProcessor执行；
 * 利用BeanDefinitionRegistryPostProcessor给容器中再额外添加一些组件；
 * <p>
 * 5. 原理：
 * <ul>
 * <li>1. ioc创建对象</li>
 * <li>2. refresh() -> invokeBeanFactoryPostProcessors(beanFactory);</li>
 * <li>3. 从容器中获取到所有的BeanDefinitionRegistryPostProcessor组件。</li>
 * <ul>
 * <li>1. 依次触发所有的postProcessBeanDefinitionRegistry()方法</li>
 * <li>2. 再来触发postProcessBeanFactory()方法</li>
 * </ul>
 * <li>4. 再来从容器中找到BeanFactoryPostProcessor组件；然后依次触发postProcessBeanFactory()方法</li>
 * </ul>
 * 6. ApplicationListener：监听容器中发布的事件。事件驱动模型开发；
 * public interface ApplicationListener<E extends ApplicationEvent>
 * 监听 ApplicationEvent 及其下面的子事件；
 * 步骤：
 * <ul>
 * <li>1. 写一个监听器（ApplicationListener实现类）来监听某个事件（ApplicationEvent及其子类）</li>
 * <li><code>@EventListener</code> 原理：使用EventListenerMethodProcessor处理器来解析方法上的@EventListener；</li>
 * <li>2. 把监听器加入到容器；</li>
 * <li>3. 只要容器中有相关事件的发布，我们就能监听到这个事件；</li>
 * <li>ContextRefreshedEvent：容器刷新完成（所有bean都完全创建）会发布这个事件；</li>
 * <li>ContextClosedEvent：关闭容器会发布这个事件；</li>
 * <li>4. 发布一个事件：</li>
 * <li>applicationContext.publishEvent()；</li>
 * </ul>
 * 原理：
 * ContextRefreshedEvent、IOCTest_Ext$1[source=我发布的时间]、ContextClosedEvent；
 * <ul>
 * <li>1. ContextRefreshedEvent事件：</li>
 * <ul>
 * <li>1. 容器创建对象：refresh()；</li>
 * <li>2. finishRefresh();容器刷新完成会发布ContextRefreshedEvent事件</li>
 * </ul>
 * <li>2. 自己发布事件；</li>
 * <li>3. 容器关闭会发布ContextClosedEvent；</li>
 * </ul>
 * <p>
 * 【事件发布流程】：
 * publishEvent(new ContextRefreshedEvent(this));
 * <ul>
 * <li>1. 获取事件的多播器（派发器）：getApplicationEventMulticaster()</li>
 * <li>2. multicastEvent派发事件：</li>
 * <li>3. 获取到所有的ApplicationListener；</li>
 * <code>for (final ApplicationListener<?> listener : getApplicationListeners(event, type)) {</code>
 * <ul>
 * <li>1. 如果有Executor，可以支持使用Executor进行异步派发；</li>
 * <li>Executor executor = getTaskExecutor();</li>
 * <li>2. 否则，同步的方式直接执行listener方法；invokeListener(listener, event);</li>
 * <li>拿到listener回调onApplicationEvent方法；</li>
 * </ul>
 * </ul>
 * <p>
 * 【事件多播器（派发器）】
 * <ul>
 * <li>1. 容器创建对象：refresh();</li>
 * <li>2. initApplicationEventMulticaster();初始化ApplicationEventMulticaster；</li>
 * <ul>
 * <li>1. 先去容器中找有没有id=“applicationEventMulticaster”的组件；</li>
 * <li>2. 如果没有this.applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);</li>
 * <li>并且加入到容器中，我们就可以在其他组件要派发事件，自动注入这个applicationEventMulticaster；</li>
 * </ul>
 * </ul>
 * <p>
 * 【容器中有哪些监听器】
 * <ul>
 * <li>1. 容器创建对象：refresh();</li>
 * <li>2. registerListeners();</li>
 * <li>从容器中拿到所有的监听器，把他们注册到applicationEventMulticaster中；</li>
 * <li>String[] listenerBeanNames = getBeanNamesForType(ApplicationListener.class, true, false);</li>
 * <li>//将listener注册到ApplicationEventMulticaster中</li>
 * <li>getApplicationEventMulticaster().addApplicationListenerBean(listenerBeanName);</li>
 * </ul>
 * <p>
 * SmartInitializingSingleton 原理：->afterSingletonsInstantiated();
 * <ul>
 * <li>1）、ioc容器创建对象并refresh()；</li>
 * <li>2）、finishBeanFactoryInitialization(beanFactory);初始化剩下的单实例bean；</li>
 * <li>1）、先创建所有的单实例bean；getBean();</li>
 * <li>2）、获取所有创建好的单实例bean，判断是否是SmartInitializingSingleton类型的；</li>
 * <li>如果是就调用afterSingletonsInstantiated();</li>
 * </ul>
 */
@ComponentScan("com.onevgo.spring.annotation.ext")
@Configuration
public class ExtConfig {
}
