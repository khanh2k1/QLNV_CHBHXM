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
    <%--css--%>
    <jsp:include page="Style.jsp"></jsp:include>
    <style>
        .error_message {
            color: red;
            font-size: 14px;
            font-weight: 600;
        }
    </style>
</head>

<body class="nav-md">
<div class="container body">
    <div class="main_container">
        <%--side_bar--%>
        <jsp:include page="Navbar.jsp"></jsp:include>
        <%--/side_bar--%>

        <%--top-navigation--%>
        <jsp:include page="Topnav.jsp"></jsp:include>
        <%--/top-navigation--%>

        <%--page-content--%>
        <div class="right_col" role="main" style="min-height: 100%;">
            <div class="">
                <div class="page-title">
                    <div class="title_left">
                        <h3>THÊM BẢNG GIÁ DỊCH VỤ</h3>
                        <p class="error_message">${message_banggia}</p>
                    </div>
                </div>
                <div class="clearfix"></div>
                <div class="row">
                    <div class="col-md-6 col-sm-6 ">
                        <div class="x_panel">

                            <div class="x_content">
                                <br>
                                <form:form class="form-horizontal form-label-left"
                                           action="/admin/BangGia/DanhSachBangGia/ThemBangGia"
                                           modelAttribute="bg"
                                           method="post">

                                    <%--dich vu--%>
                                    <div class="form-group row">
                                        <label class="control-label col-md-3 col-sm-3 ">TÊN DỊCH VỤ</label>
                                        <div class="col-md-9 col-sm-9 ">

                                            <form:select path="madv" class="select2_single form-control" tabindex="-1">
                                                <c:forEach items="${listDichVu}" var="dv">
                                                    <form:option value="${dv.madv}" label="${dv.tendv}"/>
                                                </c:forEach>
                                            </form:select>

                                        </div>
                                    </div>

                                    <div class="ln_solid"></div>

                                    <%--dich vu--%>
                                    <%--loai xe--%>
                                    <div class="form-group row">
                                        <label class="control-label col-md-3 col-sm-3 ">TÊN LOẠI XE</label>
                                        <div class="col-md-9 col-sm-9 ">
                                                <%-- path la dung de lay du lieu ma ng dung chon--%>
                                            <form:select path="malx" class="select2_single form-control" tabindex="-1">
                                                <c:forEach items="${listLoaiXe}" var="lx">
                                                    <form:option value="${lx.maLX}" label="${lx.tenLX}"/>
                                                </c:forEach>
                                            </form:select>
                                        </div>
                                    </div>
                                    <div class="ln_solid"></div>
                                    <div class="form-group row">
                                        <label class="control-label col-md-3 col-sm-3 ">ĐƠN GIÁ</label>
                                        <div class="col-md-9 col-sm-9 ">

                                            <form:input type="number" class="select2_single form-control" tabindex="-1"
                                                        path="dongia"/>
                                            <p class="error_message">${message_dongia}</p>

                                        </div>
                                    </div>

                                    <div class="ln_solid"></div>

                                    <div class="form-group row">
                                        <label class="control-label col-md-3 col-sm-3 ">TRẠNG THÁI </label>
                                        <div class="col-md-9 col-sm-9 ">
                                            <form:select path="trangthai" class="select2_single form-control"
                                                         tabindex="-1">
                                                <form:option value="Đang dùng">Đang dùng</form:option>
                                            </form:select>
                                        </div>
                                    </div>

                                    <div class="ln_solid"></div>

                                    <%--dich vu--%>
                                    <div class="form-group">
                                        <div class="col-md-9 col-sm-9  offset-md-3">
                                            <a href="/admin/BangGia/DanhSachBangGia"
                                               class="btn btn-primary">TRỞ VỀ</a>

                                            <button type="submit" class="btn btn-success">THÊM</button>
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
        <jsp:include page="Footer.jsp"/>
        <%--!footer--%>
        <jsp:include page="Scripts.jsp"/>

</body>

</html>
