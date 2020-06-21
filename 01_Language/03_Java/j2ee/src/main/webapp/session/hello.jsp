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
CreateTime: <%=session.getCreationTime()%>
<br>
LastAccessTime: <%=session.getLastAccessedTime()%>
<br>
Hello: <%=request.getParameter("username")%>
<%
    session.setAttribute("username", request.getParameter("username"));
%>
<a href="<%=response.encodeURL("login.jsp")%>">重新登录</a>
<br>
<a href="<%=response.encodeURL("logout.jsp")%>">注销</a>
</body>
</html>
