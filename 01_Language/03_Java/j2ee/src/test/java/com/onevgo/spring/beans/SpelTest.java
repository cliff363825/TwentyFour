package com.onevgo.spring.beans;

import com.onevgo.spring.beans.spel.Address;
import com.onevgo.spring.beans.spel.Car;
import com.onevgo.spring.beans.spel.Person;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpelTest {
    private ClassPathXmlApplicationContext applicationContext;

    @Before
    public void setUp() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext("beans-spel.xml");
    }

    @After
    public void tearDown() throws Exception {
        applicationContext.close();
    }

    @Test
    public void test01() {
        Address address = (Address) applicationContext.getBean("address");
        System.out.println(address);

        Car car = (Car) applicationContext.getBean("car");
        System.out.println(car);

        Person person = (Person) applicationContext.getBean("person");
        System.out.println(person);
    }
}
