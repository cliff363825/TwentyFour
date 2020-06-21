package com.onevgo.servlet.v3.springmvc.controller;

import com.onevgo.servlet.v3.springmvc.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @Autowired
    private HelloService helloService;

    @ResponseBody
    @RequestMapping("/hello")
    public String hello() {
        String hello = helloService.sayHello("tomcat...");
        return hello;
    }

    @RequestMapping("/success")
    public String success() {
        return "success";
    }
}
