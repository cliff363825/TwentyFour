package com.onevgo.bookstore.dao.impl;

import com.onevgo.bookstore.dao.UserDAO;
import com.onevgo.bookstore.domain.User;

public class UserDAOImpl extends BaseDAO<User> implements UserDAO {

    @Override
    public User getUser(String username) {
        String sql = "SELECT userId, username, accountId " +
                "FROM userinfo WHERE username = ?";
        return query(sql, username);
    }
}
