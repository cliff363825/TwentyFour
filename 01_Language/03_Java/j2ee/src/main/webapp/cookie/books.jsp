<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Books</title>
</head>
<body>
<h4>Books Page</h4>

<a href="book.jsp?book=JavaWeb">Java Web</a><br><br>
<a href="book.jsp?book=Java">Java</a><br><br>
<a href="book.jsp?book=Oracle">Oracle</a><br><br>
<a href="book.jsp?book=Ajax">Ajax</a><br><br>
<a href="book.jsp?book=JavaScript">JavaScript</a><br><br>
<a href="book.jsp?book=Android">Android</a><br><br>
<a href="book.jsp?book=Jbpm">Jbpm</a><br><br>
<a href="book.jsp?book=Struts">Struts</a><br><br>
<a href="book.jsp?book=Hibernate">Hibernate</a><br><br>
<a href="book.jsp?book=Spring">Spring</a><br><br>

<%
    Cookie[] cookies = request.getCookies();
    if (cookies != null && cookies.length > 0) {
        for (Cookie c : cookies) {
            if (c.getName().startsWith("BOOK_")) {
                out.println(c.getValue());
                out.println("<br>");
            }
        }
    }
%>
</body>
</html>
