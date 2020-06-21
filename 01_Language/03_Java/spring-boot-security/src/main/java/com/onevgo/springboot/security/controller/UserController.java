package com.onevgo.springboot.security.controller;

import com.onevgo.springboot.security.entity.SysUser;
import com.onevgo.springboot.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @PreAuthorize("hasAuthority('UserIndex')")
    @GetMapping("/user/index")
    public String index() {
        return "user/index";
    }

    @GetMapping("/user/hi")
    @ResponseBody
    public String hi() {
        SysUser sysUser = userService.getUserByName("zhangsan");
        return sysUser.toString();
    }
}
