package com.onevgo.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NoCacheFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("NoCacheFilter.doFilter");
        response.setHeader("Cache-Control", "max-age=0");
        // If you're serving to IE 9, then the following may be needed
        response.setHeader("Cache-Control", "max-age=1");

        // If you're serving to IE over SSL, then the following may be needed
        response.setDateHeader("Expires", 0L); // Date in the past
        response.setDateHeader("Last-Modified", System.currentTimeMillis()); // always modified
        response.setHeader("Cache-Control", "cache, must-revalidate"); // HTTP/1.1
        response.setHeader("Pragma", "public"); // HTTP/1.0

        chain.doFilter(request, response);
    }
}
