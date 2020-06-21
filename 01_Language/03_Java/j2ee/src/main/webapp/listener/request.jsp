<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    request.setAttribute("requestKey", "requestValue");
//    response.sendRedirect("test.jsp");
%>
<jsp:forward page="test.jsp"/>
</body>
</html>
