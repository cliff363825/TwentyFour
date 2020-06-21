<%@ page import="java.io.InputStream" %>
<%@ page import="java.io.InputStreamReader" %>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.IOException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%=request.getContextPath()%>
<br>
<%=request.getServletContext().getContextPath()%>
<br>
<%=request.getServletContext().getRealPath("/")%>
<br>
<%
    BufferedReader br = null;
    try {
        InputStream is = request.getServletContext().getResourceAsStream("/login.html");
        br = new BufferedReader(new InputStreamReader(is));
        String line = null;
        while ((line = br.readLine()) != null) {
            out.println(line + "<br>");
        }
    } finally {
        if (br != null) {
            try {
                br.close();
            } catch (IOException e) {
            }
        }
    }
%>
</body>
</html>
