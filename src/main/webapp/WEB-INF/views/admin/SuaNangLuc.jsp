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

    <title>Danh sách nhân viên</title>
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

                                                CẬP NHẬT NĂNG LỰC CHO NHÂN VIÊN<p
                                                    style="font-weight: 700; color: red">${maNV}</p>

                                            </div>
                                            <div class="x_content">
                                                <div class="row">
                                                    <div class="col-sm-12">
                                                        <div class="card-box table-responsive">
                                                            <form:form
                                                                    action="/admin/NangLuc/DanhSachNangLuc/showNangLuc/${maNV}"
                                                                    method="post"
                                                                    modelAttribute="nangLuc">
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
                                                                        <c:forEach items="${listDichVu}" var="dv">
                                                                        <tr>
                                                                            <div style="font-size: 25px;font-family: cursive;">
                                                                                <td>
                                                                                    <form:checkbox path="listMaDV" value="${dv.madv}" cssStyle="height: 20px; width: 20px"/>
                                                                                </td>
                                                                               <td>
                                                                                   <c:out value="${dv.tendv}"/>
                                                                               </td>
                                                                            </div>
                                                                        </tr>
                                                                        </c:forEach>

                                                                    </tbody>
                                                                </table>
                                                                <h4>
                                                                    <input type="submit" value="LƯU" onclick=""
                                                                           class="btn btn-primary"/>
                                                                    <a href="/admin/NangLuc/DanhSachNangLuc/${maNV}"
                                                                       class="btn btn-danger" style="font-weight: 700">TRỞ VỀ</a>
                                                                </h4>
                                                            </form:form>
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
