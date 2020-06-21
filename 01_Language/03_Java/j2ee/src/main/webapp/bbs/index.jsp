<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/bbs/bbs.jsp" method="post">
    context: <textarea name="content"></textarea>
    <input type="submit" value="Submit">
</form>
</body>
</html>
