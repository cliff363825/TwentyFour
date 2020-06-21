package com.onevgo.shoppingcart.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProcessStep1Servlet extends HttpServlet {
    static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] books = request.getParameterValues("book");

        request.getSession().setAttribute("books", books);

        response.sendRedirect(request.getContextPath() + "/shoppingcart/step-2.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
