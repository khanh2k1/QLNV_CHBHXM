package qlnv.dao;


import qlnv.entity.LOAINHANVIEN;
import qlnv.jdbc.JDBCConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoaiNhanVienDAO {
    public static List<LOAINHANVIEN> getList() throws SQLException {
        String sql = "select*from LoaiNV";
        try (
                Connection connection = JDBCConnection.getJDBCConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                List<LOAINHANVIEN> list = new ArrayList<>();
                while (resultSet.next()) {
                    LOAINHANVIEN loainv = new LOAINHANVIEN();
                    loainv.setMaloai(resultSet.getInt("MaLoai"));
                    loainv.setTenloai(resultSet.getString("TenLoai"));

                    list.add(loainv);
                }
                return list;
            }
        }
    }

    public static boolean insert(LOAINHANVIEN loainv) throws SQLException {
        String sql = "insert into LoaiNV(MaLoai, TenLoai) values(?,?)";
        try (
                Connection connection = JDBCConnection.getJDBCConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setInt(1, loainv.getMaloai());
            preparedStatement.setString(2, loainv.getTenloai());

            return preparedStatement.executeUpdate() > 0;
        }
    }

    public static boolean delete(int maloai) throws SQLException {
        String sql = "delete from LoaiNV where MaLoai=?";
        try (
                Connection connection = JDBCConnection.getJDBCConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setInt(1, maloai);

            return preparedStatement.executeUpdate() > 0;
        }
    }
    public static boolean update(LOAINHANVIEN loainv) throws SQLException {

        String sql = "update dbo.LoaiNV set TenLoai=? where MaLoai=?";
        try (
                Connection connection = JDBCConnection.getJDBCConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {

            preparedStatement.setInt(2, loainv.getMaloai());
            preparedStatement.setString(1,loainv.getTenloai());

            //int res = preparedStatement.executeUpdate();
            return preparedStatement.executeUpdate() > 0;
        }
    }



    public static int get_max_maLoaiNV() throws SQLException {
        Connection connection = JDBCConnection.getJDBCConnection();
        //Preparing a CallableStatement
        CallableStatement cstmt = connection.prepareCall("{? = call FU_GET_MAX_MALOAINV()}");

        cstmt.registerOutParameter(1, Types.INTEGER);
        cstmt.execute();

        int maxMaLoaiNV = cstmt.getInt(1);

        return maxMaLoaiNV;
    }

}
