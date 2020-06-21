<%@ page import="com.onevgo.Customer" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="el.jsp" method="post">
    Username: <input type="text" name="username"
                     value="<%=request.getParameter("username") == null ? "" : request.getParameter("username")%>">
    Username: <input type="text" name="username" value="${param.username}">
    <input type="submit" value="Submit">
</form>

<br>
username: ${param.username}
<%
    String username = request.getParameter("username");
    if (username == null) {
        username = "";
    }
    out.print(username);
%>
<br>

<jsp:useBean id="customer" class="com.onevgo.Customer" scope="session"/>
<jsp:setProperty name="customer" property="age" value="10"/>

age:
<%
    Customer customer1 = (Customer) session.getAttribute("customer");
    if (customer1 != null) {
        out.print(customer1.getAge());
    }
%>
<br>

<%
    application.setAttribute("time", new Date());
%>

<a href="el2.jsp?score=89">To el2</a>
<br>
<a href="el2.jsp?name=A&name=B&name=C">To el2</a>
<br>
</body>
</html>
