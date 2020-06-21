package com.onevgo.bookstore.dao;

import com.onevgo.bookstore.domain.Account;

public interface AccountDAO {

    /**
     * 根据 accountId 获取对应的 Account 对象
     *
     * @param accountId
     * @return
     */
    Account get(Integer accountId);

    /**
     * 根据传入的 accountId, amount 更新指定账户的余额: 扣除 amount 指定的钱数
     *
     * @param accountId
     * @param amount
     */
    void updateBalance(Integer accountId, float amount);

}
