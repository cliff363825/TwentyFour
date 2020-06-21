package com.onevgo.spring;

import com.onevgo.spring.helloworld.Car;
import com.onevgo.spring.helloworld.HelloWorld;
import com.onevgo.spring.helloworld.Person;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloWorldTest {
    private ClassPathXmlApplicationContext applicationContext;

    @Before
    public void setUp() throws Exception {
        // 1. 创建spring 的IOC容器对象
        applicationContext = new ClassPathXmlApplicationContext("beans-helloworld.xml");
    }

    @After
    public void tearDown() throws Exception {
        applicationContext.close();
    }

    @Test
    public void test01() {
        // 2. 从IOC容器中获取 bean 对象
        HelloWorld helloWorld1 = (HelloWorld) applicationContext.getBean("helloWorld");
        helloWorld1.hello();

        /*
        HelloWorld helloWorld = new HelloWorld();
        helloWorld.setName("onevgo");
        helloWorld.hello();
        */
    }

    @Test
    public void test02() {
        // org.springframework.beans.factory.NoUniqueBeanDefinitionException
        HelloWorld bean = applicationContext.getBean(HelloWorld.class);
        bean.hello();
    }

    @Test
    public void test03() {
        Car car = (Car) applicationContext.getBean("car");
        System.out.println(car);

        Car car2 = (Car) applicationContext.getBean("car2");
        System.out.println(car2);

        Person person = (Person) applicationContext.getBean("person");
        System.out.println(person);

        Person person2 = (Person) applicationContext.getBean("person2");
        System.out.println(person2);

        Person person3 = (Person) applicationContext.getBean("person3");
        System.out.println(person3);
    }
}
