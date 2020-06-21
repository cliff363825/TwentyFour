<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/login-servlet?method=login" method="post">
    name: <input type="text" name="name">
    <input type="submit" value="Submit">
</form>
</body>
</html>
