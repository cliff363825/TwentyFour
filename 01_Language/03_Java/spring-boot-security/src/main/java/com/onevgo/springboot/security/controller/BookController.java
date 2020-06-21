package com.onevgo.springboot.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookController {
    @GetMapping("/login")
    public String login() {
        return "book/login";
    }

    @PreAuthorize("hasAuthority('BookList')")
    @GetMapping("/book/list")
    public String list() {
        return "book/list";
    }

    @PreAuthorize("hasAuthority('BookAdd')")
    @GetMapping("/book/add")
    public String add() {
        return "book/add";
    }

    @PreAuthorize("hasAuthority('BookDetail')")
    @GetMapping("/book/detail")
    public String detail() {
        return "book/detail";
    }
}
