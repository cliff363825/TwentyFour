package com.onevgo.bookstore.domain;

import lombok.Data;

import java.sql.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
public class Trade {
    //Trade 对象对应的 id
    private Integer tradeId;
    //交易的时间
    private Date tradeTime;
    //Trade 关联的多个 TradeItem
    private Set<TradeItem> items;
    //和 Trade 关联的 User 的 userId
    private Integer userId;
}

