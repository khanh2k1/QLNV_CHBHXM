<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <title>Thông tin bảng giá</title>
    <style>
        .error_message {
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
                        <h3>Thông tin bảng giá</h3>
                    </div>
                </div>
                <div class="clearfix"></div>
                <div class="row">
                    <div class="col-md-12 col-sm-12 ">
                        <div class="x_panel">
                            <div class="x_content">

                                <form:form
                                        id="demo-form2" class="form-horizontal form-label-left"
                                        action="/admin/BangGia/DanhSachBangGia/editBangGia/${madg}"
                                        method="post"
                                        modelAttribute="bg_custom">

                                    <div class="item form-group">
                                        <label class="col-form-label col-md-3 col-sm-3 label-align">Tên loại xe
                                        </label>
                                        <div class="col-md-6 col-sm-6 ">
                                            <form:hidden path="tenlx"/>
                                            <label class="form-control">${bg_custom.tenlx}</label>
                                            <p class="error_message"></p>
                                        </div>
                                    </div>
                                    <div class="item form-group">
                                        <label class="col-form-label col-md-3 col-sm-3 label-align">Tên dịch vụ
                                            <span class="required">*</span>
                                        </label>
                                        <div class="col-md-6 col-sm-6 ">
                                            <form:hidden path="tendv"/>
                                            <p class="form-control">${bg_custom.tendv}</p>
                                            <p class="error_message"></p>
                                        </div>
                                    </div>
                                    <div class="item form-group">
                                        <label class="col-form-label col-md-3 col-sm-3 label-align">Đơn giá
                                            <span class="required">*</span>
                                        </label>
                                        <div class="col-md-6 col-sm-6 ">
                                            <form:input type="number" path="dongia" class="form-control"/>
                                            <p class="error_message">${message_dongia}</p>
                                        </div>
                                    </div>
                                    <div class="item form-group">
                                        <label class="col-form-label col-md-3 col-sm-3 label-align">Trạng thái

                                        </label>
                                        <div class="col-md-6 col-sm-6 ">
                                            <form:select path="trangthai" class="form-control">
                                                <form:option value="Đang dùng" label="Đang dùng"/>
                                                <form:option value="Ngưng" label="Ngưng"/>
                                            </form:select>
                                            <p class="error_message">${message_trangthai}</p>

                                        </div>
                                    </div>

                                    <div class="ln_solid"></div>
                                    <div class="item form-group">
                                        <div class="col-md-6 col-sm-6 offset-md-3">
                                            <a type="button" class="btn btn-primary"
                                               onclick="showWarning('/admin/BangGia/DanhSachBangGia')"
                                               style="color: white">Trở về</a>

                                            <button class="btn btn-success" type="submit">Lưu</button>
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
                var ms = confirm('Bạn có muốn tiếp tục ?')
                if (ms === true) {
                    window.location.href = link;
                }
            }
        </script>
</body>

</html>
