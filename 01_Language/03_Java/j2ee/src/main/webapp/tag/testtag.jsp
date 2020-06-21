<%@ page import="java.util.List" %>
<%@ page import="com.onevgo.Customer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title2</title>
</head>
<body>

<c:forEach items="${requestScope.customers}" var="customer">
    ${customer.id}, ${customer.name}, ${customer.age} <br>
</c:forEach>

<%
    List<Customer> customers = (List<Customer>) request.getAttribute("customers");

    if (customers != null) {
        for (Customer customer : customers) {
%>
<%= customer.getId()%>, <%=customer.getName()%>, <%=customer.getAge()%> <br>
<%
        }
    }
%>
</body>
</html>
