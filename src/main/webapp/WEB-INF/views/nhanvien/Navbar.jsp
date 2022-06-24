<%@ page import="qlnv.entity.TAIKHOAN" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="qlnv.jdbc.JDBCConnection" %>
<%@ page import="java.sql.CallableStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.time.LocalDateTime" %>
<%--
  Created by IntelliJ IDEA.
  User: tanle
  Date: 21/04/2022
  Time: 2:36 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<style>

    .menu_section>ul {
        margin-top: 10px;
        display: block;
        font-size: 17px;
        font-family: cursive;
    }
    .a-custom{
        color: greenyellow;
        font-size: 15px;
        padding: 9px;
    }

</style>
<div class="col-md-3 left_col">
    <div class="left_col scroll-view">
        <form:form action=""></form:form>
        <div class="navbar nav_title" style="border: 0;">

            <%
                TAIKHOAN tk = (TAIKHOAN) session.getAttribute("nhanvien");
                String sql = "select HoTen, MaNV from NhanVien where MaNV=?";
                String tenNV="";
                String maNV="";
                try (
                        Connection con = JDBCConnection.getJDBCConnection();
                        CallableStatement cstm = con.prepareCall(sql);) {
                    // Set parameter values
                    cstm.setString(1, tk.getTendangnhap());

                    // Executes the Procedure statement
                    try (ResultSet rs = cstm.executeQuery();) {
                        while (rs.next()) {
                            tenNV = rs.getString("HoTen");
                            maNV = rs.getString("MaNV");
                        }
                    }


                    session.setAttribute("tenNV",tenNV);
                    session.setAttribute("maNV", maNV);
                }
                // lay thang va nam hien tai
                int thang = LocalDateTime.now().getMonthValue();
                int nam = LocalDateTime.now().getYear();

                session.setAttribute("thang",thang);
                session.setAttribute("nam",nam);
            %>
            <span class="site_title" style="font-size: 20px;font-weight: 600;font-family: cursive; color: #2bdbdc !important;">
                <a href="/nhanvien/NhanVien/editNhanVien/${sessionScope.maNV}">
                    ${sessionScope.tenNV}
                </a>
            </span>


        </div>
        <div class="clearfix"></div>

        <!-- /menu profile quick info -->

        <br>

        <!-- sidebar menu -->
        <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
            <div class="menu_section active">
                <ul class="nav side-menu" style="">
                    <li class="active"><a href="/nhanvien/CongViec/DanhSachCongViec"><i class="fa fa-home"></i> Trang chủ <span class="fa fa-chevron-down"></span></a>
                        <ul class="nav child_menu" style="display: block;">
                            <li><a href="${pageContext.request.contextPath}/nhanvien/CongViec/DanhSachCongViec" class="a-custom">Công việc của tôi</a></li>
                        </ul>
                    </li>
                    <li class=""><a><i class="fa fa-edit"></i> Danh sách <span class="fa fa-chevron-down"></span></a>
                        <ul class="nav child_menu" style="display: none;">
                            <li><a href="${pageContext.request.contextPath}/nhanvien/LoaiXe/DanhSachLoaiXe" class="a-custom">Danh sách loại xe</a></li>
                            <li><a href="${pageContext.request.contextPath}/nhanvien/DichVu/DanhSachDichVu" class="a-custom">Danh sách dịch vụ</a></li>
                            <li><a href="${pageContext.request.contextPath}/nhanvien/BangGia/DanhSachBangGia" class="a-custom">Danh sách bảng giá</a></li>
                            <li><a href="${pageContext.request.contextPath}/nhanvien/NangLuc/DanhSachNhanVien" class="a-custom">Danh sách năng lực</a></li>
                        </ul>
                    </li>
                    <li class=""><a><i class="fa fa-money"></i> Thu nhập <span class="fa fa-chevron-down"></span></a>
                        <ul class="nav child_menu" style="display: none;">
                            <li><a href="${pageContext.request.contextPath}/nhanvien/Luong/DanhSachLuongTheoThang/${thang}_${nam}" class="a-custom">Lương của nhân viên</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
        <!-- /sidebar menu -->
    </div>
</div>