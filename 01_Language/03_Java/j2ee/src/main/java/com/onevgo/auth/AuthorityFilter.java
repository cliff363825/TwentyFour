package com.onevgo.auth;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AuthorityFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String servletPath = httpServletRequest.getServletPath();

        List<String> uncheckedUrls = Arrays.asList("/auth/403.jsp", "/auth/articles.jsp", "/auth/authority-manager.jsp", "/auth/login.jsp", "/auth/logout.jsp");
        if (uncheckedUrls.contains(servletPath)) {
            chain.doFilter(request, response);
            return;
        }

        User user = (User) httpServletRequest.getSession().getAttribute("user");
        if (user == null) {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/auth/login.jsp");
            return;
        }

        List<Authority> authorities = user.getAuthorities();
        if (authorities != null) {
            for (Authority authority : authorities) {
                if (authority != null && authority.getUrl() != null && authority.getUrl().equals(servletPath)) {
                    chain.doFilter(request, response);
                    return;
                }
            }
        }

        httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/auth/403.jsp");
    }

    @Override
    public void destroy() {

    }
}
