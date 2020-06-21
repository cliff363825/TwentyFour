package com.onevgo.spring.tx;

import java.math.BigDecimal;

public interface BookShopDAO {
    public BigDecimal findBookPriceByIsbn(String isbn);

    public void updateBookStock(String isbn);

    public void updateUserAccount(String username, BigDecimal price);
}
