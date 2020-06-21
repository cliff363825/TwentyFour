package com.onevgo.spring.annotation.service;

import com.onevgo.spring.annotation.dao.BookDAO;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class BookService {
//    @Qualifier("bookDAO2")
//    @Autowired(required = false)
//    @Resource(name = "bookDAO2")
    @Inject
    private BookDAO bookDAO;

    public void print() {
        System.out.println(bookDAO);
    }
}
