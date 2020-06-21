<%@ page import="cn.hutool.core.date.DateUtil" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
SessionId: <%=session.getId()%>
<br>
IsNew: <%=session.isNew()%>
<br>
MaxInactiveInternal: <%=session.getMaxInactiveInterval()%>
<br>
<%
    long creationTime = session.getCreationTime();
    out.print(DateUtil.formatDate(new Date(creationTime)));
%>
<br>
CreateTime: <%=session.getCreationTime()%>
<br>
LastAccessTime: <%=session.getLastAccessedTime()%>
<br>
Bye: <%=session.getAttribute("username")%>
<%
    session.invalidate();
%>
<a href="login.jsp">重新登录</a>
</body>
</html>
