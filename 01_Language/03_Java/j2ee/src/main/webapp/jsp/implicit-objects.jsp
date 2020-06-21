<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>jsp隐含9个对象</title>
</head>
<body>
<h1>jsp隐含的9个对象</h1>
<%
    // JSP隐含对象
    // 1. request: javax.servlet.http.HttpServletRequest
    String name = request.getParameter("name");
    System.out.println(name);

    // 2. response: javax.servlet.http.HttpServletResponse
    System.out.println(response instanceof HttpServletResponse);

    // 3. pageContext: javax.servlet.jsp.PageContext
    ServletRequest request1 = pageContext.getRequest();
    System.out.println(request1 == request);

    // 4. session: javax.servlet.http.HttpSession
    System.out.println(session.getId());

    // 5. application: javax.servlet.ServletContext
    System.out.println(application.getInitParameter("username"));

    // 6. config: javax.servlet.ServletConfig
    System.out.println(config.getInitParameter("test"));

    // 7. out: javax.servlet.jsp.JspWriter
    out.println(request.getParameter("name"));
    out.println("<br><br>");
    out.println(application.getInitParameter("username"));
    out.println("<br><br>");
    out.println(config.getInitParameter("test"));
    out.println("<br><br>");

    // 8. page: java.lang.Object
    out.println(page instanceof HttpServlet);
%>
</body>
</html>
