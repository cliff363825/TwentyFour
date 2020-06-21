package com.onevgo.spring.hibernate.service.impl;

import com.onevgo.spring.hibernate.service.BookShopService;
import com.onevgo.spring.hibernate.service.Cashier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "cashier")
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

    @Autowired
    public void setBookShopService(BookShopService bookShopService) {
        this.bookShopService = bookShopService;
    }
}
