<%@ page import="java.util.Date" %>
<%@ page import="com.onevgo.TokenProcessor" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    //    session.setAttribute("token", new Date().getTime() + "");
%>
<form action="<%=request.getContextPath()%>/token-servlet" method="post">
    <input type="hidden" name="COM.ONEVGO.TOKEN_KEY" value="<%=TokenProcessor.getInstance().saveToken(request)%>">
    Name: <input type="text" name="name"><br>
    <input type="submit" value="Submit">
</form>

<br><br>

<form action="<%=request.getContextPath()%>/check-code-servlet" method="post">
    <input type="hidden" name="COM.ONEVGO.TOKEN_KEY" value="<%=TokenProcessor.getInstance().saveToken(request)%>">
    Name: <input type="text" name="name"><br>
    Code: <input type="text" name="CHECK_CODE_KEY">
    <img src="<%=request.getContextPath()%>/validate-color-servlet"><br>
    <input type="submit" value="Submit">
</form>
</body>
</html>
