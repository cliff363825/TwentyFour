package com.onevgo.servlet;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

public class TestServletConfigServlet implements Servlet {
    private ServletConfig config;

    @Override
    public void init(ServletConfig config) throws ServletException {
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
        ServletConfig config = getServletConfig();

        String user = config.getInitParameter("user");
        out.write("user = " + user + "<br>");

        Enumeration<String> parameterNames = config.getInitParameterNames();
        while (parameterNames.hasMoreElements()) {
            String s = parameterNames.nextElement();
            out.write(s + " = " + config.getInitParameter(s) + "<br>");
        }

        String servletName = config.getServletName();
        out.write("servletName = " + servletName);
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
