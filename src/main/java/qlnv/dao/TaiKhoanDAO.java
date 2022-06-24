package qlnv.dao;

import qlnv.entity.NHANVIEN;
import qlnv.entity.TAIKHOAN;
import qlnv.jdbc.JDBCConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaiKhoanDAO {

    public static List<TAIKHOAN> getList() throws SQLException {
        String sql = "select*from TaiKhoan";
        try (
                Connection connection = JDBCConnection.getJDBCConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                List<TAIKHOAN> list = new ArrayList<>();
                while (resultSet.next()) {
                    TAIKHOAN tk = new TAIKHOAN();
                    tk.setTendangnhap(resultSet.getString("TenDangNhap"));
                    tk.setMatkhau(resultSet.getString("MatKhau"));
                    tk.setMaNV(resultSet.getString("MaNV"));
                    tk.setQuyen(resultSet.getBoolean("Quyen"));

                    list.add(tk);
                }
                return list;
            }
        }
    }

    public static boolean insert(TAIKHOAN tk) throws SQLException {
        String sql = "insert into TaiKhoan(TenDangNhap,MatKhau,MaNV,Quyen) values (?,?,?,?)";
        try (
                Connection connection = JDBCConnection.getJDBCConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setString(1, tk.getTendangnhap());
            preparedStatement.setString(2, tk.getMatkhau());
            preparedStatement.setString(3, tk.getMaNV());
            preparedStatement.setBoolean(4,tk.isQuyen());

            return preparedStatement.executeUpdate() > 0;

        }
    }

    public static boolean delete_tenDangNhap(String tenDangNhap) throws SQLException {
        String sql = "delete from TaiKhoan where TenDangNhap=?";
        try (
                Connection connection = JDBCConnection.getJDBCConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setString(1, tenDangNhap);

            return preparedStatement.executeUpdate() > 0;
        }
    }

    public static boolean delete(String maNV) throws SQLException {
        String sql = "delete from TaiKhoan where MaNV=?";
        try (
                Connection connection = JDBCConnection.getJDBCConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setString(1, maNV);

            return preparedStatement.executeUpdate() > 0;
        }
    }

    public static boolean update(TAIKHOAN tk) throws SQLException {

        String sql = "update dbo.TaiKhoan set MatKhau = ? where MaNV = ?";
        try (
                Connection connection = JDBCConnection.getJDBCConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {

            preparedStatement.setString(2, tk.getMaNV());
            preparedStatement.setString(1, tk.getMatkhau());
            return preparedStatement.executeUpdate() > 0;
        }
    }

    public static TAIKHOAN getTaiKhoan(String maNV) throws SQLException {
        TAIKHOAN tk = new TAIKHOAN();
        try {

            Connection connection = JDBCConnection.getJDBCConnection();
            //Preparing a CallableStatement
            CallableStatement cstmt = connection.prepareCall("{call sp_getTaiKhoan(?)}");

            // cung cap gia tro cho bien
            cstmt.setString(1, maNV);
            ResultSet result = cstmt.executeQuery();
            // duyet ket qua
            while (result.next()) {
               tk.setTendangnhap(result.getString("TenDangNhap"));
               tk.setMatkhau(result.getString("MatKhau"));
               tk.setQuyen(result.getBoolean("Quyen"));
            }
            // dong ket noi
            connection.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        // set ma nhan vien cho tai khoan neu no ton tai
        tk.setMaNV(maNV);
        return tk;
    }
}
