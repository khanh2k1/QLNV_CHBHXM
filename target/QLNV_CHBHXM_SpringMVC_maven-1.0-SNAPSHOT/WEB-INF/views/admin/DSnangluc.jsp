<%@ page import="qlnv.entity.NANGLUC" %>
<%@ page import="qlnv.dao.NangLucDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="qlnv.dao.DichVuDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.ArrayList.*" %>
<%@ page import="qlnv.entity.DICHVU" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <title>Danh sách năng lực</title>
    <%--css--%>
    <jsp:include page="Style.jsp"></jsp:include>
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
                            <div class="x_content">
                                <div class="row">
                                    <div class="col-md-12 col-sm-12 ">
                                        <div class="x_panel">
                                            <div class="x_title" style="font-size: 25px">
                                                Năng lực của nhân viên <p
                                                    style="font-weight: 700; color: red">${maNV}</p>

                                            </div>
                                            <div class="x_content">
                                                <div class="row">
                                                    <div class="col-sm-12">
                                                        <a href="/admin/NangLuc/DanhSachNangLuc/editNangLuc/${maNV}"
                                                           type="button" class="btn btn-primary">SỬA NĂNG LỰC</a>
                                                        <a href="/admin/NhanVien/DanhSachNhanVien"
                                                           class="btn btn-danger" style="font-weight: 700">TRỞ VỀ</a>
                                                        <div class="card-box table-responsive">
                                                            <form
                                                                    action="/admin/NangLuc/DanhSachNangLuc/${maNV}"
                                                                    method="post">

                                                                <table id="datatable"
                                                                       class="table table-striped table-bordered"
                                                                       style="width:100%">
                                                                    <thead>
                                                                    <tr>
                                                                        <th>Tên dịch vụ</th>
                                                                        <th>Đã sở hữu</th>
                                                                    </tr>

                                                                    </thead>

                                                                    <tbody>

                                                                    <c:forEach items="${listDichVu}" var="i"
                                                                               varStatus="dv"
                                                                               begin="0"
                                                                               end="${listDichVu.size()}">

                                                                        <tr>
                                                                            <td><c:out value="${i.tendv}"></c:out></td>
                                                                            <td>

                                                                                <c:forEach items="${listNangLucCuaMaNV.listMaDV}"
                                                                                           var="j"
                                                                                           begin="0"
                                                                                           end="${listNangLucCuaMaNV.listMaDV.size()}"
                                                                                           varStatus="nl">
                                                                                    <c:if test="${i.madv == j}">
                                                                                        <input name="" type="checkbox"
                                                                                               checked
                                                                                               style="height: 20px; width: 20px;"/>
                                                                                    </c:if>
                                                                                </c:forEach>
                                                                            </td>
                                                                        </tr>
                                                                    </c:forEach>

                                                                    </tbody>
                                                                </table>
                                                            </form>

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
<jsp:include page="Scripts.jsp"></jsp:include>
</body>

</html>
