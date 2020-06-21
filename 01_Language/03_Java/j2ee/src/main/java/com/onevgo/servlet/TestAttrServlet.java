package com.onevgo.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class TestAttrServlet extends HttpServlet {
    static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();

        // 1. 在 Servlet 中无法得到 pageContext 对象

        // 2. request
        Object requestAttr = request.getAttribute("requestAttr");
        out.write("requestAttr: " + requestAttr + "<br>");

        // 3. session
        Object sessionAttr = request.getSession().getAttribute("sessionAttr");
        out.write("sessionAttr: " + sessionAttr + "<br>");

        // 4. application
        Object applicationAttr = getServletContext().getAttribute("applicationAttr");
        out.write("application: " + applicationAttr + "<br>");
    }
}
