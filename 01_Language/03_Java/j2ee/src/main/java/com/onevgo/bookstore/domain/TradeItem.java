package com.onevgo.bookstore.domain;

import lombok.Data;

@Data
public class TradeItem {
    private Integer tradeItemId;
    //和 TradeItem 关联的 Book
    private Book book;
    private int quantity;
    //和 TradeItem 关联的 Book 的 bookId
    private Integer bookId;
    private Integer tradeId;

    public TradeItem() {
    }

    public TradeItem(Integer tradeItemId, Integer bookId, int quantity, Integer tradeId) {
        super();
        this.tradeItemId = tradeItemId;
        this.bookId = bookId;
        this.quantity = quantity;
        this.tradeId = tradeId;
    }
}
