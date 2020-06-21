package com.onevgo.spring.beans;

import com.onevgo.spring.beans.relation.Address;
import com.onevgo.spring.beans.relation.Person;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RelationTest {
    private ClassPathXmlApplicationContext applicationContext;

    @Before
    public void setUp() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext("beans-relation.xml");
    }

    @After
    public void tearDown() throws Exception {
        applicationContext.close();
    }

    @Test
    public void testAbstract() {
        // org.springframework.beans.factory.BeanIsAbstractException: Error creating bean with name 'address': Bean definition is abstract
        Address address = (Address) applicationContext.getBean("address");
        System.out.println(address);
    }

    @Test
    public void test01() {
        Address address2 = (Address) applicationContext.getBean("address2");
        System.out.println(address2);

        Address address3 = (Address) applicationContext.getBean("address3");
        System.out.println(address3);

        Person person = (Person) applicationContext.getBean("person");
        System.out.println(person);
    }
}
