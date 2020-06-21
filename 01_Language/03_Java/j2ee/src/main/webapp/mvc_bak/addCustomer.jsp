<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Customer</title>
</head>
<body>
<p><%=request.getAttribute("message") == null ? "" : request.getAttribute("message")%></p>
<form action="addCustomer.do" method="post">
    <table>
        <tr>
            <td>Name:</td>
            <td><input type="text" name="name" value="<%=request.getParameter("name") == null ? "" : request.getParameter("name")%>"></td>
        </tr>

        <tr>
            <td>Address:</td>
            <td><input type="text" name="address" value="<%=request.getParameter("address") == null ? "" : request.getParameter("address")%>"></td>
        </tr>

        <tr>
            <td>Phone:</td>
            <td><input type="text" name="phone" value="<%=request.getParameter("phone") == null ? "" : request.getParameter("phone")%>"></td>
        </tr>

        <tr>
            <td><input type="submit" value="submit"></td>
            <td></td>
        </tr>
    </table>
</form>
</body>
</html>
