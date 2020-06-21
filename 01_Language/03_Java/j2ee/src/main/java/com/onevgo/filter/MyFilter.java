package com.onevgo.filter;

import javax.servlet.*;
import java.io.IOException;

public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String name = filterConfig.getInitParameter("name");
        System.out.println("MyFilter.init: name => " + name);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("MyFilter.doFilter: beforeDoFilter");
        chain.doFilter(request, response);
        System.out.println("MyFilter.doFilter: afterDoFilter");
    }

    @Override
    public void destroy() {
        System.out.println("MyFilter.destroy");
    }
}
