package com.onevgo.auth;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class AuthorityServlet extends HttpServlet {
    static final long serialVersionUID = 1L;

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

    public void getAuthorities(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");

        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUser(username);
        request.setAttribute("user", user);
        request.setAttribute("authorities", userDAO.getAuthorities());

        request.getRequestDispatcher(request.getContextPath() + "/auth/authority-manager.jsp")
                .forward(request, response);
    }

    public void updateAuthority(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String[] urls = request.getParameterValues("url");

        if (StrUtil.isEmpty(username) || ArrayUtil.isEmpty(urls)) {
            return;
        }

        UserDAO userDAO = new UserDAO();
        List<Authority> allAuthorities = userDAO.getAuthorities();

        List<Authority> authorities = new ArrayList<>();
        for (String url : urls) {
            for (Authority authority : allAuthorities) {
                if (authority.getUrl().equals(url)) {
                    authorities.add(authority);
                }
            }
        }

        userDAO.update(username, authorities);
        response.sendRedirect(request.getContextPath() + "/auth/authority-manager.jsp");
    }
}
