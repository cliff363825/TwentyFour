package com.onevgo.spring.annotation;

import com.onevgo.spring.annotation.bean.Person;
import com.onevgo.spring.annotation.config.MainConfigOfPropertyValues;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

public class IOCTest_PropertyValue {
    private ApplicationContext applicationContext;

    @Before
    public void setUp() throws Exception {
        applicationContext = new AnnotationConfigApplicationContext(MainConfigOfPropertyValues.class);
    }

    @After
    public void tearDown() throws Exception {
        ((AnnotationConfigApplicationContext) applicationContext).close();
    }

    @Test
    public void test01() {
        Person person = (Person) applicationContext.getBean("person");
        System.out.println(person);

        Environment environment = applicationContext.getEnvironment();
        String nickName = environment.getProperty("person.nickName");
        System.out.println(nickName);
    }
}
