<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <title>Danh sách nhân viên</title>
    <%--css--%>
    <jsp:include page="style.jsp"></jsp:include>
</head>
<body class="nav-md">
<div class="container body">
    <div class="main_container">
        <%--side_bar--%>
        <jsp:include page="navbar.jsp"></jsp:include>
        <%--/side_bar--%>

        <%--top-navigation--%>
        <jsp:include page="top_nav.jsp"></jsp:include>
        <%--/top-navigation--%>

        <%--page-content--%>
        <jsp:include page="page-content_DSNhanVien.jsp"></jsp:include>
        <%--/page-content--%>

        <%--footer--%>
        <jsp:include page="footer.jsp"></jsp:include>
        <%--/footer--%>

    </div>
</div>
<jsp:include page="scripts.jsp"></jsp:include>
</body>

</html>
