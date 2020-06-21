package com.onevgo.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EncodingFilter extends HttpFilter {
    private String encoding;

    @Override
    protected void init() {
        super.init();
        encoding = getFilterConfig().getInitParameter("encoding");
    }

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (encoding != null) {
            request.setCharacterEncoding(encoding);
        }

        chain.doFilter(request, response);
    }
}
