<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    application.setAttribute("ServletContextKey", "ServletContextValue");
    application.setAttribute("ServletContextKey", "ServletContextValue1");
    application.removeAttribute("ServletContextKey");
%>
</body>
</html>
