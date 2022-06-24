<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <title>Thông tin nhân viên</title>
    <%--css--%>
    <style>
        .error_message{
            color: red;
            font-size: 14px;
            font-weight: 600;
        }
    </style>
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
                        <h3>Thông tin công việc</h3>
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
                                <form action="/admin/CongViec/DanhSachCongViec/ThemCongViec/DanhSachBangGia/${bg.madg}/DanhSachNhanVien/${nv.maNV}"
                                method="post">
                                    <div class="item form-group">
                                        <label class="col-form-label col-md-3 col-sm-3 label-align">Mã nhân viên
                                        </label>
                                        <div class="col-md-6 col-sm-6 ">
                                            <p class="form-control">${nv.maNV}</p>
                                        </div>
                                    </div>

                                    <div class="item form-group">
                                        <label class="col-form-label col-md-3 col-sm-3 label-align">Họ tên
                                        </label>
                                        <div class="col-md-6 col-sm-6 ">
                                            <p class="form-control">${nv.hoten}</p>
                                        </div>
                                    </div>

                                    <div class="item form-group">
                                        <label class="col-form-label col-md-3 col-sm-3 label-align">Tên dịch vụ
                                        </label>
                                        <div class="col-md-6 col-sm-6 ">
                                            <p class="form-control">${dv.tendv}</p>
                                        </div>
                                    </div>
                                    <div class="item form-group">
                                        <label class="col-form-label col-md-3 col-sm-3 label-align">Tên loại xe
                                        </label>
                                        <div class="col-md-6 col-sm-6 ">
                                            <p class="form-control">${lx.tenLX}</p>
                                        </div>
                                    </div>
                                    <div class="item form-group">
                                        <label class="col-form-label col-md-3 col-sm-3 label-align">Ngày nhận công việc
                                        </label>
                                        <div class="col-md-6 col-sm-6 ">
                                            <p class="form-control">
                                                <fmt:formatDate value="${ngaylam}" pattern="dd/MM/yyyy HH:mm"/>
                                            </p>
                                        </div>
                                    </div>
                                    <div class="item form-group">
                                        <label class="col-form-label col-md-3 col-sm-3 label-align">Tổng chi phí
                                        </label>
                                        <div class="col-md-6 col-sm-6 ">
                                            <p class="form-control">${bg.dongia} VND</p>
                                        </div>
                                    </div>
                                    <div class="ln_solid"></div>
                                    <div class="item form-group">
                                        <div class="col-md-6 col-sm-6 offset-md-3">
                                            <a type="button" class="btn btn-primary" onclick="showWarning('/admin/CongViec/DanhSachCongViec')" style="color: white">Trở về</a>
                                            <button type="submit" class="btn btn-success">Xác nhận</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%--!page-content--%>
        <%--footer--%>
        <jsp:include page="Footer.jsp"/>
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
