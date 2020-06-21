package com.onevgo.bookstore.domain;

import lombok.Data;

import java.util.Set;

@Data
public class User {
    private Integer userId;
    private String username;
    private int accountId;
    private Set<Trade> trades;

    public User() {
    }

    public User(Integer userId, String username, int accountId) {
        super();
        this.userId = userId;
        this.username = username;
        this.accountId = accountId;
    }
}
