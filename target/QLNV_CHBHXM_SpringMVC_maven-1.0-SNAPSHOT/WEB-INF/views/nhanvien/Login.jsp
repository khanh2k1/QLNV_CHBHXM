<%@ page import = "java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Đăng nhập</title>
    <%--css--%>
    <style>
        .wall{
            background-image: url('/khanh/production/images/1.jpg') !important;
        }
        .wall image{
            width: 100%;
            height: auto;
        }
        .error_message{
            color: whitesmoke;
            font-size: 16px;
            font-weight: 600;
        }
        .login_content h1 {
            font: normal 25px Helvetica, Arial, sans-serif;
            letter-spacing: -1px;
            line-height: 30px;
            margin: 10px 0 30px;
            font-weight: 600;

        }
    </style>
    <!-- Bootstrap -->
    <link href="/khanh/vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="/khanh/vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="/khanh/vendors/nprogress/nprogress.css" rel="stylesheet">
    <!-- Animate.css -->
    <link href="/khanh/vendors/animate.css/animate.min.css" rel="stylesheet">
    <!-- Custom Theme Style -->
    <link href="/khanh/build/css/custom.min.css" rel="stylesheet">

    <!--====== Bootstrap css ======-->
    <link rel="stylesheet" href="/khanh1/css/bootstrap.min.css">
    <!--====== Line Icons css ======-->
    <link rel="stylesheet" href="/khanh1/css/LineIcons.css">
    <!--====== Magnific Popup css ======-->
    <link rel="stylesheet" href="/khanh1/css/magnific-popup.css">
    <!--====== Slick css ======-->
    <link rel="stylesheet" href="/khanh1/css/slick.css">
    <!--====== Animate css ======-->
    <link rel="stylesheet" href="/khanh1/css/animate.css">
    <!--====== Default css ======-->
    <link rel="stylesheet" href="/khanh1/css/default.css">
    <!--====== Style css ======-->
    <link rel="stylesheet" href="/khanh1/css/style.css">

    <%
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String date = sdf.format(new Date());
    %>
</head>

<body class="login wall">
<div>
    <a class="hiddenanchor" id="signup"></a>
    <a class="hiddenanchor" id="signin"></a>

    <div class="login_wrapper wall">
        <div class="animate form login_form">
            <section class="login_content">

                <form:form action="/nhanvien/login" commandName="nhanvien" method="post">
                    <h1 style="
                    font-size: 30px;
                        color: whitesmoke;
                        letter-spacing: -1px;
                        line-height: 30px;
                        margin: 10px 0 30px;
                        font-weight: 500;" >

                        Hôm nay là : <%=date%>
                    </h1>
                    <h1 style="
                    color: whitesmoke;
                        font: normal 25px Helvetica, Arial, sans-serif;
                        letter-spacing: -1px;
                        line-height: 30px;
                        margin: 10px 0 30px;
                        font-weight: 600;">

                        ĐĂNG NHẬP VỚI CHỨC NĂNG NHÂN VIÊN
                    </h1>
                    <div>
                        <form:input path="tendangnhap" type="text" class="form-control"
                                    placeholder="Tên đăng nhập"></form:input>
                        <p class="error_message">${message_tendangnhap}</p>
                        <form:hidden path="quyen"/>
                        <form:hidden path="maNV"/>
                    </div>
                    <div>
                        <form:input path="matkhau" type="password" class="form-control"
                                    placeholder="Mật khẩu"></form:input>
                        <p class="error_message">${message_taikhoan}</p>
                    </div>
                    <p style="font-size: 16px; font-weight: 700">${message}</p>
                    <div>
                        <button class="btn btn-primary submit">Log in</button>
                        <h2>
                            <a href="/admin/login" class="btn btn-danger">Chức năng quản lí</a>
                        </h2>
                    </div>

                </form:form>
            </section>
        </div>
    </div>
</div>
</body>
</html>
