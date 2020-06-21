package com.onevgo.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class PasswordFilter implements Filter {
    private FilterConfig config;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.config = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String initPassword = config.getServletContext().getInitParameter("password");
        String password = request.getParameter("password");

        if (!initPassword.equals(password)) {
            request.setAttribute("message", "密码不正确");
            request.getRequestDispatcher(((HttpServletRequest) request).getContextPath() + "/filter/login.jsp").forward(request, response);
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
