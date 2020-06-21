package com.onevgo.mvc;

import com.onevgo.mvc.db.JdbcUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class TestJdbcUtils {
    @Test
    public void testGetConnection() {
        try {
            Connection connection = JdbcUtils.getConnection();
            System.out.println(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
