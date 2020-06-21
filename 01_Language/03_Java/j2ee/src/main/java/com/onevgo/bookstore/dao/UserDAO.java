package com.onevgo.bookstore.dao;

import com.onevgo.bookstore.domain.User;

public interface UserDAO {

    /**
     * 根据用户名获取 User 对象
     *
     * @param username
     * @return
     */
    User getUser(String username);

}

