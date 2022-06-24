package qlnv.jdbc;

/*import qlnv.dao.NhanVienDAO;
import qlnv.entity.NHANVIEN;*/



import qlnv.dao.*;
import qlnv.entity.*;
import qlnv.entityCustom.CONGVIECCUANHANVIEN;

import java.math.BigDecimal;
import java.sql.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JDBCConnection {
    public static Connection getJDBCConnection() {

        Connection connection = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String user = "sa";
            String pass = "25032001";
            String url = "jdbc:sqlserver://DESKTOP-TUEEJN0:1433;databaseName=QLNV_CHBHXM";
            connection = DriverManager.getConnection(url, user, pass);

        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return connection;
    }


    public static void main(String[] args) throws SQLException {
//        LuongDAO.khoiTaoLuongChoNhanVien("N21NVBT000");
        System.out.println("ma dich vu da ton tai trong 2 bang : "+ BangGiaDAO.check_malx_banggia(1) + "===" + 1);

    }
}

