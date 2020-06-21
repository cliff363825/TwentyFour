package com.onevgo.spring.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service(value = "bookShopService")
public class BookShopServiceImpl implements BookShopService {
    private BookShopDAO bookShopDAO;

    // 添加事务注解
    // 1. 使用 propagation 指定事务的传播行为，即当前的事务方法被另外一个事务方法调用时如何使用事务
    // REQUIRED: 默认值，即使用调用方法的事务
    // REQUIRES_NEW: 事务自己的事务，调用的事务方法的事务被挂起。
    // 2. 使用 isolation 指定事务的隔离级别，最常用的取值为 READ_COMMITTED
    // 3. 默认情况下 Spring 的声明式事务对所有的运行时异常进行回滚。也可以通过对应的属性进行设置。通常情况下取默认值即可
    // 4. 使用 readOnly 指定事务是否为只读。
    // 这样可以帮助数据库引擎优化事务。若真的是一个只读数据库值的方法，应设置readOnly=true
    // 5. 使用 timeout 指定强制回滚之前事务可以占用的时间
    @Transactional(
//            propagation = Propagation.REQUIRED,
            propagation = Propagation.REQUIRES_NEW,
//            isolation = Isolation.DEFAULT,
            isolation = Isolation.READ_COMMITTED,
//            noRollbackFor = {UserAccountException.class},
            readOnly = false,
            timeout = 3
    )
    @Override
    public void purchase(String username, String isbn) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 1. 获取书的单价
        BigDecimal price = bookShopDAO.findBookPriceByIsbn(isbn);
        // 2. 获取书的库存
        bookShopDAO.updateBookStock(isbn);
        // 3. 更新用户余额
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
