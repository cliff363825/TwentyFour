package com.onevgo.filter;

import com.onevgo.MyHttpServletRequest;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ContentFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        MyHttpServletRequest servletRequest = new MyHttpServletRequest(request);
        chain.doFilter(servletRequest, response);
    }
}
