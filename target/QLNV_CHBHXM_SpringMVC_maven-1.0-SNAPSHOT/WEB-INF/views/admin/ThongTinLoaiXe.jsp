<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <title>Danh sách loai xe</title>
    <style>
        .error_message{
            color: red;
            font-size: 17px;
            font-weight: 600;
        }
    </style>
    <%--css--%>
    <jsp:include page="Style.jsp"/>
</head>

<body class="nav-md">
<div class="container body">
    <div class="main_container">
        <%--side_bar--%>
        <jsp:include page="Navbar.jsp"/>
        <%--/side_bar--%>

        <%--top-navigation--%>
        <jsp:include page="Topnav.jsp"/>
        <%--/top-navigation--%>

        <%--page-content--%>
        <div class="right_col" role="main" style="min-height: 100%;">
            <div class="">
                <div class="page-title">
                    <div class="title_left">
                        <h3>Thông tin loai xe</h3>
                    </div>
                </div>
                <div class="clearfix"></div>
                <div class="row">
                    <div class="col-md-12 col-sm-12 ">
                        <div class="x_panel">
                            <div class="x_title">

                                <div class="clearfix"></div>
                            </div>
                            <div class="x_content">
                                <br>

                                <form:form
                                        id="demo-form2" class="form-horizontal form-label-left"
                                        modelAttribute="loaiXe"
                                        action="/admin/LoaiXe/DanhSachLoaiXe/editLoaiXe/${maLX}"
                                        method="post">

                                    <div class="item form-group">
                                        <label class="col-form-label col-md-3 col-sm-3 label-align">Mã loại xe
                                        </label>
                                        <div class="col-md-6 col-sm-6 ">
                                            <form:hidden path="maLX"/>
                                            <p class="form-control">${maLX}</p>
                                        </div>
                                    </div>
                                    <div class="item form-group">
                                        <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Tên Loại xe
                                            <span class="required">*</span>
                                        </label>
                                        <div class="col-md-6 col-sm-6 ">
                                            <form:input type="text" path="tenLX" id="first-name" class="form-control"/>
                                            <p class="error_message">${message_tenlx}</p>
                                        </div>
                                    </div>

                                    <div class="ln_solid"></div>
                                    <div class="item form-group">
                                        <div class="col-md-6 col-sm-6 offset-md-3">
                                            <a type="button" class="btn btn-primary" onclick="showWarning('/admin/LoaiXe/DanhSachLoaiXe')" style="color: white">Trở về</a>
                                            <button type="submit" class="btn btn-success">Lưu</button>
                                            <a class="btn btn-danger" type="button"
                                               style="font-weight: 700; color:white"
                                               onclick="showWarning('/admin/LoaiXe/DanhSachLoaiXe/deleteLoaiXe/${maLX}')">
                                                XÓA
                                            </a>
                                        </div>
                                    </div>
                                </form:form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%--!page-content--%>
        <%--footer--%>
        <jsp:include page="Footer.jsp"></jsp:include>
        <%--!footer--%>
        <jsp:include page="Scripts.jsp"/>
            <script>
                function showWarning(link) {
                    var ms  = confirm('Bạn có muốn tiếp tục ?')
                    if (ms===true) {
                        window.location.href = link;
                    }
                }
            </script>

</body>

</html>
