<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.net.URLDecoder" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Index</title>
</head>
<body>
<%
    String name = request.getParameter("name");
    Cookie cookie = null;
    if (name != null && !name.trim().equals("")) {
        cookie = new Cookie("name", URLEncoder.encode(name, "UTF-8"));
        cookie.setMaxAge(30);
        response.addCookie(cookie);
    } else {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie c : cookies) {
                if (c.getName() != null && c.getName().equals("name")) {
                    cookie = c;
                }
            }
        }
    }

    if (cookie == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    out.println("Hello: " + URLDecoder.decode(cookie.getValue(), "UTF-8") + ", expire: " + cookie.getMaxAge());
%>
</body>
</html>
