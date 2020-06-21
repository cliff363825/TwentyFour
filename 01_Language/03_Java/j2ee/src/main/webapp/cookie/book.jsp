<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Book</title>
</head>
<body>
<h4>Book Detail Page</h4>

Book: <%= request.getParameter("book") %>

<br><br>

<a href="books.jsp">Return</a>

<%
    Cookie[] cookies = request.getCookies();
    List<Cookie> bookCookies = new ArrayList<>();
    Cookie tempCookie = null;
    String book = request.getParameter("book");

    if (cookies != null && cookies.length > 0) {
        for (Cookie c : cookies) {
            String cookieName = c.getName();
            if (cookieName != null && cookieName.startsWith("BOOK_")) {
                bookCookies.add(c);

                if (c.getValue().equals(book)) {
                    tempCookie = c;
                }
            }
        }
    }

    if (bookCookies.size() >= 5 && tempCookie == null) {
        tempCookie = bookCookies.get(0);
    }

    if (tempCookie != null) {
        tempCookie.setMaxAge(0);
        response.addCookie(tempCookie);
    }

    if (book != null) {
        Cookie cookie = new Cookie("BOOK_" + book, book);
        response.addCookie(cookie);
    }
%>
</body>
</html>
