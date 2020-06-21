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
<%
    String username = (String) session.getAttribute("username");
    if (username == null) {
        username = "";
    }
%>
<form action="<%=response.encodeURL("hello.jsp")%>" method="post">
    username: <input type="text" name="username" value="<%=username%>">
    <input type="submit" value="Submit">
</form>
</body>
</html>
