<%@page language="java" %>
<%--<%@page extends="org.apache.jasper.runtime.HttpJspBase" %>--%>
<%@ page import="java.util.Date" %>
<%@page session="true" %>
<%@page buffer="8kb" %>
<%@page autoFlush="true" %>
<%@page isThreadSafe="true" %>
<%@page info="jsp_directive" %>
<%@page errorPage="/WEB-INF/error.jsp" %>
<%@page isErrorPage="false" %>
<%@page contentType="text/html; charset=UTF-8" %>
<%@page pageEncoding="UTF-8" %>
<%@page isELIgnored="true" %>
<html>
<head>
    <title>jsp指令 - page</title>
</head>
<body>
<h1>jsp指令 - page</h1>
<%
    Date date = new Date();
    out.print(date);
%>
<br>
<%= session.getId()%>
<br>
<%--<%=1/0%>--%>
</body>
</html>
