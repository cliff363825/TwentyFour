<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Session</title>
</head>
<body>
<%=session.getId()%>
<%
    Cookie cookie = new Cookie("JSESSIONID", session.getId());
    cookie.setMaxAge(30);
    cookie.setPath("/");
    response.addCookie(cookie);
%>
</body>
</html>
