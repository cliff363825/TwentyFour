<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/authority-servlet?method=getAuthorities" method="post">
    name: <input type="text" name="username">
    <input type="submit" value="Submit">
</form>

<c:if test="${!empty requestScope.user}">
    <br>
    ${requestScope.user.username} 的权限是：
    <br>
    <form action="${pageContext.request.contextPath}/authority-servlet?method=updateAuthority" method="post">
        <input type="hidden" name="username" value="${requestScope.user.username}">

        <c:forEach items="${requestScope.authorities}" var="auth">
            <c:set var="flag" value="false"/>
            <c:forEach items="${requestScope.user.authorities}" var="ua">
                <c:if test="${ua.url == auth.url}">
                    <c:set var="flag" value="true"/>
                </c:if>
            </c:forEach>
            <c:choose>
                <c:when test="${flag == true}">
                    <input type="checkbox" name="url" value="${auth.url}" checked>${auth.displayName}<br>
                </c:when>
                <c:otherwise>
                    <input type="checkbox" name="url" value="${auth.url}">${auth.displayName}<br>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <input type="submit" value="Submit">
    </form>
</c:if>
</body>
</html>
