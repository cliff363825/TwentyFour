package com.onevgo.token;

import com.onevgo.TokenProcessor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenServlet extends HttpServlet {
    static final long serialVersionUID = 1L;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");

        /*
        String token = request.getParameter("token");
        HttpSession session = request.getSession();
        String sessionToken = (String) session.getAttribute("token");
        if (token == null || !token.equals(sessionToken)) {
            response.sendRedirect("/token/fail.jsp");
            return;
        }
        session.removeAttribute("token");
        */

        boolean valid = TokenProcessor.getInstance().isTokenValid(request);
        if (!valid) {
            response.sendRedirect("/token/fail.jsp");
            return;
        }
        TokenProcessor.getInstance().resetToken(request);

        System.out.println("name: " + name);
        request.getRequestDispatcher("/token/success.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
