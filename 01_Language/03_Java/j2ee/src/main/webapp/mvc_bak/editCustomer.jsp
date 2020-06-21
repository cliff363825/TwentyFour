<%@ page import="com.onevgo.mvc.domain.Customer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Customer</title>
</head>
<body>
<%
    String id = null;
    String name = null;
    String address = null;
    String phone = null;

    Customer customer = (Customer) request.getAttribute("customer");
    if (customer != null) {
        id = customer.getId() + "";
        name = customer.getName();
        address = customer.getAddress();
        phone = customer.getPhone();
    }
%>
<p><%=request.getAttribute("message") == null ? "" : request.getAttribute("message")%></p>
<form action="update.do" method="post">
    <table>
        <tr>
            <td>Name:</td>
            <td><input type="text" name="name" value="<%=name%>"></td>
        </tr>

        <tr>
            <td>Address:</td>
            <td><input type="text" name="address" value="<%=address%>"></td>
        </tr>

        <tr>
            <td>Phone:</td>
            <td><input type="text" name="phone" value="<%=phone%>"></td>
        </tr>

        <tr>
            <td><input type="submit" value="submit"></td>
            <td>
                <input type="hidden" name="id" value="<%=id%>">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
