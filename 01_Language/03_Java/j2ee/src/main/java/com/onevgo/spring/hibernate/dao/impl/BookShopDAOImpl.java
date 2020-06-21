package com.onevgo.spring.hibernate.dao.impl;

import com.onevgo.spring.hibernate.BookStockException;
import com.onevgo.spring.hibernate.UserAccountException;
import com.onevgo.spring.hibernate.dao.BookShopDAO;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public class BookShopDAOImpl implements BookShopDAO {
    @Autowired
    private SessionFactory sessionFactory;
    // 不推荐使用 HibernateTemplate 和 HibernateDaoSupport
    // 因为这样会导致 DAO 和 Spring 的 API 进行耦合
    // 可移植性变差
//    private HibernateTemplate hibernateTemplate;

    @Override
    public BigDecimal findBookPriceByIsbn(String isbn) {
        String hql = "select b.price from Book b where b.isbn = ?";
        Query query = getSession().createQuery(hql).setString(0, isbn);
        return (BigDecimal) query.uniqueResult();
    }

    @Override
    public void updateBookStock(String isbn) {
        // 验证书的库存是否充足
        String hql2 = "select b.stock from Book b where b.isbn = ?";
        Query query1 = getSession().createQuery(hql2).setString(0, isbn);
        Integer stock = (Integer) query1.uniqueResult();
        if (stock == null || stock.compareTo(0) < 0) {
            throw new BookStockException("库存不足");
        }

        String hql = "update Book b set b.stock=b.stock-1 where b.isbn = ?";
        Query query = getSession().createQuery(hql).setString(0, isbn);
        query.executeUpdate();
    }

    @Override
    public void updateUserAccount(String username, BigDecimal price) {
        // 验证余额是否足够
        String hql = "select a.balance from Account a where a.username = ?";
        Query query = getSession().createQuery(hql).setString(0, username);
        BigDecimal balance = (BigDecimal) query.uniqueResult();
        if (balance == null || balance.compareTo(price) < 0) {
            throw new UserAccountException("余额不足");
        }

        String hql2 = "update Account a set a.balance=a.balance-? where a.username = ?";
        Query query1 = getSession().createQuery(hql2).setBigDecimal(0, price).setString(1, username);
        query1.executeUpdate();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // 获取和当前线程绑定的Session
    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
