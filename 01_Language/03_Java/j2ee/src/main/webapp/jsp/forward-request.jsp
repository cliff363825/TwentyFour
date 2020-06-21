<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>RequestDispatcher.forward</title>
</head>
<body>
<h1>RequestDispatcher.forward...start</h1>
<%
    request.setAttribute("name", "forward-request.jsp");
    request.getRequestDispatcher(request.getContextPath() + "/jsp/forward-result.jsp").forward(request, response);
    System.out.println("RequestDispatcher.forward 执行了 ...");
%>
<h1>RequestDispatcher.forward...end</h1>
</body>
</html>
