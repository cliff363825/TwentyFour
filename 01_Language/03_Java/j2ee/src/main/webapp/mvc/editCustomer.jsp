<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit Customer</title>
</head>
<body>
<c:if test="${!empty requestScope.message}">
    <p style="color: red"><c:out value="${requestScope.message}" /></p>
</c:if>

<c:set var="id" value="${requestScope.customer != null ? requestScope.customer.id : param.id}" />
<c:set var="name" value="${requestScope.customer != null ? requestScope.customer.name : param.name}" />
<c:set var="address" value="${requestScope.customer != null ? requestScope.customer.address : param.address}" />
<c:set var="phone" value="${requestScope.customer != null ? requestScope.customer.phone : param.phone}" />

<form action="${pageContext.request.contextPath}/update.do" method="post">
    <table>
        <tr>
            <td>Name:</td>
            <td><input type="text" name="name" value="${name}"></td>
        </tr>

        <tr>
            <td>Address:</td>
            <td><input type="text" name="address" value="${address}"></td>
        </tr>

        <tr>
            <td>Phone:</td>
            <td><input type="text" name="phone" value="${phone}"></td>
        </tr>

        <tr>
            <td><input type="submit" value="submit"></td>
            <td>
                <input type="hidden" name="id" value="${id}">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
