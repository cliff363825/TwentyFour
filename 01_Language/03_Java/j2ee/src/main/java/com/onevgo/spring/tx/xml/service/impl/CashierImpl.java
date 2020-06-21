package com.onevgo.spring.tx.xml.service.impl;

import com.onevgo.spring.tx.xml.service.BookShopService;
import com.onevgo.spring.tx.xml.service.Cashier;

import java.util.List;

public class CashierImpl implements Cashier {
    private BookShopService bookShopService;
    @Override
    public void checkout(String username, List<String> isbns) {
        for (String isbn : isbns) {
            bookShopService.purchase(username, isbn);
        }
    }

    public BookShopService getBookShopService() {
        return bookShopService;
    }

    public void setBookShopService(BookShopService bookShopService) {
        this.bookShopService = bookShopService;
    }
}
