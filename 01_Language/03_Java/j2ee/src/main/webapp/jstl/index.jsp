<%@ page import="com.onevgo.Customer" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h4>c:out</h4>
<%
    request.setAttribute("book", "<<JAVA>>");
%>
${requestScope.book}
<br>
<c:out value="${requestScope.book}"/>
<br>
<c:out value="${requestScope.book}" escapeXml="false"/>
<br>
<c:out value="${requestScope.book}" default="bookTitle"/>
<br>

<h4>c:set</h4>
<c:set var="name" value="onevgo" scope="page"/>
<%--
    pageContext.setAttribute("name", "onevgo");
--%>
name: ${pageScope.name}
<br>
<c:set var="subject" value="${param.subject}" scope="session"/>
subject: ${sessionScope.subject}
<br>
<%
    Customer customer = new Customer();
    customer.setId(1001);
    request.setAttribute("customer", customer);
%>
ID: ${requestScope.customer.id}
<br>
<c:set target="${requestScope.customer}" property="id" value="${param.id}"/>
ID: ${requestScope.customer.id}

<h4>c:remove</h4>
<c:set var="date" value="1997-09-1" scope="session"/>
date: ${sessionScope.date}
<br>
<c:remove var="date" scope="session"/>
date: ${sessionScope.date}
<br>

<h4>c:if</h4>
<c:set var="age" value="20" scope="request"/>
<c:if test="${requestScope.age > 18}">成年</c:if>
<br>
<c:if test="${param.age > 18}" scope="request" var="isAdult"/>
isAdult: <c:out value="${requestScope.isAdult}"/>
<br>

<h4>c:choose, c:when, c:otherwise</h4>
<c:choose>
    <c:when test="${param.age > 60}">老年</c:when>
    <c:when test="${param.age > 30}">中年</c:when>
    <c:when test="${param.age > 18}">青年</c:when>
    <c:otherwise>未成年</c:otherwise>
</c:choose>
<br>

<h4>c:forEach 可以对数组，Collection, Map遍历</h4>
<c:forEach begin="1" end="10" step="3" var="i">
    <c:out value="${i}"/>
</c:forEach>
<br>
<%
    List<Customer> customers = new ArrayList<>();
    customers.add(new Customer(1, "AAA", 11)); // index = 0
    customers.add(new Customer(2, "BBB", 22)); // 1
    customers.add(new Customer(3, "CCC", 33)); // 2
    customers.add(new Customer(4, "DDD", 44)); // 3
    customers.add(new Customer(5, "EEE", 55)); // 4
    customers.add(new Customer(6, "FFF", 66)); // 5

    request.setAttribute("customers", customers);
%>
<c:forEach items="${requestScope.customers}" var="cust">
    ${cust.id}: ${cust.name}<br>
</c:forEach>
<br>
<c:forEach items="${requestScope.customers}" begin="1" step="2" end="5" var="cust" varStatus="status">
    ${status.index}, ${status.count}, ${status.first}, ${status.last}: ${cust.id}: ${cust.name}<br>
</c:forEach>
<br>
<%
    Map<String, Customer> customerMap = new HashMap<>();
    customerMap.put("a", new Customer(1, "AAA", 11)); // index = 0
    customerMap.put("b", new Customer(2, "BBB", 22)); // 1
    customerMap.put("c", new Customer(3, "CCC", 33)); // 2
    customerMap.put("d", new Customer(4, "DDD", 44)); // 3
    customerMap.put("e", new Customer(5, "EEE", 55)); // 4
    customerMap.put("f", new Customer(6, "FFF", 66)); // 5

    request.setAttribute("customerMap", customerMap);
%>
<c:forEach items="${requestScope.customerMap}" var="cust">
    ${cust.key} - ${cust.value} - ${cust.value.id}<br>
</c:forEach>
<br>
<%
    String[] names = new String[]{"aaa", "bbb", "ccc", "ddd"};
    request.setAttribute("names", names);
%>
<c:forEach items="${requestScope.names}" var="name">
    ${name} <br>
</c:forEach>
<br>
<c:forEach items="${pageContext.session.attributeNames}" var="attr">
    ${attr}<br>
</c:forEach>
<br>

<h4>c:forTokens 处理字符串的，类似于String的split() 方法</h4>
<c:set var="test" scope="request" value="a,b,c.d.e.f;g;h;j"/>
<c:forTokens items="${requestScope.test}" delims=",.;" var="s">
    ${s} <br>
</c:forTokens>

<c:out value=""/>
<h4>c:import 可以包含任何页面到当前页面</h4>
<%--<c:import url="http://www.baidu.com" charEncoding="utf-8" />--%>

<h4>c:redirect 使当前jsp 页面重定向到执行的页面。 使当前JSP转发到指定页面可以使用</h4>
<%--<c:redirect url="/test.jsp" />--%>

<h4>
    c:url 产生一个url地址。 可以Cookie是否可用来智能进行URL 重写，对GET请求的参数进行编码
    可以把产生的URL 存储在域对象的属性中
    还可以使用c:param 为 URL 添加参数， c:url 会对参数进行自动的转码。
    value 中的 / 代表的是当前WEB应用的根目录
</h4>
<c:url value="test.jsp" var="testurl">
    <c:param name="name" value="世界你好"/>
</c:url>
url: ${pageScope.testurl}
<br>
</body>
</html>
