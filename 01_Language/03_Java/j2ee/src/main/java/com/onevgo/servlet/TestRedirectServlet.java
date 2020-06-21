package com.onevgo.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TestRedirectServlet extends HttpServlet {
    static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("TestRedirectServlet.doGet ... 1");

        request.setAttribute("from", "test-redirect");
        response.sendRedirect(request.getContextPath() + "/test-forward-redirect-result");

        System.out.println("TestRedirectServlet.doGet ... 2");
    }
}
