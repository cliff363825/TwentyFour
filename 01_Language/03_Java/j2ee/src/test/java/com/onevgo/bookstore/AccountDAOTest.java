package com.onevgo.bookstore;

import com.onevgo.bookstore.dao.AccountDAO;
import com.onevgo.bookstore.dao.impl.AccountDAOIml;
import com.onevgo.bookstore.domain.Account;
import org.junit.Test;

public class AccountDAOTest {

    AccountDAO accountDAO = new AccountDAOIml();

    @Test
    public void testGet() {
        Account account = accountDAO.get(1);
        System.out.println(account.getBalance());
    }

    @Test
    public void testUpdateBalance() {
        accountDAO.updateBalance(1, 50);
    }
}
