package com.onevgo.springboot.security.service;

import com.onevgo.springboot.security.dao.UserDao;
import com.onevgo.springboot.security.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public SysUser getUserByName(String username) {
        return userDao.selectByName(username);
    }
}
