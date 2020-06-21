package com.onevgo.servlet;

import cn.hutool.db.DbUtil;
import cn.hutool.db.handler.EntityHandler;
import cn.hutool.db.sql.SqlExecutor;
import org.springframework.util.CollectionUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Map;

public class Login3Servlet extends HttpServlet {
    static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String sql = "select * from user where username = ? and password = ? limit 1";

        Connection connection = null;
        Map<String, Object> user = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test");
            user = SqlExecutor.query(connection, sql, new EntityHandler(), username, password);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtil.close(connection);
        }

        PrintWriter out = response.getWriter();

        if (CollectionUtils.isEmpty(user)) {
            out.println("Sorry");
        } else {
            out.println("Hello, " + username + ", ID: " + user.get("id"));
        }
    }
}
