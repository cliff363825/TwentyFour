<%@ page import="com.onevgo.listener.Customer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    Object customer = session.getAttribute("customer");
    if (customer == null) {
        customer = new Customer();
        session.setAttribute("customer", customer);
        System.out.println("new customer = " + customer);
    } else {
        System.out.println("load customer = " + customer);
    }
%>
</body>
</html>
