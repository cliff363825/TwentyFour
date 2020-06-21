<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isErrorPage="true" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
<h1>Error</h1>
<%
    if (exception != null) {
        out.println("Error message: " + exception.getMessage());
    }
%>
</body>
</html>
