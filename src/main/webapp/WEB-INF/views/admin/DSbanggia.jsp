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

  <title>Danh sách bảng giá</title>
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
                          <div class="clearfix"></div>
                        </div>
                        <div class="x_content">
                          <h2>
                            <a href="${pageContext.request.contextPath}/admin/BangGia/DanhSachBangGia/ThemBangGia" class="btn btn-primary" style="font-weight: 700">THÊM
                            BẢNG GIÁ </a>
                          </h2>
                          <div class="row">
                            <div class="col-sm-12">

                              <table id="datatable"

                                     class="table_custom table table-striped table-bordered dataTable no-footer"
                                     style="width:100%" role="grid"
                                     aria-describedby="datatable_info">
                                <thead>
                                <tr role="row">
                                  <th class="sorting_asc" tabindex="0"
                                      aria-controls="datatable" rowspan="1"
                                      colspan="1" aria-sort="ascending"
                                      aria-label="Name: activate to sort column descending"
                                  >Tên loại xe
                                  </th>
                                  <th class="sorting" tabindex="0"
                                      aria-controls="datatable" rowspan="1"
                                      colspan="1"
                                      aria-label="Position: activate to sort column ascending"
                                  >Tên dịch vụ
                                  </th>
                                  <th class="sorting_asc" tabindex="0"
                                      aria-controls="datatable" rowspan="1"
                                      colspan="1" aria-sort="ascending"
                                      aria-label="Name: activate to sort column descending"
                                  >Đơn giá
                                  </th>
                                  <th class="sorting" tabindex="0"
                                      aria-controls="datatable" rowspan="1"
                                      colspan="1"
                                      aria-label="Position: activate to sort column ascending"
                                  >Trạng thái
                                  </th>
                                </tr>
                                </thead>

                                <tbody>
                                <c:set var="count" value="0" scope="page"/>
                                <c:forEach items="${listBangGia_custom}" var="bg">
                                  <tr onclick="location.href='/admin/BangGia/DanhSachBangGia/editBangGia/${bg.madg}'">
                                    <td>${bg.tenlx}</td>
                                    <td>${bg.tendv}</td>
                                    <td><fmt:formatNumber type="number"
                                                          maxFractionDigits="0"
                                                          value="${bg.dongia}"/>VND
                                    </td>
                                    <td>${bg.trangthai}</td>

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
