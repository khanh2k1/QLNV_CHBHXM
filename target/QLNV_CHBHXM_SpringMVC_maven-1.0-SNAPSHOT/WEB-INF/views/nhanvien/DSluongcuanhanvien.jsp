<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--package tu viet--%>

<%--package tu viet--%>
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
                            </div>
                            <div class="x_content">

                                <div class="x_content">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <div class="card-box table-responsive">
                                                <form:form
                                                        action="${pageContext.request.contextPath}/nhanvien/Luong/DanhSachLuongTheoThang/${thang_nam_songaythuclam.thang}_${thang_nam_songaythuclam.nam}"
                                                        method="post" modelAttribute="thang_nam_songaythuclam">
                                                    <div class="">
                                                        <h3>Tổng doanh thu của cửa hàng trong
                                                            tháng ${thang_nam_songaythuclam.thang}: ${tongdoanhthutheothang}
                                                            VND</h3>
                                                        <form:select path="thang"
                                                                     cssClass="form-control col-md-1 col-sm-1">
                                                            <c:forEach items="${listThang}" var="thang_1">
                                                                <option value="${thang_1}"
                                                                        class="select2-dropdown">${thang_1}</option>
                                                            </c:forEach>
                                                        </form:select>

                                                        <form:select path="nam"
                                                                     cssClass="form-control col-md-1 col-sm-1">
                                                            <c:forEach items="${listNam}" var="nam_1">
                                                                <option value="${nam_1}">${nam_1}</option>
                                                            </c:forEach>
                                                        </form:select>

                                                        <button type="submit" class="btn btn-primary">Tra cứu
                                                        </button>

                                                        <h3>Bảng lương
                                                            tháng ${thang_nam_songaythuclam.thang}/${thang_nam_songaythuclam.nam}</h3>
                                                    </div>
                                                    <table id="datatable"
                                                           class="table table-striped table-bordered"
                                                           style="width:100%">
                                                        <thead>
                                                        <tr>
                                                            <th>Mã nhân viên</th>
                                                            <th>Họ Tên nhân viên</th>
                                                            <th>Số ngày thực làm</th>
                                                            <th>Lương cơ bản</th>
                                                            <th>Tổng doanh thu</th>
                                                            <th>Tổng lương nhận được</th>
                                                        </tr>
                                                        </thead>

                                                        <tbody>
                                                            <%--                                                                    <tg:warningModal/>--%>
                                                        <c:forEach items="${listLuongNhanVienTheoThang}"
                                                                   var="l">
                                                            <tr>
                                                                <td>${l.manv}</td>
                                                                <td>${l.hoten}</td>
                                                                <td>

                                                                    <a href="">
                                                                            ${l.songaythuclam} / <span>20 ngày</span></></p>
                                                                    </a>

                                                                </td>
                                                                <td>${l.luongcoban}</td>
                                                                <td>${l.tongdoanhthu} vnd</td>
                                                                <td><p><fmt:formatNumber type="number"
                                                                                         maxFractionDigits="0"
                                                                                         value="${l.luongthuclanh}"/>
                                                                    vnd</p>
                                                                </td>
                                                            </tr>
                                                        </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </form:form>
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


<script>
    function showWarning(link) {
        var ms = confirm('Ban co muon tiep tuc ?')
        if (ms === true) {
            window.location.href = link;
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
