<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
  String date = sdf.format(new Date());
%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="top_nav">
  <div class="nav_menu">
    <div class="nav toggle">
      <a id="menu_toggle"><i class="fa fa-bars"></i></a>
    </div>
    <nav class="nav navbar-nav">
      <ul class=" navbar-right">
        <p style="
        position: absolute;
                    font-size: 30px;
                    color: black;
                    font-weight: 500;" >
          Hôm nay là : <%=date%>
        </p>
        <li class="nav-item dropdown open" style="padding-left: 15px;">

          <a href="/nhanvien/logout" type="button" class="btn btn-dark">Thoát</a>
        </li>
      </ul>
    </nav>
  </div>
</div>