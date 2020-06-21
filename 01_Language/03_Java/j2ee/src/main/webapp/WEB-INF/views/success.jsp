<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h4>Success</h4>
${requestScope.time}
<br>
${requestScope.names}
<br>
request.user: ${requestScope.user}
<br>
request.abc user: ${requestScope.abc}
<br>
session.user: ${sessionScope.user}
<br>
<fmt:message key="i18n.username"></fmt:message>
<br>
<fmt:message key="i18n.password"></fmt:message>
</body>
</html>
