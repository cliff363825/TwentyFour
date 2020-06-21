<%@ page import="java.util.List" %>
<%@ page import="com.onevgo.Customer" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- 导入标签库（描述文件）-->
<%@ taglib prefix="one" uri="http://www.onevgo.com/one/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<one:hello value="${param.name}" count="10"/>
<br>
<one:max num1="${param.num1}" num2="${param.num2}"/>
<br>
<one:readerFile src="/login.html"/>
<br>
<one:testJspFragment>Hello world!</one:testJspFragment>
<br>
<one:printUpper time="10">onevgo</one:printUpper>
<br>
<%
    List<Customer> customers = new ArrayList<>();
    customers.add(new Customer(1, "AA", 11));
    customers.add(new Customer(2, "BB", 22));
    customers.add(new Customer(3, "CC", 33));
    customers.add(new Customer(4, "DD", 44));
    request.setAttribute("customers", customers);

    Map<String, Customer> customerMap = new HashMap<>();
    customerMap.put("a", new Customer(1, "AA", 11));
    customerMap.put("B", new Customer(2, "bb", 11));
    customerMap.put("C", new Customer(3, "cc", 11));
    customerMap.put("D", new Customer(4, "dd", 11));
    customerMap.put("E", new Customer(5, "ee", 11));
    request.setAttribute("customerMap", customerMap);
%>

<c:forEach items="${requestScope.customers}" var="customer">
    ${customer.id} - ${customer.name} - ${customer.age} <br>
</c:forEach>
<one:forEach items="${requestScope.customers}" var="customer">
    ${customer.id} - ${customer.name} - ${customer.age} <br>
</one:forEach>

<c:forEach items="${requestScope.customerMap}" var="customer">
    ${customer.key} - ${customer.value} <br>
</c:forEach>
<one:forEach items="${requestScope.customerMap}" var="customer">
    ${customer.key} - ${customer.value} <br>
</one:forEach>
<br>

<one:parentTag>
    <!-- 子标签以父标签的标签体存在 子标签把父标签的name属性打印到JSP 页面上 -->
    <one:sonTag/>
</one:parentTag>

<one:choose>
    <one:when test="${param.age > 24}">大学毕业</one:when>
    <one:when test="${param.age > 18}">高中毕业</one:when>
    <one:otherwise>小学毕业？</one:otherwise>
</one:choose>
<br>

${fn:length(param.name)}
<br>
${one:concat("Hello ", "world!")}
</body>
</html>
