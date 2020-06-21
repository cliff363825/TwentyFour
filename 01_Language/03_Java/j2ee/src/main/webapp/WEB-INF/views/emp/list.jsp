<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <script src="${pageContext.request.contextPath}/static/js/jquery.js"></script>
    <script>
        $(function () {
            $('.delete').on('click', function () {
                if (!confirm('你确定要删除这条信息？')) {
                    return false;
                }
                var th = $(this);
                $.ajax({
                    url: th.attr('href'),
                    type: 'DELETE',
                    dataType: 'json',
                    success: function (res) {
                        if (res.code === 0) {
                            location.reload();
                        } else {
                            alert(res.msg);
                        }
                    }
                });

                return false;
            });
        });
    </script>
</head>
<body>
<c:if test="${requestScope.page == null || requestScope.page.numberOfElements == 0}">
    没有任何记录
</c:if>
<c:if test="${requestScope.page != null && requestScope.page.numberOfElements > 0}">
    <table border="1" cellpadding="10" cellspacing="0">
        <tr>
            <th>ID</th>
            <th>LastName</th>
            <th>Email</th>
            <th>Birth</th>
            <th>CreateTime</th>
            <th>Department</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        <c:forEach items="${requestScope.page.content}" var="emp">
            <tr>
                <td>${emp.id}</td>
                <td>${emp.lastName}</td>
                <td>${emp.email}</td>
                <td><fmt:formatDate value="${emp.birth}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
                <td><fmt:formatDate value="${emp.createTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate> </td>
                <td>${emp.department.departmentName}</td>
                <td><a href="${pageContext.request.contextPath}/emp/${emp.id}">Edit</a></td>
                <td><a href="${pageContext.request.contextPath}/emp/${emp.id}" class="delete">Delete</a></td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="8">
                共 ${requestScope.page.totalElements} 条记录
                共 ${requestScope.page.totalPages} 页
                当前 ${requestScope.page.number + 1} 页
                <a href="?pageNo=${requestScope.page.number + 1 - 1}">上一页</a>
                <a href="?pageNo=${requestScope.page.number + 1 + 1}">下一页</a>
            </td>
        </tr>
    </table>
</c:if>
</body>
</html>
