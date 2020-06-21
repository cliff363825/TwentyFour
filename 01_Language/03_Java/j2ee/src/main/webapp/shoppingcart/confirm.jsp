<%@ page import="com.onevgo.shoppingcart.bean.Customer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    Customer customer = (Customer) session.getAttribute("customer");
    String[] books = (String[]) session.getAttribute("books");
%>
<table>
    <tr>
        <td>顾客姓名</td>
        <td><%=customer.getName()%></td>
    </tr>

    <tr>
        <td>地址：</td>
        <td><%=customer.getAddress()%></td>
    </tr>

    <tr>
        <td>卡号：</td>
        <td><%=customer.getCard()%></td>
    </tr>

    <tr>
        <td>卡的类型：</td>
        <td><%=customer.getCardType()%></td>
    </tr>

    <tr>
        <td>Books:</td>
        <td>
            <%
                for(String book : books) {
                    out.println(book + "<br>");
                }
            %>
        </td>
    </tr>
</table>
</body>
</html>
