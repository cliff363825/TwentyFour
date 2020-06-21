package com.onevgo.spring.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository(value = "bookShopDAO")
public class BookShopDAOImpl implements BookShopDAO {
    private JdbcTemplate jdbcTemplate;
    @Override
    public BigDecimal findBookPriceByIsbn(String isbn) {
        String sql = "select price from book where isbn = ?";
        BigDecimal price = jdbcTemplate.queryForObject(sql, BigDecimal.class, isbn);
        return price;
    }

    @Override
    public void updateBookStock(String isbn) {
        // 检查书的库存是否足够，若不够，则抛出异常
        String sql2 = "select stock from book_stock where isbn = ?";
        Integer stock = jdbcTemplate.queryForObject(sql2, Integer.class, isbn);
        if (stock < 1) {
            throw new BookStockException("库存不足");
        }

        String sql = "update book_stock set stock = stock - 1 where isbn = ?";
        jdbcTemplate.update(sql, isbn);
    }

    @Override
    public void updateUserAccount(String username, BigDecimal price) {
        // 验证余额是否足够，若不足，则抛出异常
        String sql2 = "select balance from account where username = ?";
        BigDecimal balance = jdbcTemplate.queryForObject(sql2, BigDecimal.class, username);
        if (balance == null || balance.compareTo(price) < 0) {
            throw new UserAccountException("余额不足");
        }

        String sql = "update account set balance = balance - ? where username = ?";
        jdbcTemplate.update(sql, price, username);
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
