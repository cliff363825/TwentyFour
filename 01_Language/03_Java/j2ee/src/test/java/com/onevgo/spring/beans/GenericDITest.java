package com.onevgo.spring.beans;

import com.onevgo.spring.beans.generic.di.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class GenericDITest {
    private ClassPathXmlApplicationContext applicationContext;

    @Before
    public void setUp() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext("beans-generic-di.xml");
    }

    @After
    public void tearDown() throws Exception {
        applicationContext.close();
    }

    @Test
    public void test01() {
        UserService userService = (UserService) applicationContext.getBean("userService");
        userService.add();
    }
}
