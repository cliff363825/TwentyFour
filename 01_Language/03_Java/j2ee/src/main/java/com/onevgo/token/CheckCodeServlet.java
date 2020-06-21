package com.onevgo.token;

import com.onevgo.ValidateColorServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CheckCodeServlet extends HttpServlet {
    static final long serialVersionUID = 1L;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String checkCodeKey = request.getParameter("CHECK_CODE_KEY");
        String sessionCode = (String) request.getSession().getAttribute(ValidateColorServlet.CHECK_CODE_KEY);

        System.out.println(checkCodeKey + "-----" + sessionCode);
        if (checkCodeKey == null || !checkCodeKey.equalsIgnoreCase(sessionCode)) {
            response.sendRedirect(request.getContextPath() + "/token/code-fail.jsp");
            return;
        }

        request.getRequestDispatcher("/token/success.jsp").forward(request, response);
    }
}
