package com.onevgo.upload.db;

import cn.hutool.db.handler.BeanHandler;
import cn.hutool.db.handler.BeanListHandler;
import cn.hutool.db.handler.RsHandler;
import cn.hutool.db.sql.SqlExecutor;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DAO<T> {

    private Class<T> clazz;

    public DAO() {

        Type type = getClass().getGenericSuperclass();

        if (type instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) type;

            Type[] parameterArgs = pt.getActualTypeArguments();

            if (parameterArgs != null && parameterArgs.length > 0) {
                clazz = (Class<T>) parameterArgs[0];
            }
        }

    }

    protected void update(Connection conn, String sql, Object... args) throws SQLException {
        SqlExecutor.execute(conn, sql, args);
    }

    protected T get(Connection conn, String sql, Object... args) throws SQLException {
        return SqlExecutor.query(conn, sql, new BeanHandler<>(clazz), args);
    }

    protected List<T> getForList(Connection conn, String sql, Object... args) throws SQLException {
        return SqlExecutor.query(conn, sql, new BeanListHandler<>(clazz), args);
    }

    protected <E> E getValue(Connection conn, String sql, Object... args) throws SQLException {
        return SqlExecutor.query(conn, sql, new RsHandler<E>() {
            @Override
            public E handle(ResultSet rs) throws SQLException {
                return rs.next() ? (E) rs.getObject(1) : null;
            }
        }, args);
    }

}
