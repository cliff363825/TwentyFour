package com.onevgo.auth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDAO {

    private static Map<String, User> users = new HashMap<>();
    private static List<Authority> authorities = new ArrayList<>();

    static {
        authorities.add(new Authority("Article-1", "/auth/article-1.jsp"));
        authorities.add(new Authority("Article-2", "/auth/article-2.jsp"));
        authorities.add(new Authority("Article-3", "/auth/article-3.jsp"));
        authorities.add(new Authority("Article-4", "/auth/article-4.jsp"));

        User user1 = new User("AAA", authorities.subList(0, 2));
        users.put("AAA", user1);

        User user2 = new User("BBB", authorities.subList(2, 4));
        users.put("BBB", user2);
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public User getUser(String username) {
        return users.get(username);
    }

    public void update(String username, List<Authority> authorities) {
        User user = users.get(username);
        if (user == null) {
            return;
        }

        user.setAuthorities(authorities);
    }
}
