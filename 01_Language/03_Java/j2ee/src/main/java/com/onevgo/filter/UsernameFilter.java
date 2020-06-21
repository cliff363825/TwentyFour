package com.onevgo.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class UsernameFilter implements Filter {
    private FilterConfig config;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.config = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String initUsername = config.getInitParameter("username");
        String username = request.getParameter("username");

        if (!initUsername.equals(username)) {
            request.setAttribute("message", "用户名不正确");
            request.getRequestDispatcher(((HttpServletRequest) request).getContextPath() + "/filter/login.jsp").forward(request, response);
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
