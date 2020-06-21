<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cookie Path</title>
</head>
<body>
<%
    Cookie cookie = new Cookie("cookiePath", "cookieValue");
    cookie.setPath(request.getContextPath() + "/cookie");
    response.addCookie(cookie);
%>
<a href="../path.jsp">To read cookie.</a>
</body>
</html>
