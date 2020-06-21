package com.onevgo.spring.hibernate;

import com.onevgo.spring.hibernate.service.BookShopService;
import com.onevgo.spring.hibernate.service.Cashier;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.util.Arrays;

public class SpringHibernateTest {
    private ApplicationContext applicationContext;
    private BookShopService bookShopService;
    private Cashier cashier;

    @Before
    public void setUp() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext("spring-hibernate.xml");
        bookShopService = (BookShopService) applicationContext.getBean("bookShopService");
        cashier = (Cashier) applicationContext.getBean("cashier");
    }

    @Test
    public void testCashier() {
        cashier.checkout("aa", Arrays.asList("1001", "1002"));
    }

    @Test
    public void testBookShopService() {
        bookShopService.purchase("aa", "1001");
    }

    @Test
    public void testDataSource() {
        DataSource dataSource = (DataSource) applicationContext.getBean("dataSource");
        System.out.println(dataSource);
    }
}
