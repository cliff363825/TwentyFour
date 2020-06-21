<%@ page import="java.util.List" %>
<%@ page import="com.onevgo.mvc.domain.Customer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customer List</title>
    <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
</head>
<body>
<form action="query.do" method="post">
    <table>
        <tr>
            <td>CustomerName:</td>
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
            <td><input type="submit" value="查詢"></td>
            <td><a href="addCustomer.jsp">Add Customer</a></td>
        </tr>
    </table>
</form>

<br><br>

<%
    List<Customer> customers = (List<Customer>) request.getAttribute("customers");
    if (customers != null && customers.size() > 0) {
%>
<table>
    <tr>
        <th>ID</th>
        <th>CustomerName</th>
        <th>Address</th>
        <th>Phone</th>
        <th>Update/Delete</th>
    </tr>
    <% for (Customer customer : customers) { %>
    <tr>
        <td><%=customer.getId()%></td>
        <td><%=customer.getName()%></td>
        <td><%=customer.getAddress()%></td>
        <td><%=customer.getPhone()%></td>
        <td>
            <a href="edit.do?id=<%=customer.getId()%>">UPDATE</a>
            <a href="delete.do?id=<%=customer.getId()%>" class="delete">DELETE</a>
        </td>
    </tr>
    <% } %>
</table>
<%
    }
%>
<script>
    (function ($) {
        $(function () {
            $('.delete').on('click', function () {
                if (!confirm('是否确定删除')) {
                    return false;
                }
            });
        })
    })(jQuery);
</script>
</body>
</html>
