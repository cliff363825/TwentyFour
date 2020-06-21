package com.onevgo.spring.beans;

import com.onevgo.spring.beans.autowired.Person;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AutowiredTest {
    private ClassPathXmlApplicationContext applicationContext;

    @Before
    public void setUp() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext("beans-autowired.xml");
    }

    @After
    public void tearDown() throws Exception {
        applicationContext.close();
    }

    @Test
    public void testAutowired() {
        Person person = (Person) applicationContext.getBean("person");
        System.out.println(person);
    }
}
