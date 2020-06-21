package com.onevgo.spring.beans;

import com.onevgo.spring.beans.annotation.TestObject;
import com.onevgo.spring.beans.annotation.controller.UserController;
import com.onevgo.spring.beans.annotation.repository.UserRepository;
import com.onevgo.spring.beans.annotation.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationTest {
    private ClassPathXmlApplicationContext applicationContext;

    @Before
    public void setUp() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext("beans-annotation.xml");
    }

    @After
    public void tearDown() throws Exception {
        applicationContext.close();
    }

    @Test
    public void test01() {
        // org.springframework.beans.factory.NoSuchBeanDefinitionException: No bean named 'testObject' available
        TestObject testObject = applicationContext.getBean("testObject", TestObject.class);
        System.out.println(testObject);
    }

    @Test
    public void test02() {
        UserController userController = (UserController) applicationContext.getBean("userController");
        System.out.println(userController);
        userController.execute();
    }

    @Test
    public void test03() {
        UserService userService = (UserService) applicationContext.getBean("userService");
        System.out.println(userService);
    }

    @Test
    public void test04() {
        UserRepository userRepository = (UserRepository) applicationContext.getBean("userRepositoryImpl");
        System.out.println(userRepository);
    }
}
