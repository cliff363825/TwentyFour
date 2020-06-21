package com.onevgo.spring.beans;

import com.onevgo.spring.beans.collections.Person;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CollectionsTest {
    private ClassPathXmlApplicationContext applicationContext;

    @Before
    public void setUp() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext("beans-collections.xml");
    }

    @After
    public void tearDown() throws Exception {
        applicationContext.close();
    }

    @Test
    public void test01() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans-collections.xml");

        Person person = (Person) applicationContext.getBean("person");
        System.out.println(person);

        Person person2 = (Person) applicationContext.getBean("person2");
        System.out.println(person2);
    }

    @Test
    public void testList() {
        Person person3 = (Person) applicationContext.getBean("person3");
        System.out.println(person3);
    }

    @Test
    public void testMap() {
        Person person4 = (Person) applicationContext.getBean("person4");
        System.out.println(person4);
    }

    @Test
    public void testProperties() {
        Person person5 = (Person) applicationContext.getBean("person5");
        System.out.println(person5);
    }

    @Test
    public void testList02() {
        Person person6 = (Person) applicationContext.getBean("person6");
        System.out.println(person6);
    }
}
