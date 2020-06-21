package com.onevgo.servlet;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

public class TestLoadOnStartupServlet implements Servlet {
    private ServletConfig config;

    public TestLoadOnStartupServlet() {
        System.out.println("TestLoadOnStartupServlet.TestLoadOnStartupServlet");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("TestLoadOnStartupServlet.init");
        this.config = config;
    }

    @Override
    public ServletConfig getServletConfig() {
        return this.config;
    }

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write("TestLoadOnStartupServlet.service");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("TestLoadOnStartupServlet.destroy");
    }
}
