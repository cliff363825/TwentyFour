<%@ page import="java.util.Date" %>
<%@ page import="java.util.Locale" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    String code = request.getParameter("code");
    if (code != null) {
        if ("en".equals(code)) {
            session.setAttribute("locale", Locale.US);
        } else if ("zh".equals(code)) {
            session.setAttribute("locale", Locale.SIMPLIFIED_CHINESE);
        }
    }

    Date date = new Date();
    request.setAttribute("date", date);
    request.setAttribute("salary", 12345.67d);
%>
<c:if test="${!empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"></fmt:setLocale>
</c:if>

<fmt:bundle basename="i18n">
    <fmt:message key="date"></fmt:message>
    <fmt:formatDate value="${date}" dateStyle="FULL"></fmt:formatDate>
    <fmt:message key="salary"></fmt:message>
    <fmt:formatNumber value="${salary}" type="currency"></fmt:formatNumber>
    <fmt:message key="i18n.username"></fmt:message>
    <fmt:message key="i18n.password"></fmt:message>
</fmt:bundle>
<br><br>
<a href="index.jsp?code=en">Eng</a>
<a href="index.jsp?code=zh">中文</a>
</body>
</html>
