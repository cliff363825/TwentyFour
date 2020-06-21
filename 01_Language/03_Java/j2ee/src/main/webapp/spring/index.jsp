<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="com.onevgo.spring.struts2.beans.Person" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    // 1. 从 application 域对象中得到 IOC 容器的实例
    ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(application);

    // 2. 从 IOC 容器中得到 Bean
    Person person = applicationContext.getBean(Person.class);

    person.hello();
%>
</body>
</html>
