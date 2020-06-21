<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/springmvc/testFileUpload" method="post" enctype="multipart/form-data">
    File: <input type="file" name="file"><br>
    Desc: <input type="text" name="desc"><br>
    <input type="submit" value="Submit"><br>
</form>
<br>
<form action="${pageContext.request.contextPath}/springmvc/testHttpMessageConverter" method="post" enctype="multipart/form-data">
    File: <input type="file" name="file"><br>
    Desc: <input type="text" name="desc"><br>
    <input type="submit" value="Submit"><br>
</form>
<br>
<form action="${pageContext.request.contextPath}/springmvc/testHttpMessageConverter" method="post">
    Username: <input type="text" name="username"><br>
    Age: <input type="text" name="age"><br>
    <input type="submit" value="Submit"><br>
</form>
<br>
<form action="/springmvc/testModelAttribute" method="post">
    <input type="hidden" name="id" value="1"><br>
    username: <input type="text" name="username" value="Tom"><br>
    email: <input type="text" name="email" value="tom@onevgo.com"><br>
    age: <input type="text" name="age" value="12"><br>
    <input type="submit" name="Submit"><br>
</form>
<br>
<form action="/springmvc/testPojo" method="post">
    username: <input type="text" name="username"><br>
    password: <input type="password" name="passowrd"><br>
    email: <input type="text" name="email"><br>
    age: <input type="text" name="age"><br>
    city: <input type="text" name="address.city"><br>
    province: <input type="text" name="address.province"><br>
    <input type="submit" name="Submit"><br>
</form>
<br>
<form action="${pageContext.request.contextPath}/springmvc/testMap" method="post">
    <input name="username" value="Tom"><br>
    <input name="age" value="12"><br>
    <input type="submit" value="Submit"><br>
</form>
<br>
<form action="${pageContext.request.contextPath}/springmvc/testRest/1" method="post">
    <input type="hidden" name="_method" value="PUT">
    <input type="submit" value="TestRest PUT">
</form>
<br>
<form action="${pageContext.request.contextPath}/springmvc/testRest/1" method="post">
    <input type="hidden" name="_method" value="DELETE">
    <input type="submit" value="TestRest DELETE">
</form>
<br>
<form action="${pageContext.request.contextPath}/springmvc/testRest" method="post">
    <input type="submit" value="TestRest POST">
</form>
<br>
<form action="${pageContext.request.contextPath}/springmvc/testRest/1" method="get">
    <input type="submit" value="TestRest GET">
</form>
</body>
</html>
