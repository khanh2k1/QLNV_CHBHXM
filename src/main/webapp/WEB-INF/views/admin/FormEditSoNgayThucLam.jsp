<%--
  Created by IntelliJ IDEA.
  User: tanle
  Date: 03/06/2022
  Time: 10:13 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
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
                                modelAttribute="luong"
                                action="/admin/Luong/DanhSachLuongTheoThang/${manv}/${thang}/${nam}/${songaythuclam}"
                                method="post" onsubmit="return validateForm_custom()">


                            <div class="item form-group">
                                <label class="col-form-label col-md-3 col-sm-3 label-align">Mã nhân viên
                                </label>
                                <div class="col-md-6 col-sm-6 ">
                                    <form:hidden path="maNV"/>
                                    <label class="form-control">${luong.maNV}</label>
                                </div>
                            </div>

                            <div class="item form-group">
                                <label class="col-form-label col-md-3 col-sm-3 label-align">Tháng/Năm
                                </label>
                                <div class="col-md-6 col-sm-6 ">
                                    <form:hidden path="nam"/>
                                    <label class="form-control">${luong.thang}/ ${luong.nam}</label>
                                </div>
                            </div>

                            <div class="item form-group">
                                <label class="col-form-label col-md-3 col-sm-3 label-align">Tổng doanh thu
                                </label>
                                <div class="col-md-6 col-sm-6 ">
                                    <form:hidden path="tongdoanhthu"/>
                                    <label class="form-control">${luong.tongdoanhthu} VND</label>
                                </div>
                            </div>

                            <div class="item form-group">
                                <label class="col-form-label col-md-3 col-sm-3 label-align"> Số ngày thực làm
                                </label>
                                <div class="col-md-6 col-sm-6 ">
                                    <form:hidden path="tongdoanhthu"/>
                                    <form:input path="songaythuclam" class="form-control"/>
                                </div>
                            </div>
                            <div class="item form-group">
                                <div class="col-md-6 col-sm-6 offset-md-3">
                                    <a type="button" class="btn btn-primary"
                                       onclick="showWarning('/admin/Luong/DanhSachLuongTheoThang/${thang}_${nam}')" style="color: white">Trở
                                        về</a>

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

<script>

    function validateForm_custom() {
        var x = document.forms["luong"]["songaythuclam"].value;
        if (x == "" || x == null) {
            alert("Số ngày thực làm không được để trống !");
            return false;
        }
        if (x < 0 || x > 20) {
            alert("Số ngày thực làm chỉ từ 0 đến 20 !");
            return false;
        }
    }

        function showWarning(link) {
            var ms = confirm('Bạn có muốn tiếp tục ?')
            if (ms === true) {
                window.location.href = link;

            }
        }
</script>
</script>
</body>
</html>
