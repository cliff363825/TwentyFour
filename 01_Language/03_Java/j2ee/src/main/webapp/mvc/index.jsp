<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Customer List</title>
    <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
</head>
<body>
<form action="/query.do" method="post">
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
            <td><a href="${pageContext.request.contextPath}/mvc/addCustomer.jsp">Add Customer</a></td>
        </tr>
    </table>
</form>

<br><br>

<c:if test="${!empty requestScope.customers}">
<table>
    <tr>
        <th>ID</th>
        <th>CustomerName</th>
        <th>Address</th>
        <th>Phone</th>
        <th>Update/Delete</th>
    </tr>
    <c:forEach items="${requestScope.customers}" var="cust">
    <tr>
        <td><c:out value="${cust.id}" /></td>
        <td><c:out value="${cust.name}" /></td>
        <td><c:out value="${cust.address}" /></td>
        <td><c:out value="${cust.phone}" /></td>
        <td>
            <a href="<c:url value="/edit.do"><c:param name="id" value="${cust.id}" /></c:url>">UPDATE</a>
            <a href="<c:url value="/delete.do"><c:param name="id" value="${cust.id}" /></c:url>" class="delete">DELETE</a>
        </td>
    </tr>
    </c:forEach>
</table>
</c:if>
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
