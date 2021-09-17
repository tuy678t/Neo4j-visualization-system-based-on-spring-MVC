<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/4/11
  Time: 22:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MDF</title>
</head>
<body>

<form action="<c:url value="/say/create" />">
    <input type="submit" value="创建" />
</form>
<form action="<c:url value="/say/delete" />">
    <input type="submit" value="删除" />
</form>
<form action="<c:url value="/say/update" />">
    <input type="submit" value="更新" />
</form>
    ${message}
</body>
</html>
