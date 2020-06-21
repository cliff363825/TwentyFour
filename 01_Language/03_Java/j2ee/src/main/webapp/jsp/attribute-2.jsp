<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>jsp域属性2</title>
</head>
<body>
<h1>jsp域属性2</h1>

pageContextAttr: <%=pageContext.getAttribute("pageContextAttr")%> <br>
requestAttr: <%=request.getAttribute("requestAttr")%> <br>
sessionAttr: <%=session.getAttribute("sessionAttr")%> <br>
applicationAttr: <%=application.getAttribute("applicationAttr")%> <br>

</body>
</html>
