package com.onevgo.spring.annotation;

import com.onevgo.spring.annotation.bean.Boss;
import com.onevgo.spring.annotation.bean.Car;
import com.onevgo.spring.annotation.bean.Color;
import com.onevgo.spring.annotation.config.MainConfigOfAutowired;
import com.onevgo.spring.annotation.service.BookService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOCTest_Autowired {
    private ApplicationContext applicationContext;

    @Before
    public void setUp() throws Exception {
        applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAutowired.class);
    }

    @After
    public void tearDown() throws Exception {
        ((AnnotationConfigApplicationContext) applicationContext).close();
    }

    @Test
    public void test01() {
        BookService bookService = applicationContext.getBean(BookService.class);
        bookService.print();

//        BookDAO bookDAO = applicationContext.getBean(BookDAO.class);
//        System.out.println(bookDAO);

        Boss boss = applicationContext.getBean(Boss.class);
        System.out.println(boss);

        Car car = applicationContext.getBean(Car.class);
        System.out.println(car);

        Color color = applicationContext.getBean(Color.class);
        System.out.println(color);
    }
}
