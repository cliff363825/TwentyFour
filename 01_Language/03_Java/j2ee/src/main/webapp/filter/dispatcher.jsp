<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="test.jsp" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>dispatcher</h1>
<%--<jsp:forward page="/test.jsp" />--%>
<%--<jsp:include page="/test.jsp" />--%>
<%
    int a = 1 / 0;
%>
</body>
</html>
