package com.onevgo.servlet;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;

public class TestServletContextServlet implements Servlet {
    private ServletConfig config;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.config = config;
    }

    @Override
    public ServletConfig getServletConfig() {
        return config;
    }

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");

        ServletContext application = getServletConfig().getServletContext();
        PrintWriter out = response.getWriter();

        String driver = application.getInitParameter("driver");
        out.write("driver = " + driver + "<br>");

        Enumeration<String> initParameterNames = application.getInitParameterNames();
        while (initParameterNames.hasMoreElements()) {
            String s = initParameterNames.nextElement();
            out.write(s + " = " + application.getInitParameter(s) + "<br>");
        }

        String realPath = application.getRealPath("/");
        out.write("realPath = " + realPath + "<br>");

        String contextPath = application.getContextPath();
        out.write("contextPath = " + contextPath + "<br>");

        try {
            URL resource = application.getResource("/");
            out.write("resource = " + resource + "<br>");
//            InputStream inputStream = servletContext.getResourceAsStream("/");
//            System.out.println("inputStream = " + inputStream);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
