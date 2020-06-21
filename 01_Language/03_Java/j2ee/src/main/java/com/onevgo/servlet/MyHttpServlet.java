package com.onevgo.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

abstract public class MyHttpServlet extends MyGenericServlet {
    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        HttpServletRequest httpServletRequest;
        HttpServletResponse httpServletResponse;
        if (!(request instanceof HttpServletRequest) || !(response instanceof HttpServletResponse)) {
            throw new ServletException("非HTTP请求");
        }
        httpServletRequest = (HttpServletRequest) request;
        httpServletResponse = (HttpServletResponse) response;

        service(httpServletRequest, httpServletResponse);
    }

    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String method = request.getMethod();

        if ("GET".equalsIgnoreCase(method)) {
            doGet(request, response);
        } else if ("POST".equalsIgnoreCase(method)) {
            doPost(request, response);
        } else {
            throw new ServletException("不支持的请求方式：" + method);
        }
    }

    abstract public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    abstract public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
