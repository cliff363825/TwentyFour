package com.onevgo.mvc.dao;

import cn.hutool.db.handler.BeanHandler;
import cn.hutool.db.handler.BeanListHandler;
import cn.hutool.db.handler.RsHandler;
import cn.hutool.db.sql.SqlExecutor;
import com.onevgo.mvc.db.JdbcUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * 封装了基本的CRUD的方法，以供子类继承使用
 * 当前DAO直接在方法中获取数据库连接
 *
 * @param <T> 当前DAO处理的实体类的类型是什么
 */
public class DAO<T> {

    private Class<T> clazz;

    public DAO() {
        Type genericSuperclass = getClass().getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;

            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            if (actualTypeArguments != null && actualTypeArguments.length > 0) {
                clazz = (Class<T>) actualTypeArguments[0];
            }
        }
    }

    /**
     * 返回某一个字段的值，例如返回某一条记录的customerName, 或返回数据表中有多少条记录等
     *
     * @param sql
     * @param args
     * @param <E>
     * @return
     */
    public <E> E getForValue(String sql, Object... args) {
        Connection connection = null;
        try {
            connection = JdbcUtils.getConnection();
            return SqlExecutor.query(connection, sql, new RsHandler<E>() {
                @Override
                public E handle(ResultSet rs) throws SQLException {
                    return rs.next() ? (E) rs.getObject(1) : null;
                }
            }, args);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.releaseConnection(connection);
        }

        return null;
    }

    /**
     * 返回T所对应的List
     *
     * @param sql
     * @param args
     * @return
     */
    public List<T> getForList(String sql, Object... args) {
        Connection connection = null;
        try {
            connection = JdbcUtils.getConnection();
            return SqlExecutor.query(connection, sql, new BeanListHandler<>(clazz), args);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.releaseConnection(connection);
        }

        return null;
    }

    /**
     * 返回对应的T的一个实体类的对象
     *
     * @param sql
     * @param args
     * @return
     */
    public T get(String sql, Object... args) {
        Connection connection = null;
        try {
            connection = JdbcUtils.getConnection();
            return SqlExecutor.query(connection, sql, new BeanHandler<>(clazz), args);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.releaseConnection(connection);
        }

        return null;
    }

    /**
     * 该方法封装了INSERT DELETE UPDATE操作
     *
     * @param sql  SQL语句
     * @param args 填充SQL语句的占位符
     */
    public void update(String sql, Object... args) {
        Connection connection = null;
        try {
            connection = JdbcUtils.getConnection();
            SqlExecutor.execute(connection, sql, args);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.releaseConnection(connection);
        }
    }
}
