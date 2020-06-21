package com.onevgo.auth;

import cn.hutool.core.util.StrUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public class LoginServlet extends HttpServlet {
    static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String methodName = request.getParameter("method");

        if (StrUtil.isEmpty(methodName)) {
            return;
        }

        try {
            Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private UserDAO userDAO = new UserDAO();

    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");

        if (StrUtil.isEmpty(name)) {
            return;
        }

        User user = userDAO.getUser(name);
        if (user == null) {
            return;
        }

        request.getSession().setAttribute("user", user);
        response.sendRedirect(request.getContextPath() + "/auth/articles.jsp");
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().invalidate();

        response.sendRedirect(request.getContextPath() + "/auth/login.jsp");
    }
}
