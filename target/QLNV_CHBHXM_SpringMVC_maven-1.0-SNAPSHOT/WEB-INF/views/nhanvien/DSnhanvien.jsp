<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <title>Danh sách nhân viên</title>
    <%--css--%>
    <jsp:include page="Style.jsp"></jsp:include>
    <style>
        button_custom {
            width: 15px;
            font-weight: 700;
        }

        a.button_custom {
            font-size: 11px;
            width: 180px;
            font-weight: 600;
        }
        tr:hover{
            background-color: #f5f5f5;
            transform: scale(1.02);
            box-shadow: 2px 2px 12px rgba(0,0,0,0.7), -1px -1px 8px rgba(0,0,0,0.2);
            cursor: pointer;
            font-weight: 700;
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
                                                </div>
                                                <div class="x_content">
                                                    <div class="row">
                                                        <div class="col-sm-12">
                                                            <table id="datatable"
                                                                   class="table table-striped table-bordered dataTable no-footer table_custom"
                                                                   style="width:100%" role="grid"
                                                                   aria-describedby="datatable_info">
                                                                <thead>
                                                                <tr role="row">
                                                                    <th class="sorting_asc" tabindex="0"
                                                                        aria-controls="datatable" rowspan="1"
                                                                        colspan="1" aria-sort="ascending"
                                                                        aria-label="Name: activate to sort column descending"
                                                                        style="width: 153px;">Mã nhân viên
                                                                    </th>
                                                                    <th class="sorting" tabindex="0"
                                                                        aria-controls="datatable" rowspan="1"
                                                                        colspan="1"
                                                                        aria-label="Position: activate to sort column ascending"
                                                                        style="width: 250px;">Họ và tên
                                                                    </th>
                                                                    <th class="sorting_asc" tabindex="0"
                                                                        aria-controls="datatable" rowspan="1"
                                                                        colspan="1" aria-sort="ascending"
                                                                        aria-label="Name: activate to sort column descending"
                                                                        style="width: 153px;">Ngày sinh
                                                                    </th>
                                                                    <th class="sorting_asc" tabindex="0"
                                                                        aria-controls="datatable" rowspan="1"
                                                                        colspan="1" aria-sort="ascending"
                                                                        aria-label="Name: activate to sort column descending"
                                                                        style="width: 153px;">Trạng thái
                                                                    </th>

                                                                </tr>
                                                                </thead>

                                                                <tbody>
                                                                <c:set var="count" value="0" scope="page"/>
                                                                <c:forEach items="${listNhanVien}" var="nv">
                                                                    <tr onclick="location.href='/nhanvien/NangLuc/DanhSachNhanVien/DanhSachNangLuc/${nv.maNV}';">
                                                                        <td>${nv.maNV}</td>
                                                                        <td>${nv.hoten}</td>
                                                                        <td>${nv.ngaysinh}</td>
                                                                        <td>${nv.trangthai}</td>
                                                                    </tr>
                                                                </c:forEach>

                                                                </tbody>
                                                            </table>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <%--page-content--%>
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
<jsp:include page="Scripts.jsp"></jsp:include>

<%--// alert thanh cong--%>
<script>
    function thanhCong() {

        alert("Thành công !");
    }

    // that bai
    function thatBai() {
        alert("Thất bại !");
    }
</script>
</body>

</html>
