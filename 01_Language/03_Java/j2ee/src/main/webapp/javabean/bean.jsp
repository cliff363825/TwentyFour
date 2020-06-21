<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="<%=request.getContextPath()%>/javabean/bean.jsp?id=1&name=onevgo.com&age=10">Click me!</a>

<jsp:useBean id="customer" class="com.onevgo.Customer" scope="request"/>

<%--<jsp:setProperty name="customer" property="age" value="10" />--%>
<jsp:setProperty name="customer" property="*"/>

age:
<jsp:getProperty name="customer" property="age"/>
name:
<jsp:getProperty name="customer" property="name"/>
id:
<jsp:getProperty name="customer" property="id"/>

<jsp:useBean id="customer2" beanName="<%=customer.getClass().getName()%>" type="java.lang.Object" scope="request"/>
<%
    out.println(customer2.hashCode());
%>
</body>
</html>
