package com.onevgo.spring.beans.annotation.controller;

import com.onevgo.spring.beans.annotation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    @Autowired
    @Qualifier(value = "userService")
    private UserService userService;

    public void execute() {
        System.out.println("UserController.execute");
        userService.add();
    }
}
