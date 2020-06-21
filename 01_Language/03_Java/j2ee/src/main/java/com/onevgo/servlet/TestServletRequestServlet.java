package com.onevgo.servlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

public class TestServletRequestServlet implements Servlet {
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

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String[] interesting = request.getParameterValues("interesting");

        out.write("<h1>hello, " + username + "</h1><br>");
        out.write("username = " + username + ", password = " + password +
                ", interesting = " + Arrays.toString(interesting) + "<br>");

        Map<String, String[]> parameterMap = request.getParameterMap();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            out.write(entry.getKey() + " = " + Arrays.toString(entry.getValue()) + "<br>");
        }

        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String s = parameterNames.nextElement();
            out.write(s + " = " + request.getParameter(s) + "<br>");
        }

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        out.write("method = " + httpServletRequest.getMethod() + "<br>");
        out.write("scheme = " + httpServletRequest.getScheme() + "<br>");
        out.write("serverName = " + httpServletRequest.getServerName() + "<br>");
        out.write("localAddr = " + httpServletRequest.getLocalAddr() + "<br>");
        out.write("localPort = " + httpServletRequest.getLocalPort() + "<br>");
        out.write("contextPath = " + httpServletRequest.getContextPath() + "<br>");
        out.write("servletPath = " + httpServletRequest.getServletPath() + "<br>");
        out.write("pathInfo = " + httpServletRequest.getPathInfo() + "<br>");
        out.write("queryString = " + httpServletRequest.getQueryString() + "<br>");
        out.write("protocol = " + httpServletRequest.getProtocol() + "<br>");

        out.write("remoteHost = " + httpServletRequest.getRemoteHost() + "<br>");
        out.write("remoteAddr = " + httpServletRequest.getRemoteAddr() + "<br>");
        out.write("remotePort = " + httpServletRequest.getRemotePort() + "<br>");

        out.write("requestUri = " + httpServletRequest.getRequestURI() + "<br>");
        out.write("requestUrl = " + httpServletRequest.getRequestURL() + "<br>");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
