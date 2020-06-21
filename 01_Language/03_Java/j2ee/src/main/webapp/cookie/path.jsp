<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cookie Path</title>
</head>
<body>
<%
    String cookieValue = null;
    Cookie[] cookies = request.getCookies();
    if (cookies != null && cookies.length > 0) {
        for (Cookie cookie : cookies) {
            if ("cookiePath".equals(cookie.getName())) {
                cookieValue = cookie.getValue();
            }
        }
    }

    if (cookieValue != null) {
        out.println(cookieValue);
    } else {
        out.println("没有指定的Cookie.");
    }
%>
</body>
</html>
