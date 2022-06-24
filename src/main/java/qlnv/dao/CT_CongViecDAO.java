package qlnv.dao;

import qlnv.entity.CT_CONGVIEC;
import qlnv.entity.LOAINHANVIEN;
import qlnv.entity.NHANVIEN;
import qlnv.entityCustom.BANGCONGVIEC;
import qlnv.entityCustom.CT_CONGVIEC_CUSTOM;
import qlnv.jdbc.JDBCConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CT_CongViecDAO {

    public static List<CT_CONGVIEC> getList() throws SQLException {
        String sql = "select*from ChiTietCV";
        try (
                Connection connection = JDBCConnection.getJDBCConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                List<CT_CONGVIEC> list = new ArrayList<>();
                while (resultSet.next()) {
                    CT_CONGVIEC ct_congviec = new CT_CONGVIEC();
                    ct_congviec.setMadg(resultSet.getInt("MaDG"));
                    ct_congviec.setSoluong(resultSet.getInt("SoLuong"));

                    list.add(ct_congviec);
                }
                return list;
            }
        }
    }

    public static List<CT_CONGVIEC_CUSTOM> listSoLuongCongViec() {
        List<CT_CONGVIEC_CUSTOM> list = new ArrayList<>();
        try {
            // thiet lap ke noi
            Connection conn = JDBCConnection.getJDBCConnection();

            // khoi tao loi goi thuc thi thu tuc
            CallableStatement command = conn.prepareCall("{call SP_SHOW_DANHSACHCONGVIEC ()}");
            // cung cap gia tro cho bien
            ResultSet resultSet = command.executeQuery();
            // duyet ket qua
            while (resultSet.next()) {
                CT_CONGVIEC_CUSTOM ct_congviec_custom = new CT_CONGVIEC_CUSTOM();
                ct_congviec_custom.setTenlx(resultSet.getString("TenLX"));
                ct_congviec_custom.setTendv(resultSet.getString("TenDV"));
                ct_congviec_custom.setDongia(resultSet.getInt("DonGia"));
                ct_congviec_custom.setSoluong(resultSet.getInt("SoLuong"));
                list.add(ct_congviec_custom);
            }
            // dong ket noi
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    // chi tiet cong viec duoc them vao khi co bang gia dc them vao
    public static boolean insert(CT_CONGVIEC ct_congviec) throws SQLException {
        String sql = "insert into ChiTietCV(MaDG,Soluong) values (?,?)";

        try (
                Connection connection = JDBCConnection.getJDBCConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setInt(1, ct_congviec.getMadg());
            preparedStatement.setInt(2, ct_congviec.getSoluong());


            return preparedStatement.executeUpdate() > 0;
        }
    }

    public static boolean delete(int madg) throws SQLException {
        String sql = "delete from ChiTietCV where MaDG=?";
        try (
                Connection connection = JDBCConnection.getJDBCConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setInt(1, madg);

            return preparedStatement.executeUpdate() > 0;
        }
    }

    public static boolean update(CT_CONGVIEC ct_congviec) throws SQLException {
        String sql = "update ChiTietCV set Soluong=? where MaDG=?";
        try (
                Connection connection = JDBCConnection.getJDBCConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {

            preparedStatement.setInt(1, ct_congviec.getSoluong());
            preparedStatement.setInt(1, ct_congviec.getMadg());

            return preparedStatement.executeUpdate() > 0;
        }
    }
}
