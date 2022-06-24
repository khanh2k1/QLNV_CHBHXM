package qlnv.dao;


import qlnv.entity.BANGGIA;
import qlnv.entity.DICHVU;
import qlnv.entity.LOAIXE;
import qlnv.entity.TAIKHOAN;
import qlnv.jdbc.JDBCConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoaiXeDAO {

    public static List<LOAIXE> getList() throws SQLException {
        String sql = "select*from LoaiXe";
        try (
                Connection connection = JDBCConnection.getJDBCConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                List<LOAIXE> list = new ArrayList<>();
                while (resultSet.next()) {
                    LOAIXE lx = new LOAIXE();
                    lx.setMaLX(resultSet.getInt("MaLX"));
                    lx.setTenLX(resultSet.getString("TenLX"));

                    list.add(lx);
                }
                return list;
            }
        }
    }

    public static boolean insert(LOAIXE lx) throws SQLException {
        String sql = "insert into LoaiXe(MaLX, TenLX) values(?,?)";
        try (
                Connection connection = JDBCConnection.getJDBCConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setInt(1, lx.getMaLX());
            preparedStatement.setString(2, lx.getTenLX());


            return preparedStatement.executeUpdate() > 0;

        }
    }

    // xoa loai xe voi dk khong co ben khoa cua bang khac
    public static boolean delete(int maLX) throws SQLException {
        List<BANGGIA> listBG = BangGiaDAO.getList();
        // bang gia
        for (int i = 0; i < listBG.size(); i++) {
            if (maLX == listBG.get(i).getMalx()) {
                return false;
            }
        }

        String sql = "delete from LoaiXe where MaLX=?";
        try (
                Connection connection = JDBCConnection.getJDBCConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setInt(1, maLX);

            return preparedStatement.executeUpdate() > 0;
        }
    }

    public static boolean update(LOAIXE lx) throws SQLException {

        String sql = "update dbo.LoaiXe set TenLX=? where MaLX=?";
        try (
                Connection connection = JDBCConnection.getJDBCConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {

            preparedStatement.setInt(2, lx.getMaLX());
            preparedStatement.setString(1, lx.getTenLX());

            //int res = preparedStatement.executeUpdate();
            return preparedStatement.executeUpdate() > 0;
        }
    }

    public static LOAIXE findByID(int maLX) throws SQLException {

        String sql = "select*from LoaiXe";
        LOAIXE lx_ = new LOAIXE();
        try (
                Connection connection = JDBCConnection.getJDBCConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                List<LOAIXE> list = new ArrayList<>();
                while (resultSet.next()) {
                    LOAIXE lx = new LOAIXE();
                    lx.setMaLX(resultSet.getInt("MaLX"));
                    lx.setTenLX(resultSet.getString("TenLX"));

                    list.add(lx);
                }
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getMaLX() == maLX) {
                        lx_.setMaLX(list.get(i).getMaLX());
                        lx_.setTenLX(list.get(i).getTenLX());
                        break;
                    }
                }
                return lx_;
            }
        }
    }

    public static int get_max_maLX() throws SQLException {
        Connection connection = JDBCConnection.getJDBCConnection();
        //Preparing a CallableStatement
        CallableStatement cstmt = connection.prepareCall("{? = call fu_max_getMaLoaiXe()}");

        cstmt.registerOutParameter(1, Types.INTEGER);
        cstmt.execute();

        int maxMaLX = cstmt.getInt(1);

        return maxMaLX;
    }

}
