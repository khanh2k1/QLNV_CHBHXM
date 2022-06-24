package qlnv.dao;


import qlnv.entityCustom.BANGGIA_custom;
import qlnv.jdbc.JDBCConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BangGia_customDAO {

    public static List<BANGGIA_custom> getList() throws SQLException {
        String sql = "{call SP_DANHSACHBANGGIA()}";
        try (
                Connection con = JDBCConnection.getJDBCConnection();
                CallableStatement cstm = con.prepareCall(sql);
        ) {
            // Set parameter values

            // Executes the Procedure statement
            List<BANGGIA_custom> list = new ArrayList<>();
            try (ResultSet rs = cstm.executeQuery();) {
                while (rs.next()) {
                    BANGGIA_custom x = new BANGGIA_custom();
                    x.setMadg(rs.getInt("MaDG"));
                    x.setTenlx(rs.getString("TenLX"));
                    x.setTendv(rs.getString("TenDV"));
                    x.setDongia(String.valueOf(rs.getInt("DonGia")));
                    x.setTrangthai(rs.getString("TrangThai"));
                    list.add(x);
                }
            }
            return list;
        }
    }

    public static BANGGIA_custom getBangGia_by_madg(int madg) throws SQLException {
        String sql = "{call SP_GETBANGBIA_BY_MADG(?)}";
        try (
                Connection con = JDBCConnection.getJDBCConnection();
                CallableStatement cstm = con.prepareCall(sql);
        ) {
            // Set parameter values
            cstm.setInt(1,madg);
            // Executes the Procedure statement
            BANGGIA_custom x = new BANGGIA_custom();
            try (ResultSet rs = cstm.executeQuery();) {
                while (rs.next()) {
                    x.setMadg(rs.getInt("MaDG"));
                    x.setTenlx(rs.getString("TenLX"));
                    x.setTendv(rs.getString("TenDV"));
                    x.setDongia(String.valueOf(rs.getInt("DonGia")));
                    x.setTrangthai(rs.getString("TrangThai"));

                }
            }
            return x;
        }
    }
}
