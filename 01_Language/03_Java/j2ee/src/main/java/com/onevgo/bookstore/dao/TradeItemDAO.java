package com.onevgo.bookstore.dao;

import java.util.Collection;
import java.util.Set;

import com.onevgo.bookstore.domain.TradeItem;

public interface TradeItemDAO {

    /**
     * 批量保存 TradeItem 对象
     *
     * @param items
     */
    void batchSave(Collection<TradeItem> items);

    /**
     * 根据 tradeId 获取和其关联的 TradeItem 的集合
     *
     * @param tradeId
     * @return
     */
    Set<TradeItem> getTradeItemsWithTradeId(Integer tradeId);

}

