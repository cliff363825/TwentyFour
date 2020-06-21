package com.onevgo.auth;

import lombok.Data;

import java.util.List;

@Data
public class User {
    private String username;
    private List<Authority> authorities;

    public User(String username, List<Authority> authorities) {
        this.username = username;
        this.authorities = authorities;
    }
}
