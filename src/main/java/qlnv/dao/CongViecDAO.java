package qlnv.dao;


import qlnv.entity.CONGVIEC;
import qlnv.entityCustom.BANGCONGVIEC;
import qlnv.entityCustom.CONGVIECCUANHANVIEN;
import qlnv.jdbc.JDBCConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CongViecDAO {
    public static List<CONGVIEC> getList() throws SQLException {
        String sql = "select*from CongViec";
        try (
                Connection connection = JDBCConnection.getJDBCConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                List<CONGVIEC> list = new ArrayList<>();
                while (resultSet.next()) {
                    CONGVIEC cv = new CONGVIEC();

                    cv.setMadg(resultSet.getInt("MaDG"));
                    cv.setManv(resultSet.getString("MaNV"));
                    cv.setNgaylam(resultSet.getTimestamp("NgayLam"));
                    cv.setTongtien(resultSet.getInt("TongTien"));

                    list.add(cv);
                }
                return list;
            }
        }
    }

    public static boolean insert(CONGVIEC cv) throws SQLException {
        String sql = "insert into CongViec(MaDG,MaNV,NgayLam,TongTien) values(?,?,?,?)";
        try (
                Connection connection = JDBCConnection.getJDBCConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setInt(1,cv.getMadg());
            preparedStatement.setString(2, cv.getManv());
            preparedStatement.setTimestamp(3,cv.getNgaylam());
            preparedStatement.setInt(4,cv.getTongtien());

            return preparedStatement.executeUpdate() > 0;
        }
    }

    public static boolean delete(String manv, Timestamp ngaylam) throws SQLException {
        String sql = "delete from CongViec where MaNV=? AND NgayLam=?";
        try (
                Connection connection = JDBCConnection.getJDBCConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setString(1, manv);
            preparedStatement.setTimestamp(2,ngaylam);

            return preparedStatement.executeUpdate() > 0;
        }
    }

//    public static boolean update(CONGVIEC dv) throws SQLException {
//
//        String sql = "update dbo.DichVu set MaNV=?, NgayLam=?, TongTien=? where MaCV=?";
//        try (
//                Connection connection = JDBCConnection.getJDBCConnection();
//                PreparedStatement preparedStatement = connection.prepareStatement(sql);
//        ) {
//
//            preparedStatement.setString(1,dv.getManv());
//            preparedStatement.setTimestamp(2,dv.getNgaylam());
//            preparedStatement.setInt(3,dv.getTongtien());
//            //int res = preparedStatement.executeUpdate();
//            return preparedStatement.executeUpdate() > 0;
//        }
//    }


    public static int get_max_maLX() throws SQLException {
        Connection connection = JDBCConnection.getJDBCConnection();
        //Preparing a CallableStatement
        CallableStatement cstmt = connection.prepareCall("{? = call fu_max_getMaCONGVIEC()}");

        cstmt.registerOutParameter(1, Types.INTEGER);
        cstmt.execute();

        int maxMaDV = cstmt.getInt(1);

        return maxMaDV;
    }

    public static List<CONGVIECCUANHANVIEN> listCongViecCuaNhanVien(){
        try{
            Connection connection = JDBCConnection.getJDBCConnection();
            CallableStatement command = connection.prepareCall("{call SP_LIST_CONG_VIEC_CUA_MOT_NHAN_VIEN()}");
            ResultSet resultSet = command.executeQuery();

            List<CONGVIECCUANHANVIEN> list = new ArrayList<CONGVIECCUANHANVIEN>();

            while(resultSet.next()){
                CONGVIECCUANHANVIEN x = new CONGVIECCUANHANVIEN();

                x.setManv(resultSet.getString("MaNV"));
                x.setHoten(resultSet.getString("HoTen"));
                x.setTendv(resultSet.getString("TenDV"));
                x.setTenlx(resultSet.getString("TenLX"));
                x.setDongia(resultSet.getInt("DonGia"));
                x.setNgaylam(resultSet.getTimestamp("NgayLam"));
                list.add(x);

            }

            return list;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new ArrayList<>();
    }

    // de co the xoa nhan vien hay khong
    public static boolean check_nhan_vien_co_cong_viec(String maNV) throws SQLException {

        //Preparing a CallableStatement to call a function
        Connection con = JDBCConnection.getJDBCConnection();
        CallableStatement cstmt = con.prepareCall("{? = call FU_CHECK_CONGVIEC_NHANVIEN_BY_MANV(?)}");
        //Registering the out parameter of the function (return type)
        cstmt.registerOutParameter(1, Types.BIT);
        //Setting the input parameters of the function
        cstmt.setString(2,maNV);
        //Executing the statement
        cstmt.execute();
        boolean check = cstmt.getBoolean(1);

        return check;
    }

    public static int getTongTienTuMaCV(int macv){
        try{
            Connection connection = JDBCConnection.getJDBCConnection();
            CallableStatement command = connection.prepareCall("{call SP_TONG_TIEN_CUA_DICH_VU(?)}");

            command.setInt(1,macv);
            ResultSet resultSet = command.executeQuery();

            int tongtien = 0;

            while(resultSet.next()){
                tongtien = resultSet.getInt("TongTien");
            }

            return tongtien;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }

    // lay danh sach cong viec cua 1 nhan vien
    public static List<CONGVIECCUANHANVIEN> listCongViecCuaMotNhanVien(String manv){
        try{
            Connection connection = JDBCConnection.getJDBCConnection();
            CallableStatement command = connection.prepareCall("{call SP_cong_viec_cua_1_nhan_vien_theo_manv(?)}");

            // truyen tham so cho sp
            command.setString(1,manv);
            // thuc hien cau lenh
            ResultSet resultSet = command.executeQuery();

            List<CONGVIECCUANHANVIEN> list = new ArrayList<CONGVIECCUANHANVIEN>();

            while(resultSet.next()){
                CONGVIECCUANHANVIEN x = new CONGVIECCUANHANVIEN();

                x.setManv(resultSet.getString("MaNV"));
                x.setHoten(resultSet.getString("HoTen"));
                x.setTendv(resultSet.getString("TenDV"));
                x.setTenlx(resultSet.getString("TenLX"));
                x.setDongia(resultSet.getInt("DonGia"));
                x.setNgaylam(resultSet.getTimestamp("NgayLam"));
                list.add(x);

            }

            return list;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new ArrayList<>();
    }

}
