<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSP中文乱码</title>
</head>
<body>
<%
    // 1. 解决POST中文乱码
    request.setCharacterEncoding("UTF-8");
    String name = request.getParameter("name");

    // 2. 解决GET中文乱码
    // 2.1 代码转换
    // name = new String(name.getBytes("ISO-8859-1"), "UTF-8");
    // 2.2 修改Tomcat的server.xml
    // <Connector connectionTimeout="20000" port="8989" protocol="HTTP/1.1" redirectPort="8443" URIEncoding="UTF-8" useBodyEncodingForURI="true"/>
%>
Name: <%=name%>
<form action="" method="get">
    <input type="text" name="name" value="<%=name != null ? name : ""%>">
    <input type="submit" value="提交">
</form>
</body>
</html>
