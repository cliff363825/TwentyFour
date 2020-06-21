package com.onevgo.filter;

import javax.servlet.*;
import java.io.IOException;

public class SecondFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("SecondFilter.init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("SecondFilter.doFilter: beforeDoFilter");
        chain.doFilter(request, response);
        System.out.println("SecondFilter.doFilter: afterDoFilter");
    }

    @Override
    public void destroy() {
        System.out.println("SecondFilter.destroy");
    }
}
