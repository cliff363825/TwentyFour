package com.onevgo.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Login2Servlet extends MyHttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/login.html").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        String initUsername = getServletContext().getInitParameter("username");
        String initPassword = getServletContext().getInitParameter("password");

        PrintWriter out = res.getWriter();

        if (initUsername != null && initUsername.equals(username) &&
                initPassword != null && initPassword.equals(password)) {
            out.println("Hello, " + username);
        } else {
            out.println("Sorry");
        }
    }
}
