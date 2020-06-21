package com.onevgo.servlet;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends MyGenericServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("before init...");
        super.init(config);
        System.out.println("after init...");
    }

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String initUsername = getServletContext().getInitParameter("username");
        String initPassword = getServletContext().getInitParameter("password");

        PrintWriter out = response.getWriter();

        if (initUsername != null && initUsername.equals(username) &&
                initPassword != null && initPassword.equals(password)) {
            out.println("Hello, " + username);
        } else {
            out.println("Sorry");
        }
    }
}
