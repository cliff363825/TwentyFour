package com.onevgo.bookstore.dao;

import java.util.Set;

import com.onevgo.bookstore.domain.Trade;

public interface TradeDAO {

    /**
     * 向数据表中插入 Trade 对象
     *
     * @param trade
     */
    void insert(Trade trade);

    /**
     * 根据 userId 获取和其关联的 Trade 的集合
     *
     * @param userId
     * @return
     */
    Set<Trade> getTradesWithUserId(Integer userId);

}
