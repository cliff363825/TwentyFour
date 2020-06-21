package com.onevgo.spring.tx.xml;

import java.math.BigDecimal;

public interface BookShopDAO {
    public BigDecimal findBookPriceByIsbn(String isbn);

    public void updateBookStock(String isbn);

    public void updateUserAccount(String username, BigDecimal price);
}
