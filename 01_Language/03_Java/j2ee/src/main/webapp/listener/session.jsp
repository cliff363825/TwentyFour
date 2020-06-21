<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    HttpSession session = request.getSession();
    session.setMaxInactiveInterval(10);
%>
</body>
</html>
