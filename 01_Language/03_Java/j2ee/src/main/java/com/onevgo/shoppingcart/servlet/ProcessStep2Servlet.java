package com.onevgo.shoppingcart.servlet;

import com.onevgo.shoppingcart.bean.Customer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ProcessStep2Servlet extends HttpServlet {
    static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String cardType = request.getParameter("cardType");
        String card = request.getParameter("card");

        Customer customer = new Customer(name, address, cardType, card);

        HttpSession session = request.getSession();
        session.setAttribute("customer", customer);

        response.sendRedirect(request.getContextPath() + "/shoppingcart/confirm.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
