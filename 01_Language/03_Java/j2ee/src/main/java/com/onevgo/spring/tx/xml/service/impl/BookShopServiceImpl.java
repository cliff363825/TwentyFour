package com.onevgo.spring.tx.xml.service.impl;

import com.onevgo.spring.tx.xml.BookShopDAO;
import com.onevgo.spring.tx.xml.service.BookShopService;

import java.math.BigDecimal;

public class BookShopServiceImpl implements BookShopService {
    private BookShopDAO bookShopDAO;

    @Override
    public void purchase(String username, String isbn) {
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        // 1. 获取书的单价
        BigDecimal price = bookShopDAO.findBookPriceByIsbn(isbn);
        // 2. 获取书的库存
        bookShopDAO.updateBookStock(isbn);
        // 3. 更新用户余额
        bookShopDAO.updateUserAccount(username, price);
    }

    public BookShopDAO getBookShopDAO() {
        return bookShopDAO;
    }

    public void setBookShopDAO(BookShopDAO bookShopDAO) {
        this.bookShopDAO = bookShopDAO;
    }
}
