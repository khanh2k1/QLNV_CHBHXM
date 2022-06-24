<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--package tu viet--%>

<%--package tu viet--%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <title>Danh sách loại xe</title>
    <%--css--%>
    <jsp:include page="Style.jsp"></jsp:include>
    <style>
        tr:hover{
            background-color: #f5f5f5;
            transform: scale(1.02);
            box-shadow: 2px 2px 12px rgba(0,0,0,0.7), -1px -1px 8px rgba(0,0,0,0.2);
            cursor: pointer;
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
                                                <div class="x_title">
                                                    <h2><a href="/admin/LoaiXe/DanhSachLoaiXe/addLoaiXe" class="btn btn-primary" style="font-weight: 700">THÊM
                                                        LOẠI XE</a></h2>
                                                    <p>${message}</p>
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
                                                                        <th>Mã loại xe</th>
                                                                        <th>Tên loại xe</th>
                                                                    </tr>
                                                                    </thead>


                                                                    <tbody>

<%--                                                                    <tg:warningModal/>--%>
                                                                    <c:forEach items="${listLoaiXe}" var="lx">
                                                                        <tr onclick="location.href='/admin/LoaiXe/DanhSachLoaiXe/editLoaiXe/${lx.maLX}';">
                                                                            <td>${lx.maLX}</td>
                                                                            <td>${lx.tenLX}</td>
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
                                        <%--page-content--%>
                                        <jsp:include page="Footer.jsp"/>
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


<script>
    function showWarning(link) {
    var ms  = confirm('Ban co muon tiep tuc ?')
        if (ms===true) {
            window.location.href =link;
        }
        //
        // $('#item-name').text(name);
        // $('#link-to').attr('href', link);
        // $('#warningModal').modal('show');
    }
</script>
<jsp:include page="Scripts.jsp"></jsp:include>
</body>

</html>
