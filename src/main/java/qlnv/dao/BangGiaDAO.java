package qlnv.dao;

import qlnv.entity.*;
import qlnv.jdbc.JDBCConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BangGiaDAO {
    public static List<BANGGIA> getList() throws SQLException {
        String sql = "select*from BangGia";
        try (
                Connection connection = JDBCConnection.getJDBCConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                List<BANGGIA> list = new ArrayList<>();
                while (resultSet.next()) {
                    BANGGIA bg = new BANGGIA();
                    bg.setMadg(resultSet.getInt("MaDG"));
                    bg.setMadv(resultSet.getInt("MaDV"));
                    bg.setMalx(resultSet.getInt("MaLX"));
                    bg.setDongia(resultSet.getInt("DonGia"));
                    bg.setTrangthai(resultSet.getString("TrangThai"));

                    list.add(bg);
                }
                return list;
            }
        }
    }

    public static boolean delete(int malx) throws SQLException {
        String sql = "set from BangGia where MaLX = ?";
        try (
                Connection connection = JDBCConnection.getJDBCConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setInt(1, malx);
            return preparedStatement.executeUpdate() > 0;
        }

    }

    public static int update(BANGGIA bg) throws SQLException {

//        try (
//                Connection connection = JDBCConnection.getJDBCConnection();
//                PreparedStatement preparedStatement = connection.prepareStatement(sql);
//        ) {
//            preparedStatement.setInt(1, bg.getDongia());
//            preparedStatement.setString(2, bg.getTrangthai());
//            preparedStatement.setInt(3, bg.getMadg());
//            //int res = preparedStatement.executeUpdate();
//            return preparedStatement.executeUpdate() > 0;
//        }

        try {
            String sql = "update BangGia set DonGia = ?, TrangThai = ? where MaDG=?";
            Connection connection = JDBCConnection.getJDBCConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1,bg.getDongia());
            preparedStatement.setString(2,bg.getTrangthai());
            preparedStatement.setInt(3,bg.getMadg());
            return preparedStatement.executeUpdate();


        }catch (SQLException ex){
            ex.printStackTrace();
            return -1;
        }
    }

    // kiem tra madv co ton tai trong bang gia va nang luc hay khong ?
    // neu tra ve true thi khong the xoa dc
    public static boolean check_madg_banggia_congviec(int madg){

        boolean check = false;
        try{
            //Preparing a CallableStatement to call a function
            Connection con = JDBCConnection.getJDBCConnection();
            CallableStatement cstmt = con.prepareCall("{? = call FU_CHECK_XOA_BANGGIA (?)}");
            //Registering the out parameter of the function (return type)
            cstmt.registerOutParameter(1, Types.BIT);
            //Setting the input parameters of the function
            cstmt.setInt(2,madg);
            //Executing the statement
            cstmt.execute();

            check=cstmt.getBoolean(1);


        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return check;
    }

    // neu co ton tai thi tra ve true
    public static boolean check_malx_banggia(int malx) throws SQLException {
        boolean check = false;
        try{

            Connection connection = JDBCConnection.getJDBCConnection();
            //Preparing a CallableStatement
            CallableStatement cstmt = connection.prepareCall("{? = call FU_CHECK_MALX_BANGGIA(?)}");

            cstmt.registerOutParameter(1, Types.BIT);
            cstmt.setInt(2,malx);
            cstmt.execute();

            check = cstmt.getBoolean(1);
        }catch (SQLException ex){
            ex.printStackTrace();
        }

        return check;
    }

    public static BANGGIA findBangGia(int madg) {
        BANGGIA bg = new BANGGIA();
        try {
            // thiet lap ke noi
            Connection conn = JDBCConnection.getJDBCConnection();

            // khoi tao loi goi thuc thi thu tuc
            CallableStatement command = conn.prepareCall("{call SP_FIND_BANGGIA_BY_ID(?)}");
            // cung cap gia tro cho bien
            command.setInt(1, madg);

            ResultSet resultSet = command.executeQuery();
            // duyet ket qua

            while (resultSet.next()) {
                bg.setMadg(resultSet.getInt("MaDG"));
                bg.setMadv(resultSet.getInt("MaDV"));
                bg.setMalx(resultSet.getInt("MaLX"));
                bg.setDongia(resultSet.getInt("DonGia"));
                bg.setTrangthai(resultSet.getString("TrangThai"));
            }
            // dong ket noi
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return bg;
    }

    public static void timBangGiaTheoMaDG(int madg, DICHVU dv, LOAIXE lx, int dongia, String trangthai) {
        try {
            // thiet lap ke noi
            Connection conn = JDBCConnection.getJDBCConnection();

            // khoi tao loi goi thuc thi thu tuc
            CallableStatement command = conn.prepareCall("{call SP_TIM_BANGGIA_BY_MADG(?)}");
            // cung cap gia tro cho bien
            command.setInt(1, madg);

            ResultSet resultSet = command.executeQuery();
            // duyet ket qua
            while (resultSet.next()) {
                lx.setMaLX(resultSet.getInt("MaLX"));
                lx.setTenLX(resultSet.getString("TenLX"));
                dv.setMadv(resultSet.getInt("MaDV"));
                dv.setTendv(resultSet.getString("TenDV"));
                dongia = resultSet.getInt("DonGia");
                trangthai = resultSet.getString("TrangThai");
            }
            // dong ket noi
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static boolean insert(BANGGIA bg) throws SQLException {
        String sql = "insert into BangGia(MaDV,MaLX,DonGia,TrangThai) values (?,?,?,?)";
        try (
                Connection connection = JDBCConnection.getJDBCConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setInt(1, bg.getMadv());
            preparedStatement.setInt(2, bg.getMalx());
            preparedStatement.setInt(3, bg.getDongia());
            preparedStatement.setString(4, bg.getTrangthai());

            return preparedStatement.executeUpdate() > 0;

        }
    }


}
