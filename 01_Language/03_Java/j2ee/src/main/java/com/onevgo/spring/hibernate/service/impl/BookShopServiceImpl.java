package com.onevgo.spring.hibernate.service.impl;

import com.onevgo.spring.hibernate.dao.BookShopDAO;
import com.onevgo.spring.hibernate.service.BookShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service(value = "bookShopService")
public class BookShopServiceImpl implements BookShopService {
    private BookShopDAO bookShopDAO;

    /**
     * Spring hibernate 事务的流程
     * 1. 在方法开始之前
     * <ul>
     * <li>1. 获取 Session</li>
     * <li>2. 把 Session 和当前线程绑定，这样就可以在 DAO 中使用 SessionFactory的 getCurrentSession() 方法来获取 Session 了</li>
     * <li>3. 开启事务</li>
     * </ul>
     * 2. 若方法正常结束，即没有出现异常，则
     * <ul>
     * <li>1. 提交事务</li>
     * <li>2. 使和当前线程绑定的 Session 解除绑定</li>
     * <li>3. 关闭 Session</li>
     * </ul>
     * 3. 若方法出现异常，则：
     * <ul>
     * <li>1. 回滚事务</li>
     * <li>2. 使和当前线程绑定的 Session 解除绑定</li>
     * <li>3. 关闭 Session</li>
     * </ul>
     *
     * @param username
     * @param isbn
     */
    @Override
    public void purchase(String username, String isbn) {
        BigDecimal price = bookShopDAO.findBookPriceByIsbn(isbn);
        bookShopDAO.updateBookStock(isbn);
        bookShopDAO.updateUserAccount(username, price);
    }

    public BookShopDAO getBookShopDAO() {
        return bookShopDAO;
    }

    @Autowired
    public void setBookShopDAO(BookShopDAO bookShopDAO) {
        this.bookShopDAO = bookShopDAO;
    }
}
