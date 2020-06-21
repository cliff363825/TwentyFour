package com.onevgo.spring.beans;

import com.onevgo.spring.beans.factory.Car;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FactoryTest {
    private ClassPathXmlApplicationContext applicationContext;

    @Before
    public void setUp() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext("beans-factory.xml");
    }

    @After
    public void tearDown() throws Exception {
        applicationContext.close();
    }

    @Test
    public void testStaticFactory() {
        Car car1 = (Car) applicationContext.getBean("car1");
        System.out.println(car1);
    }

    @Test
    public void testInstanceFactory() {
        Car car2 = (Car) applicationContext.getBean("car2");
        System.out.println(car2);
    }
}
