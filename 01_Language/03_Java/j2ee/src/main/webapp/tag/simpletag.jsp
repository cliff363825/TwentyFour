<%@ page import="java.util.List" %>
<%@ page import="com.onevgo.Customer" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title1</title>
</head>
<body>
<%
    List<Customer> customers = new ArrayList<>();
    customers.add(new Customer(1, "AA", 11));
    customers.add(new Customer(2, "BB", 22));
    customers.add(new Customer(3, "CC", 33));
    customers.add(new Customer(4, "DD", 44));
    customers.add(new Customer(5, "EE", 55));

    request.setAttribute("customers", customers);
%>

<jsp:forward page="testtag.jsp"/>

</body>
</html>
