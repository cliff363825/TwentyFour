package com.onevgo.spring.annotation.service;

import com.onevgo.spring.annotation.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "myUserService")
public class UserService {
    @Autowired
    private UserDAO userDAO;

    @Transactional
    public void insertUser() {
        userDAO.insert();
        System.out.println("插入完成...");
        int i = 10 / 0;
    }
}
