<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>jsp域属性1</title>
</head>
<body>
<h1>jsp域属性1</h1>
<%
    pageContext.setAttribute("pageContextAttr", "pageContextValue");
    request.setAttribute("requestAttr", "requestValue");
    session.setAttribute("sessionAttr", "sessionValue");
    application.setAttribute("applicationAttr", "applicationValue");
%>
pageContextAttr: <%=pageContext.getAttribute("pageContextAttr")%> <br>
requestAttr: <%=request.getAttribute("requestAttr")%> <br>
sessionAttr: <%=session.getAttribute("sessionAttr")%> <br>
applicationAttr: <%=application.getAttribute("applicationAttr")%> <br>

<a href="<%=request.getContextPath()%>/jsp/attribute-2.jsp">test_attribute_2</a><br>
<a href="<%=request.getContextPath()%>/test-attr">test-attr</a><br>

<%--
    request.getRequestDispatcher("/jsp_attr_2.jsp").forward(request, response);
--%>
</body>
</html>
