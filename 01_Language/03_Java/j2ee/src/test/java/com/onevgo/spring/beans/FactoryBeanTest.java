package com.onevgo.spring.beans;

import com.onevgo.spring.beans.factorybean.Car;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FactoryBeanTest {
    private ClassPathXmlApplicationContext applicationContext;

    @Before
    public void setUp() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext("beans-beanfactory.xml");
    }

    @After
    public void tearDown() throws Exception {
        applicationContext.close();
    }

    @Test
    public void testGetBean() {
        Car car = (Car) applicationContext.getBean("car");
        System.out.println(car);
    }

    @Test
    public void testGetFactoryBean() {
        FactoryBean<Car> carFactoryBean = (FactoryBean<Car>) applicationContext.getBean("&car");
        System.out.println(carFactoryBean);
    }
}
