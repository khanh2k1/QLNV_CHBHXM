<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
        <div class="right_col" role="main" style="min-height: 100%;">
            <div class="">
                <div class="page-title">
                    <div class="title_left">
                        <h3>Thông tin nhân viên</h3>
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
                                <form:form id="demo-form2"
                                           class="form-horizontal form-label-left"
                                           commandName="nhanVien"
                                           method="post"
                                           action="/admin/NhanVien/DanhSachNhanVien/editNhanVien/${maNV}">

                                    <p class="error_message">${message_trangthai}</p>
                                    <div class="item form-group">
                                        <label class="col-form-label col-md-3 col-sm-3 label-align">Mã nhân viên
                                        </label>
                                        <div class="col-md-6 col-sm-6 ">
                                            <form:hidden path="maNV"/>
                                            <label class="form-control">${nhanVien.maNV}</label>
                                        </div>
                                    </div>
                                    <div class="item form-group">
                                        <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Trạng thái
                                            <span class="required">*</span>
                                        </label>
                                        <div class="col-md-6 col-sm-6 ">
                                            <form:select path="trangthai" class="form-control">
                                                <form:option value="Đang làm">Đang làm</form:option>
                                                <form:option value="Nghỉ">Nghỉ</form:option>
                                            </form:select>
                                            <p class="error_message">${message_trangthai}</p>
                                        </div>
                                    </div>
                                    <div class="item form-group">
                                        <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Loại nhân viên
                                            <span class="required">*</span>
                                        </label>
                                        <div class="col-md-6 col-sm-6 ">
                                            <form:hidden path="loaiNV"/>
                                            <label class="form-control">${tenLoaiNV}</label>
                                        </div>
                                    </div>
                                    <div class="item form-group">
                                        <label class="col-form-label col-md-3 col-sm-3 label-align">Tên nhân viên
                                            <span class="required">*</span>
                                        </label>
                                        <div class="col-md-6 col-sm-6 ">
                                            <form:input type="text" path="hoten" class="form-control"/>
                                            <p class="error_message">${message_hoten}</p>
                                        </div>
                                    </div>
                                    <div class="item form-group">
                                        <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Giới tính
                                            <span class="required">*</span>
                                        </label>
                                        <div class="col-md-6 col-sm-6 ">
                                            <div>
                                                <p style="position: absolute; font-size: 20px;top: 0px;">Nam</p><form:radiobutton path="gioitinh" value="Nam" class="form-control" cssStyle="position: absolute; width: 20px; top: -3px;left: 56px;"/>
                                                <p style="position: absolute; font-size: 20px;top: 0px; left: 95px">Nữ</p><form:radiobutton path="gioitinh" value="Nữ" class="form-control" cssStyle="position: absolute; width: 20px; top: -3px;left: 125px;"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="item form-group">
                                        <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Ngày sinh
                                            <span class="required"></span>
                                        </label>
                                        <div class="col-md-6 col-sm-6 ">
                                            <form:input type="date" path="ngaysinh" class="form-control"/>
                                            <p class="error_message">${message_age}</p>
                                        </div>
                                    </div>
                                    <div class="item form-group">
                                        <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Địa chỉ
                                            <span class="required">*</span>
                                        </label>
                                        <div class="col-md-6 col-sm-6 ">
                                            <form:input type="text" path="diachi" id="first-name" class="form-control"/>
                                            <p class="error_message">${message_diachi}</p>
                                        </div>
                                    </div>
                                    <div class="item form-group">
                                        <label class="col-form-label col-md-3 col-sm-3 label-align">Số điện thoại
                                            <span class="required">*</span>
                                        </label>
                                        <div class="col-md-6 col-sm-6 ">
                                            <form:input type="text" path="sdt" class="form-control"/>
                                            <p class="error_message">${message_sdt}</p>
                                        </div>
                                    </div>
                                    <div class="item form-group">
                                        <label class="col-form-label col-md-3 col-sm-3 label-align">Email
                                            <span class="required">*</span>
                                        </label>
                                        <div class="col-md-6 col-sm-6 ">
                                            <form:input type="text" path="email" class="form-control" title="text@example.com"/>
                                            <p class="error_message">${message_email}</p>
                                        </div>
                                    </div>
                                    <div class="item form-group">
                                        <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Lương cơ bản
                                            <span class="required">*</span>
                                        </label>
                                        <div class="col-md-6 col-sm-6 ">
                                            <form:input type="text" path="luongcoban" class="form-control"/>
                                            <p class="error_message">${message_luongcoban}</p>
                                        </div>
                                    </div>
                                    <div class="ln_solid"></div>
                                    <div class="item form-group">
                                        <div class="col-md-6 col-sm-6 offset-md-3">
                                            <a href="${pageContext.request.contextPath}/admin/NhanVien/DanhSachNhanVien" class="fa fa-backward" style="font-size: 25px">Trở về</a>

                                            <button type="submit" class="fa fa-save" style="font-size: 20px; margin-right:20px;margin-left:25px">Lưu</button>

<%--                                            neu la nhan vien quan ly thi nut NangLuc khong duoc hien ra--%>
                                            <c:if test="${nhanVien.loaiNV==1}">
                                                <a href="/admin/NangLuc/DanhSachNangLuc/${nhanVien.maNV}"
                                                   class="fa fa-check-square-o" hidden="hidden"
                                                   style="font-size: 35px" >Năng lực
                                                </a>
                                            </c:if>

<%--                                            neu la nhan vien sua chua thi nut NangLuc hien ra--%>
                                            <c:if test="${nhanVien.loaiNV==2}">
                                                <a href="/admin/NangLuc/DanhSachNangLuc/${nhanVien.maNV}"
                                                   class="fa fa-check-square-o"
                                                   style="font-size: 25px" >Năng lực
                                                </a>
                                            </c:if>
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
</body>

</html>
