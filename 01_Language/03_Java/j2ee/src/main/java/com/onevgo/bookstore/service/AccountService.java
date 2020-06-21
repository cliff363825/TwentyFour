package com.onevgo.bookstore.service;

import com.onevgo.bookstore.dao.AccountDAO;
import com.onevgo.bookstore.dao.impl.AccountDAOIml;
import com.onevgo.bookstore.domain.Account;

public class AccountService {

    private AccountDAO accountDAO = new AccountDAOIml();

    public Account getAccount(int accountId) {
        return accountDAO.get(accountId);
    }

}
