package com.onevgo.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class LoginFilter extends HttpFilter {
    private String excludeUrls;

    @Override
    protected void init() {
        super.init();
        excludeUrls = getFilterConfig().getInitParameter("excludeUrls");
    }

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (excludeUrls != null) {
            List<String> excludeUrlList = Arrays.asList(excludeUrls.split(","));
            if (!excludeUrlList.contains(request.getServletPath())) {
                if (request.getSession().getAttribute("username") == null) {
                    response.sendRedirect("/login/login.jsp");
                    return;
                }
            }
        }

        chain.doFilter(request, response);
    }
}
