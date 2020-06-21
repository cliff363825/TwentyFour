package com.onevgo.mvc.db;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JdbcUtils {
    private static DataSource dataSource = new ComboPooledDataSource("mvc");

    public static void releaseConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 返回数据源的一个Connection对象
     *
     * @return
     */
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
