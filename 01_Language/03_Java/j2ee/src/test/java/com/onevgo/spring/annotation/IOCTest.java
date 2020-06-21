package com.onevgo.spring.annotation;

import com.onevgo.spring.annotation.bean.Blue;
import com.onevgo.spring.annotation.bean.Person;
import com.onevgo.spring.annotation.config.MainConfig2;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

import java.util.Map;

public class IOCTest {
    private ApplicationContext applicationContext;

    @Before
    public void setUp() throws Exception {
        applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);
    }

    @After
    public void tearDown() throws Exception {
        ((AnnotationConfigApplicationContext) applicationContext).close();
    }

    @Test
    public void test01() {
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String name : beanDefinitionNames) {
            Object bean = applicationContext.getBean(name);
            System.out.println(name + " => " + bean.getClass().getName());
        }
    }

    @Test
    public void test02() {
        Map<String, Person> personMap = applicationContext.getBeansOfType(Person.class);
        System.out.println(personMap);

        Object person = applicationContext.getBean("person");
        Object person1 = applicationContext.getBean("person");
        System.out.println(person == person1);
    }

    @Test
    public void test03() {
        // 动态获取环境变量的值 Mac OS X
        // -Dos.name=Windows
        Environment environment = applicationContext.getEnvironment();
        String osName = environment.getProperty("os.name");
        System.out.println(osName);
    }

    @Test
    public void testImport() {
        Blue bean = applicationContext.getBean(Blue.class);
        System.out.println(bean);

        // 工厂Bean 获取的是调用 getObject 创建的对象
        Object colorFactoryBean = applicationContext.getBean("colorFactoryBean");
        System.out.println(colorFactoryBean);

        Object colorFactoryBean2 = applicationContext.getBean("&colorFactoryBean");
        System.out.println(colorFactoryBean2);
    }
}
