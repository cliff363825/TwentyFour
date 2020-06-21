package com.onevgo.servlet;

import javax.servlet.*;
import java.io.IOException;
import java.util.Enumeration;

abstract public class MyGenericServlet implements Servlet, ServletConfig {
    private ServletConfig config;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.config = config;
    }

    @Override
    public ServletConfig getServletConfig() {
        return config;
    }

    abstract public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException;

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }

    @Override
    public String getServletName() {
        return getServletConfig().getServletName();
    }

    @Override
    public ServletContext getServletContext() {
        return getServletConfig().getServletContext();
    }

    @Override
    public String getInitParameter(String name) {
        return getServletConfig().getInitParameter(name);
    }

    @Override
    public Enumeration<String> getInitParameterNames() {
        return getServletConfig().getInitParameterNames();
    }
}
