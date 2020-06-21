package com.onevgo.spring.annotation.config;

import com.onevgo.spring.annotation.bean.Color;
import com.onevgo.spring.annotation.bean.ColorFactoryBean;
import com.onevgo.spring.annotation.bean.Person;
import com.onevgo.spring.annotation.bean.Red;
import com.onevgo.spring.annotation.condition.LinuxCondition;
import com.onevgo.spring.annotation.condition.MyImportBeanDefinitionRegistrar;
import com.onevgo.spring.annotation.condition.MyImportSelector;
import com.onevgo.spring.annotation.condition.WindowsCondition;
import org.springframework.context.annotation.*;

/**
 * 类中组件统一设置，满足当前条件，这个类中配置的所有 bean 注册才能生效
 * <code>@Conditional({WindowsCondition.class})</code>
 * 导入组件，id默认是组件的全类名
 */
@Import({Color.class, Red.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class})
@Configuration
public class MainConfig2 {
    /**
     * <code>@Scope(value = "singleton")</code>
     * 默认是单实例的
     * 1. prototype 多实例的 ioc 容器启动并不会去调用方法创建对象放在容器中。每次获取的时候才会调用方法创建对象；
     * 2. singleton 单实例的 默认值 ioc容器启动会调用方法创建对象放到ioc容器中，以后每次获取就是直接从容器 map.get() 中拿
     * 3. request 同一个请求创建一个实例
     * 4. session 同一个session创建一个实例
     * <p>
     * 懒加载
     * 1. 单实例 bean 默认在容器启动的时候创建对象
     * 2. 懒加载 容器启动不创建对象。第一次使用（获取）Bean 创建对象，并初始化
     *
     * @return
     */
    @Bean
    @Lazy
    public Person person() {
//        System.out.println("给容器中添加 person");
        return new Person("张三", 25);
    }

    /**
     * <code>@Conditional({Condition})</code>: 按照一定的条件进行判断，满足条件给容器中注册bean
     * 1. 如果系统是 windows 给容器中注册 bill
     * 2. 如果是 linux 系统，给容器中注册 linus
     *
     * @return
     */
    @Conditional({WindowsCondition.class})
    @Bean("bill")
    public Person person01() {
        return new Person("Bill Gates", 62);
    }

    @Conditional({LinuxCondition.class})
    @Bean("linus")
    public Person person02() {
        return new Person("linus", 48);
    }

    /**
     * 给容器中注册组件
     * 1. 包扫描 + 组件标注注解 (@Controller / @Service / @Repository / @Component)
     * 2. @Bean [导入的第三方包里面的组件]
     * 3. @Import [快速给容器中导入一个组件]
     * <ul>
     * <li>1. @Import(要导入到容器中的组件)，容器中就会自动组册这个组件，id默认是全类名</li>
     * <li>2. ImportSelector 返回需要导入的组件的全类名数组
     * <li>3. ImportBeanDefinitionRegistrar 手动注册 bean 到容器中
     * </ul>
     * 4. 使用 Spring 提供的 FactoryBean (工厂 Bean)
     * <ul>
     * <li>1. 默认获取到的是工厂 bean 调用 getObject 创建的对象</li>
     * <li>2. 要获取工厂 Bean 本身，我们需要给 id 前面加一个 & (&colorFactoryBean)</li>
     * </ul>
     *
     * @return
     */
    @Bean
    public ColorFactoryBean colorFactoryBean() {
        return new ColorFactoryBean();
    }
}
