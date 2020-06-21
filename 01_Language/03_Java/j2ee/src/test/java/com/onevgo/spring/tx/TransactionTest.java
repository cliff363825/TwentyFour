package com.onevgo.spring.tx;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.Arrays;

public class TransactionTest {
    private ApplicationContext applicationContext;
    private BookShopDAO bookShopDAO;
    private BookShopService bookShopService;
    private Cashier cashier;
    @Before
    public void setUp() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext("spring-tx.xml");
        bookShopDAO = (BookShopDAO) applicationContext.getBean("bookShopDAO");
        bookShopService = (BookShopService) applicationContext.getBean("bookShopService");
        cashier = (Cashier) applicationContext.getBean("cashier");
    }

    @Test
    public void testBookShopDAOFindPriceByIsbn() {
        System.out.println(bookShopDAO.findBookPriceByIsbn("12345"));
    }

    @Test
    public void testUpdateBookStock() {
        bookShopDAO.updateBookStock("12345");
    }

    @Test
    public void testUpdateUserAccount() {
        bookShopDAO.updateUserAccount("aa", BigDecimal.valueOf(200));
    }

    @Test
    public void testPurchase() {
        bookShopService.purchase("aa", "12345");
    }

    @Test
    public void testTransactionPropagation() {
        cashier.checkout("aa", Arrays.asList("12345", "23456"));
    }
}
