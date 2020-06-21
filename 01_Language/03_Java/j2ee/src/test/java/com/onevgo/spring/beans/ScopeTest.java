package com.onevgo.spring.beans;

import com.onevgo.spring.beans.scope.Car;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ScopeTest {
    private ClassPathXmlApplicationContext applicationContext;

    @Before
    public void setUp() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext("beans-scope.xml");
    }

    @After
    public void tearDown() throws Exception {
        applicationContext.close();
    }

    @Test
    public void testSingleton() {
        Car car = (Car) applicationContext.getBean("car");
        Car car2 = (Car) applicationContext.getBean("car");
        System.out.println(car == car2);
    }

    @Test
    public void testPrototype() {
        Car car = (Car) applicationContext.getBean("car2");
        Car car2 = (Car) applicationContext.getBean("car2");
        System.out.println(car == car2);
    }
}
