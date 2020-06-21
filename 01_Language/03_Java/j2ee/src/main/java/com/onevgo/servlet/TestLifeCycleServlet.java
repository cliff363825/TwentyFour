package com.onevgo.servlet;

import javax.servlet.*;
import java.io.IOException;

public class TestLifeCycleServlet implements Servlet {
    public TestLifeCycleServlet() {
        System.out.println("TestLifeCycleServlet.TestLifeCycleServlet: " + "构造器：只被调用一次。只有第一次请求 Servlet 时，创建 Servlet 的实例。调用构造器。这说明 Servlet 是单实例的！");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("TestLifeCycleServlet.init: " + "方法：只被调用一次。在创建好实例后立即被调用。用于初始化当前 Servlet。");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        System.out.println("TestLifeCycleServlet.service: " + "方法：被多次调用。每次请求都会调用 service 方法。实际用于响应请求的。");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("TestLifeCycleServlet.destroy: " + "方法：只被调用一次。在当前 Servlet 所在的 WEB 应用被卸载前调用。用于释放当前 Servlet 所占用的资源。");
    }
}
