<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>jsp声明</title>
</head>
<body>
<h1>jsp声明</h1>
<%
    Date date = new Date();
    out.print(date);
%>
<br>
<%=date%>
<br>
<%
    String ageStr = request.getParameter("age");
    int age = 0;
    if (ageStr != null) {
        try {
            age = Integer.parseInt(ageStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
%>
<% if (age > 18) { %>
成人
<% } else { %>
未成年
<% } %>
<br>
<%!
    public int viewCount = 0;
    public void incrViewCount() {
        ++viewCount;
    }
    public static int viewCount1 = 0;
    public static void incrViewCount1() {
        ++viewCount1;
    }
%>
<%
    incrViewCount();
    incrViewCount1();
%>
<%=viewCount%>
<br>
<%=viewCount1%>
<%
    if (viewCount == viewCount1) {
        out.print("我是单例");
    }
%>

<%-- JSP注释 --%>
<!-- Html注释 -->
</body>
</html>
