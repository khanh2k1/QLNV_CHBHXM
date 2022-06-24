<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <title>Danh sách công việc</title>
    <%--css--%>
    <jsp:include page="Style.jsp"></jsp:include>
    <style>
        .btn_custom{
            position: absolute;
            left: 340px;
            top: 16px;
            height: 37px;
            width: 170px;
            font-size: 19px;
            font-weight: 500;
            font-family: cursive;
        }
        .btn_custom:hover{
            background-color: black;
            color: whitesmoke;
            cursor: pointer;
        }
        .checkbox_custom{
            width: 100%;
            height: 25px;
        }
        .checkbox_custom:hover{
            cursor: pointer;
        }
    </style>
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
        <div class="right_col" role="main">
            <div class="">
                <div class="clearfix"></div>

                <div class="row">
                    <div class="col-md-12 col-sm-12 ">
                        <div class="x_panel">
                            <div class="x_title">

                                <div class="clearfix"></div>
                            </div>
                            <div class="x_content">
                                <div class="x_content">
                                    <div class="row">
                                        <div class="col-md-12 col-sm-12 ">
                                            <div class="x_panel">
                                                <div class="x_title">
                                                    <h2><a href="${pageContext.request.contextPath}/admin/CongViec/DanhSachCongViec/ThemCongViec/DanhSachBangGia"
                                                           class="btn btn-primary" style="font-weight: 700">THÊM
                                                        CÔNG VIỆC CHO NHÂN VIÊN </a></h2>
                                                    <div class="clearfix"></div>
                                                </div>
                                                <div class="x_content">
                                                    <div class="row">
                                                        <div class="col-sm-12">

                                                            <div class="card-box table-responsive">

                                                                <table id="datatable"
                                                                       class="table table-striped table-bordered"
                                                                       style="width:100%">

                                                                    <thead>
                                                                    <tr>
                                                                        <th>Họ và tên nhân viên</th>
                                                                        <th>Tên dịch vụ</th>
                                                                        <th>Tên loại xe</th>
                                                                        <th>Đơn giá</th>
                                                                        <th>Ngày làm</th>
                                                                        <th></th>
                                                                    </tr>
                                                                    </thead>


                                                                    <tbody>
                                                                    <c:forEach items="${listCongViecCuaNhanVien}" var="cv">
                                                                        <tr>
                                                                            <td>${cv.hoten}</td>
                                                                            <td>${cv.tendv}</td>
                                                                            <td>${cv.tenlx}</td>
                                                                            <td>${cv.dongia}</td>
                                                                            <td>
                                                                                <f:formatDate value="${cv.ngaylam}" type="both"
                                                                                dateStyle="short" timeStyle="short"/>
                                                                            </td>
                                                                            <td>
                                                                                <a href="/admin/CongViec/DanhSachCongViec/XoaCongViec/${cv.ngaylam}_${cv.manv}_${cv.dongia}" class="btn btn-success btn">Hoàn thành</a>
                                                                            </td>
                                                                        </tr>
                                                                    </c:forEach>

                                                                    </tbody>
                                                                </table>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="Scripts.jsp"/>
</body>

</html>
