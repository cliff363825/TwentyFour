package com.onevgo.spring.annotation.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class UserDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insert() {
        String sql = "insert into `user` (username, password, age) values (?, ?, ?)";

        String username = UUID.randomUUID().toString().substring(0, 5);
        jdbcTemplate.update(sql, username, "123456", 19);
    }
}
