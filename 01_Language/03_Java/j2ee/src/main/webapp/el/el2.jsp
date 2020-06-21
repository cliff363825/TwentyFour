<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="com.onevgo.Customer" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>EL 的 [] 与 . 运算符</h3>
<%
    session.setAttribute("age", 20);

    Customer customer = new Customer();
    customer.setName("onevgo");
    session.setAttribute("com.onevgo.customer", customer);
%>
age: ${age}
<br>
age: ${sessionScope.age}
<br>
name: ${sessionScope.com.onevgo.customer.name}
<br>
name: ${sessionScope["com.onevgo.customer"].name}
<hr>

<h3>EL 的算术运算符 +、-、*或$、/或div、%或mod</h3>
score: ${param.score + 11}
<br>
score: <%= request.getParameter("score") + 11%>
<hr>

<h3>EL 的关系运算符 ==或eq、!=或ne、&lt;或lt, &gt;或gt、&le;或le、&ge;或ge</h3>
${param.score + 1 gt 60 ? "及格" : "不及格"}
<br>
${param.score >= 0 ? '>=0' : '<0'}
<hr>

<h3>EL 的逻辑运算符 &&或and、||或or、!或not</h3>
${true && false}
<br>
${true || false}
<hr>

<h3>EL 的其他运算符 empty运算符、条件运算符、()运算符</h3>
<%
    List<String> names = new ArrayList<>();
    request.setAttribute("names", names);
    names.add("abc");
%>
names is empty: ${empty requestScope.names}
<br>
${empty param.score ? 0 : -param.score}
<hr>

<h3>EL 函数 functions</h3>
${fn:escapeXml('<script>alert("xss")</script>')}
<hr>

<h3>EL 中的隐含对象</h3>
<%
    Customer customer2 = new Customer();
    customer2.setName("onevgo");
    customer2.setAge(28);
    request.setAttribute("customer", customer2);
%>

<h3>与范围相关的隐含对象 pageScope, requestScope, sessionScope, applicationScope</h3>
Name: ${customer.name}
<br>
Customer: <%= request.getAttribute("customer")%>
<hr>

<h3>与输入有关的隐含对象 param, paramValues</h3>
score: ${param.score}
<br>
score: <%=request.getParameter("score")%>
<br>
<p>-Dorg.apache.el.parser.SKIP_IDENTIFIER_CHECK=true</p>
names: ${paramValues.name[0]} ${paramValues.name[1]} ${paramValues.name[2]} ${paramValues.name[3]}
<br>
<%
    String[] names1 = request.getParameterValues("name");
    if (names1 != null && names1.length > 0) {
        out.print(names1[0].getClass().getName());
    }
%>
<hr>

<h3>其他隐含对象 cookie, header和headerValues, initParam, pageContext</h3>
cookie:
<br>
JSESSIONID: ${cookie.JSESSIONID.name} -- ${cookie.JSESSIONID.value}
<hr>

header和headerValues:
<br>
Accept-Language: ${header['Accept-Language']}
<br>
Accept-Language: ${headerValues['Accept-Language'][0]}
<hr>

initParam:
<br>
driver: ${initParam.driver}
<hr>

pageContext: pageContext 即为 PageContext 类型，但只能读取属性。
<br>
contextPath: ${pageContext.request.contextPath}
<br>
sessionId: ${pageContext.session.id}
<br>
sessionAttributeNames: ${pageContext.session.attributeNames}
<hr>

</body>
</html>
