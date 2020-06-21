package com.onevgo.bookstore.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import cn.hutool.db.Db;
import cn.hutool.db.DbUtil;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.onevgo.bookstore.exception.DBException;

/**
 * JDBC 的工具类
 */
public class JDBCUtils {

    private static DataSource dataSource = null;

    static {
        InputStream inputStream = null;
        Properties properties = new Properties();
        try {
            inputStream = JDBCUtils.class.getResourceAsStream("/druid.properties");
            properties.load(inputStream);
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 获取数据库连接
     *
     * @return
     */
    public static Connection getConnection() {
        try {
            return Db.use(dataSource).getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("数据库连接错误!");
        }
    }

    /**
     * 关闭数据库连接
     *
     * @param connection
     */
    public static void release(Connection connection) {
        DbUtil.close(connection);
    }

    /**
     * 关闭数据库连接
     *
     * @param rs
     * @param statement
     */
    public static void release(ResultSet rs, Statement statement) {
        DbUtil.close(rs, statement);
    }
}
