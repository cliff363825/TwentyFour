<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    HttpSession session = request.getSession(false);
    out.println(session);

    HttpSession session1 = request.getSession(true);
    out.println(session1.getMaxInactiveInterval());

//    session1.invalidate();
%>
</body>
</html>
