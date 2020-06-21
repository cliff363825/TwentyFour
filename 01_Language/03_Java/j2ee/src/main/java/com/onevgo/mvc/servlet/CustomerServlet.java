package com.onevgo.mvc.servlet;

import com.onevgo.mvc.dao.CriteriaCustomer;
import com.onevgo.mvc.dao.CustomerDAO;
import com.onevgo.mvc.dao.factory.CustomerDAOFactory;
import com.onevgo.mvc.domain.Customer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

public class CustomerServlet extends HttpServlet {
    static final long serialVersionUID = 1L;
    private CustomerDAO customerDAO = CustomerDAOFactory.getInstance().getCustomerDAO();
    /*
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");

        if (method == null) {
            method = "query";
        }

        switch (method) {
            case "add":
                add(request, response);
                break;
            case "query":
                query(request, response);
                break;
            case "delete":
                delete(request, response);
                break;
            case "update":
                update(request, response);
                break;
        }
    }
    */

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletPath = request.getServletPath();
        String methodName = servletPath.substring(1, servletPath.length() - 3);
        try {
            Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "访问的页面不存在");
        }
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String idStr = request.getParameter("id");

        idStr = idStr != null ? idStr.trim() : "";

        if (idStr.equals("")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "参数错误");
            return;
        }

        int id = 0;
        try {
            id = Integer.parseInt(idStr);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "参数错误");
            return;
        }

        Customer customer = customerDAO.get(id);
        if (customer == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "customer不存在");
            return;
        }

        request.setAttribute("customer", customer);
        request.getRequestDispatcher(request.getContextPath() + "/mvc/editCustomer.jsp").forward(request, response);
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String idStr = request.getParameter("id");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");

        int id = 0;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "参数 id 错误");
            return;
        }

        Customer customer = customerDAO.get(id);
        if (customer == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "customer不存在");
            return;
        }
        String oldName = customer.getName();

        customer.setName(name);
        customer.setAddress(address);
        customer.setPhone(phone);

        request.setAttribute("customer", customer);

        if (oldName != null && !oldName.equalsIgnoreCase(name)) {
            long count = customerDAO.getCountWithName(name);
            if (count > 0) {
                request.setAttribute("message", "用户名" + name + "已存在");
                customer.setName(oldName);
                request.getRequestDispatcher(request.getContextPath() + "/mvc/editCustomer.jsp").forward(request, response);
                return;
            }
        }

        customerDAO.update(customer);
        response.sendRedirect("/query.do");
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idStr = request.getParameter("id");
        int id = 0;

        try {
            id = Integer.parseInt(idStr);
            customerDAO.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("/query.do");
    }

    private void query(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");

        List<Customer> customers = customerDAO.getForListWithCriteriaCustomer(new CriteriaCustomer(name, address, phone));

        request.setAttribute("customers", customers);

        request.getRequestDispatcher(request.getContextPath() + "/mvc/index.jsp").forward(request, response);
    }

    private void addCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");

        if (name == null || name.trim().equals("")
                || address == null || address.trim().equals("")
                || phone == null || phone.trim().equals("")) {
            request.setAttribute("message", "name, address, phone不能为空");
            request.getRequestDispatcher(request.getContextPath() + "/mvc/addCustomer.jsp").forward(request, response);
            return;
        }

        long count = customerDAO.getCountWithName(name);
        if (count > 0) {
            request.setAttribute("message", "用户名" + name + "已存在");
            request.getRequestDispatcher(request.getContextPath() + "/mvc/addCustomer.jsp").forward(request, response);
            return;
        }

        Customer customer = new Customer(name, address, phone);
        customerDAO.save(customer);

        response.sendRedirect("/query.do");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
