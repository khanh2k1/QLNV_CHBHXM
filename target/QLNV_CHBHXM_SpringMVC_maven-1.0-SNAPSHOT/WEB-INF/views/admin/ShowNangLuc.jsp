<%--
  Created by IntelliJ IDEA.
  User: tanle
  Date: 19/05/2022
  Time: 11:50 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>hello ban !</h1>
<c:forEach items="${nl.listMaDV}" var="nl">
    <c:out value="${nl}"></c:out>
</c:forEach>
</body>
</html>
