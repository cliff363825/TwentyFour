<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cookie</title>
</head>
<body>
<%
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie1 : cookies) {
            out.println(cookie1.getName() + ":" + cookie1.getValue());
        }
    } else {
        out.print("没有一个cookie");
        Cookie cookie = new Cookie("name", "onevgo.com");
        cookie.setMaxAge(30);
        response.addCookie(cookie);
    }
%>
</body>
</html>
