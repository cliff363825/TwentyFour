package com.onevgo.springmvc.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;

public class Tomcat8HiddenMethodFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String requestMethod = httpServletRequest.getMethod();
        if (requestMethod != null && (requestMethod.equalsIgnoreCase("put") || requestMethod.equalsIgnoreCase("delete"))) {
            HttpServletRequestWrapper httpServletRequestWrapper = new HttpServletRequestWrapper(httpServletRequest) {
                @Override
                public String getMethod() {
                    return "POST";
                }
            };

            chain.doFilter(httpServletRequestWrapper, response);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
