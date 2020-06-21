package com.onevgo.controller;

import com.onevgo.starter.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Autowired
    private HelloService helloService;

    @GetMapping("/hello111")
    public String hello() {
        return helloService.sayHello("haha");
    }
}
