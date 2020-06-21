package com.onevgo.filter;

import com.onevgo.context.ConnectionContext;
import com.onevgo.mvc.db.JdbcUtils;

import javax.servlet.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class TransactionFilter implements Filter {
    private FilterConfig config;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        config = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Connection connection = null;

        try {
            connection = JdbcUtils.getConnection();
            connection.setAutoCommit(false);
            ConnectionContext.getInstance().bind(connection);

            chain.doFilter(request, response);

            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();

            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        } finally {
            ConnectionContext.getInstance().remove();
            JdbcUtils.releaseConnection(connection);
        }
    }

    @Override
    public void destroy() {

    }
}
