<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>jsp:forward</title>
</head>
<body>
<h1>jsp:forward...start</h1>
<%
    request.setAttribute("name", "forward-jsp");
%>
<jsp:forward page="forward-result.jsp" />
<%
    System.out.println("jsp:forward 执行不到这里...");
%>
<h1>jsp:forward...end</h1>
</body>
</html>
