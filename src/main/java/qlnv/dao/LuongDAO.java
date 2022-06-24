package qlnv.dao;


import qlnv.entity.LUONG;
import qlnv.jdbc.JDBCConnection;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class LuongDAO {
    public static List<LUONG> getLuong(String manv) {
        List<LUONG> listLuong = new ArrayList<>();

        String sql = "{call SP_GET_BANG_LUONG_CUA_MOT_NHAN_VIEN(?)}";
        try (
                Connection con = JDBCConnection.getJDBCConnection();
                CallableStatement cstm = con.prepareCall(sql);
        ) {
            // Set parameter values
            cstm.setString(1, manv);
            // Executes the Procedure statement

            try (ResultSet rs = cstm.executeQuery();) {
                while (rs.next()) {
                    LUONG luong = new LUONG();
                    luong.setMaNV(rs.getString("MaNV"));
                    luong.setThang(rs.getInt("Thang"));
                    luong.setNam(rs.getInt("Nam"));
                    luong.setSongaythuclam(rs.getInt("SoNgayThucLam"));
                    luong.setTongdoanhthu(rs.getLong("TongDoanhThu"));

                    listLuong.add(luong);
                }
            }

            return listLuong;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new ArrayList<LUONG>();
    }


    public static boolean insert(LUONG luong) {
        luong.setSongaythuclam(20);
        String sql = "insert into LuongNV(MaNV,Thang,Nam,SoNgayThucLam,TongDoanhThu) values(?,?,?,?,?)";
        try (
                Connection connection = JDBCConnection.getJDBCConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setString(1, luong.getMaNV());
            preparedStatement.setInt(2, luong.getThang());
            preparedStatement.setInt(3, luong.getNam());
            preparedStatement.setInt(4, luong.getSongaythuclam());
            preparedStatement.setLong(5, luong.getTongdoanhthu());

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public static boolean khoiTaoLuongChoNhanVien(String manv) {

        List<LUONG> listLuong = LuongDAO.getLuong(manv);
        boolean check_bang_luong = false;
        for (int i = 0; i < listLuong.size(); i++) {
            if (manv.equals(listLuong.get(i).getMaNV())) {
                if (listLuong.get(i).getNam() == LocalDate.now().getYear()) {
                    System.out.println("Nhan vien da co bang luong trong 1 nam hien tai !");
                    check_bang_luong = true;
                    break;
                }
            }
        }
        if (check_bang_luong == false) {
            for (int i = 1; i <= 12; i++) {
                LUONG l = new LUONG(manv, i, LocalDate.now().getYear(), 0, 0);
                LuongDAO.insert(l);
            }
        }
        return false;
    }

    public static void updateTongDoanhThu(String manv, int thang, int nam, long tongdoanhthu){
        try {
            // thiet lap ke noi
            Connection conn = JDBCConnection.getJDBCConnection();

            // khoi tao loi goi thuc thi thu tuc
            CallableStatement command = conn.prepareCall("{call SP_update_tong_doanh_thu (?,?,?,?)}");
            // cung cap gia tro cho bien


            command.setString(1,manv);
            command.setInt(2,thang);
            command.setInt(3,nam);
            command.setLong(4,tongdoanhthu);

            ResultSet resultSet = command.executeQuery();

            // dong ket noi
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public static List<LUONG> getListLuongTheoThang(int thang, int nam){
        List<LUONG> list = new ArrayList<>();
        try {
            // thiet lap ke noi
            Connection conn = JDBCConnection.getJDBCConnection();

            // khoi tao loi goi thuc thi thu tuc
            CallableStatement command = conn.prepareCall("{call SP_get_list_luong_theo_thang (?,?)}");
            // cung cap gia tro cho bien


            command.setInt(1,thang);
            command.setInt(2,nam);


            ResultSet resultSet = command.executeQuery();
// duyet ket qua
            while (resultSet.next()) {
                LUONG luong = new LUONG();
                luong.setMaNV(resultSet.getString("MaNV"));
                luong.setThang(resultSet.getInt("Thang"));
                luong.setNam(resultSet.getInt("Nam"));
                luong.setSongaythuclam(resultSet.getInt("SoNgayThucLam"));
                luong.setTongdoanhthu(resultSet.getLong("TongDoanhThu"));


                list.add(luong);
            }
            // dong ket noi
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public static boolean update_so_ngay_thuc_lam(int songaythuclam, String manv, int thang, int nam) throws SQLException {
        String sql = "update LuongNV set LuongNV.SoNgayThucLam=? where MaNV=? and Thang=? and Nam=?";
        try (
                Connection connection = JDBCConnection.getJDBCConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {

            preparedStatement.setInt(1, songaythuclam );
            preparedStatement.setString(2,manv);
            preparedStatement.setInt(3,thang);
            preparedStatement.setInt(4,nam);

            return preparedStatement.executeUpdate() > 0;
        }
    }

    public static LUONG get_1_thang_luong_cua_1_nhan_vien(String manv, int thang, int nam){
        LUONG luong = new LUONG();

        String sql = "{call SP_get_1_thang_luong_cua_1_nhan_vien(?,?,?)}";
        try (
                Connection con = JDBCConnection.getJDBCConnection();
                CallableStatement cstm = con.prepareCall(sql);
        ) {
            // Set parameter values
            cstm.setString(1, manv);
            cstm.setInt(2,thang);
            cstm.setInt(3,nam);

            // Executes the Procedure statement

            try (ResultSet rs = cstm.executeQuery();) {
                while (rs.next()) {

                    luong.setMaNV(rs.getString("MaNV"));
                    luong.setThang(rs.getInt("Thang"));
                    luong.setNam(rs.getInt("Nam"));
                    luong.setSongaythuclam(rs.getInt("SoNgayThucLam"));
                    luong.setTongdoanhthu(rs.getLong("TongDoanhThu"));
                }
                return luong;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new LUONG();
    }
}
