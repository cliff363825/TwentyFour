<%@ page import="com.onevgo.listener.Customer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    Customer customer = new Customer();
//    request.setAttribute("customer", customer);
    session.setAttribute("customer", customer);
    session.removeAttribute("customer");
%>
</body>
</html>
