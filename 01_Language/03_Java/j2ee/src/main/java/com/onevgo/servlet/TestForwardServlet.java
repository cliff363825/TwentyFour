package com.onevgo.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TestForwardServlet extends HttpServlet {
    static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("TestForwardServlet.doGet ... 1");
        request.setAttribute("from", "test-forward");

        //请求的转发.
        //1. 调用 HttpServletRequest 的 getRequestDispatcher() 方法获取  RequestDispatcher 对象
        //调用 getRequestDispatcher() 需要传入要转发的地址
        String path = request.getContextPath() + "/test-forward-redirect-result";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);

        //2. 调用 HttpServletRequest 的 forward(request, response) 进行请求的转发.
        requestDispatcher.forward(request, response);
        System.out.println("TestForwardServlet.doGet ... 2");
    }
}
