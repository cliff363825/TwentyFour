<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <script src="${pageContext.request.contextPath}/static/js/jquery.js"></script>
    <script>
        $(function() {
            $('#lastName').on('change', function () {
                var val = $(this).val();
                val = $.trim(val);
                $(this).val(val);

                // 若修改的 lastName 和之前的 lastName 一致，则不发送 Ajax 请求，直接 alert:lastName 可用！
                var _oldLastName = $('#_oldLastName').val();
                _oldLastName = $.trim(_oldLastName);
                if (_oldLastName != "" && _oldLastName == val) {
                    return;
                }

                var url = '${pageContext.request.contextPath}/ajaxValidateLastName';
                var data = {lastName: val, date: new Date()};
                $.ajax({
                    url: url,
                    type: 'post',
                    data: data,
                    dataType: 'json',
                    success: function (res) {
                        if (res.code === 0) {
                            alert("lastName 可用！");
                        } else {
                            alert("lastName 不可用！");
                        }
                    }
                });
            });
        });
    </script>
</head>
<body>
<c:set var="url" value="${pageContext.request.contextPath}/emp"></c:set>
<c:if test="${requestScope.employee.id != null}">
    <c:set var="url" value="${pageContext.request.contextPath}/emp/${requestScope.employee.id}"></c:set>
</c:if>
<form:form action="${url}" method="post" modelAttribute="employee">
    <c:if test="${requestScope.employee.lastName != null}">
        <c:if test="${requestScope.employee.id != null}">
            <input type="hidden" id="_oldLastName" value="${requestScope.employee.lastName}">
            <form:hidden path="id"></form:hidden>
            <input type="hidden" name="_method" value="PUT">
        </c:if>
    </c:if>
    LastName: <form:input path="lastName" id="lastName"></form:input><br>
    Email: <form:input path="email"></form:input><br>
    Birth: <form:input path="birth"></form:input><br>
    Department: <form:select path="department.id" items="${requestScope.departments}"
                             itemLabel="departmentName" itemValue="id"></form:select><br>
    <input type="submit" value="Submit">
</form:form>
</body>
</html>
