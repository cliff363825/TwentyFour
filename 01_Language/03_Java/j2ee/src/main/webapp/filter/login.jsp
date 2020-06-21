<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${!empty requestScope.message}">
    ${requestScope.message}
</c:if>
<form action="${pageContext.request.contextPath}/filter/hello.jsp" method="post">
    username: <input type="text" name="username">
    password: <input type="password" name="password">

    <input type="submit" value="Submit" />
</form>
</body>
</html>
