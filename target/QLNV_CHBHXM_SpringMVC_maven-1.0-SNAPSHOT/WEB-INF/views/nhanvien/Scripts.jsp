<%--
  Created by IntelliJ IDEA.
  User: tanle
  Date: 30/04/2022
  Time: 1:54 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: tanle
  Date: 21/04/2022
  Time: 2:42 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Bootstrap -->
<link rel="stylesheet" href="/khanh/vendors/bootstrap/dist/css/bootstrap.min.css">
<!-- Font Awesome -->
<link href="/khanh/vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
<!-- NProgress -->
<link href="/khanh/vendors/nprogress/nprogress.css" rel="stylesheet">
<!-- Custom styling plus plugins -->
<link href="/khanh/build/css/custom.min.css" rel="stylesheet">
<!-- jQuery -->
<script src="/khanh/vendors/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap -->
<script src="/khanh/vendors/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
<!-- FastClick -->
<script src="/khanh/vendors/fastclick/lib/fastclick.js"></script>
<!-- NProgress -->
<script src="/khanh/vendors/nprogress/nprogress.js"></script>
<!-- iCheck -->
<script src="/khanh/vendors/iCheck/icheck.min.js"></script>
<!-- Datatables -->
<script src="/khanh/vendors/datatables.net/js/jquery.dataTables.min.js"></script>
<script src="/khanh/vendors/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
<script src="/khanh/vendors/datatables.net-buttons/js/dataTables.buttons.min.js"></script>
<script src="/khanh/vendors/datatables.net-buttons-bs/js/buttons.bootstrap.min.js"></script>
<script src="/khanh/vendors/datatables.net-buttons/js/buttons.flash.min.js"></script>
<script src="/khanh/vendors/datatables.net-buttons/js/buttons.html5.min.js"></script>
<script src="/khanh/vendors/datatables.net-buttons/js/buttons.print.min.js"></script>
<script src="/khanh/vendors/datatables.net-fixedheader/js/dataTables.fixedHeader.min.js"></script>
<script src="/khanh/vendors/datatables.net-keytable/js/dataTables.keyTable.min.js"></script>
<script src="/khanh/vendors/datatables.net-responsive/js/dataTables.responsive.min.js"></script>
<script src="/khanh/vendors/datatables.net-responsive-bs/js/responsive.bootstrap.js"></script>
<script src="/khanh/vendors/datatables.net-scroller/js/dataTables.scroller.min.js"></script>
<script src="/khanh/vendors/jszip/dist/jszip.min.js"></script>
<script src="/khanh/vendors/pdfmake/build/pdfmake.min.js"></script>
<script src="/khanh/vendors/pdfmake/build/vfs_fonts.js"></script>

<!-- Custom Theme Scripts -->
<script src="/khanh/build/js/custom.min.js"></script>

<script>
    function hideshow(){
        var password = document.getElementById("password1");
        var slash = document.getElementById("slash");
        var eye = document.getElementById("eye");

        if(password.type === 'password'){
            password.type = "text";
            slash.style.display = "block";
            eye.style.display = "none";
        }
        else{
            password.type = "password";
            slash.style.display = "none";
            eye.style.display = "block";
        }
    }

</script>


