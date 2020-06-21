<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add Customer</title>
</head>
<body>
<c:if test="${!empty requestScope.message}">
    <p style="color: red"><c:out value="${requestScope.message}"/></p>
</c:if>
<form action="${pageContext.request.contextPath}/addCustomer.do" method="post">
    <table>
        <tr>
            <td>Name:</td>
            <td><input type="text" name="name" value="${param.name}"></td>
        </tr>

        <tr>
            <td>Address:</td>
            <td><input type="text" name="address" value="${param.address}"></td>
        </tr>

        <tr>
            <td>Phone:</td>
            <td><input type="text" name="phone" value="${param.phone}"></td>
        </tr>

        <tr>
            <td><input type="submit" value="submit"></td>
            <td></td>
        </tr>
    </table>
</form>
</body>
</html>
