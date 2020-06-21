<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String message = null;
    if ("POST".equalsIgnoreCase(request.getMethod())) {
        String username = request.getParameter("username");
        if (username != null && username.trim().length() != 0) {
            session.setAttribute("username", username);
            response.sendRedirect("list.jsp");
            return;
        } else {
            message = "请输入用户名";
        }
    }
%>
<html>
<head>
    <title>Login</title>
</head>
<body>
<%
    if (message != null) {
        out.print("<p>" + message + "</p>");
    }
%>
<form action="" method="post">
    Username: <input type="text" name="username">
    <input type="submit" value="Submit">
</form>
</body>
</html>
