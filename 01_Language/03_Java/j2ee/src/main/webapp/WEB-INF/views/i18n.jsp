<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<fmt:message key="i18n.username"></fmt:message>

<a href="/springmvc/i18n2">I18N 2</a><br>
<a href="/springmvc/i18n?locale=zh_CN">中文</a><br>
<a href="/springmvc/i18n?locale=en_US">EN</a><br>
</body>
</html>
