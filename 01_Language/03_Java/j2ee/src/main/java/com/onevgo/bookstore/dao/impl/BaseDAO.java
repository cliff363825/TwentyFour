package com.onevgo.bookstore.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import cn.hutool.db.handler.BeanHandler;
import cn.hutool.db.handler.BeanListHandler;
import cn.hutool.db.handler.RsHandler;
import cn.hutool.db.sql.SqlExecutor;
import com.onevgo.bookstore.dao.Dao;
import com.onevgo.bookstore.utils.ReflectionUtils;
import com.onevgo.context.ConnectionContext;

public class BaseDAO<T> implements Dao<T> {

    private Class<T> clazz;

    public BaseDAO() {
        clazz = ReflectionUtils.getSuperGenericType(getClass());
    }

    @Override
    public long insert(String sql, Object... args) {
        long id = 0;

        Connection connection = null;
        try {
            connection = ConnectionContext.getInstance().get();
            id = SqlExecutor.executeForGeneratedKey(connection, sql, args);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return id;
    }

    @Override
    public void update(String sql, Object... args) {
        Connection connection = null;

        try {
            connection = ConnectionContext.getInstance().get();
            SqlExecutor.execute(connection, sql, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public T query(String sql, Object... args) {
        Connection connection = null;

        try {
            connection = ConnectionContext.getInstance().get();
            return SqlExecutor.query(connection, sql, new BeanHandler<>(clazz), args);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<T> queryForList(String sql, Object... args) {
        Connection connection = null;

        try {
            connection = ConnectionContext.getInstance().get();
            return SqlExecutor.query(connection, sql, new BeanListHandler<>(clazz), args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public <V> V getSingleVal(String sql, Object... args) {
        Connection connection = null;

        try {
            connection = ConnectionContext.getInstance().get();
            return SqlExecutor.query(connection, sql, new RsHandler<V>() {
                @Override
                public V handle(ResultSet rs) throws SQLException {
                    return rs.next() ? (V) rs.getObject(1) : null;
                }
            }, args);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void batch(String sql, Object[]... params) {
        Connection connection = null;

        try {
            connection = ConnectionContext.getInstance().get();
            SqlExecutor.executeBatch(connection, sql, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
