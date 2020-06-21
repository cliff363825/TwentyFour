package com.onevgo.bookstore;

import com.onevgo.bookstore.dao.UserDAO;
import com.onevgo.bookstore.dao.impl.UserDAOImpl;
import com.onevgo.bookstore.domain.User;
import org.junit.Test;

public class UserDAOTest {

    private UserDAO userDAO = new UserDAOImpl();

    @Test
    public void testGetUser() {
        User user = userDAO.getUser("AAA");
        System.out.println(user);
    }
}
