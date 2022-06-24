package qlnv.dao;

import qlnv.entity.NHANVIEN;
import qlnv.entityCustom.LUONG_CUSTOM;
import qlnv.jdbc.JDBCConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Luong_customDAO {

    public static List<LUONG_CUSTOM> getList(int thang, int nam){
        List<LUONG_CUSTOM> list = new ArrayList<>();
        try {
            // thiet lap ke noi
            Connection conn = JDBCConnection.getJDBCConnection();

            // khoi tao loi goi thuc thi thu tuc
            CallableStatement command = conn.prepareCall("{call SP_List_Doanh_Thu_Cua_List_Nhan_Vien_Theo_Thang (?,?)}");
            // cung cap gia tro cho bien
            command.setInt(1,thang);
            command.setInt(2,nam);
            ResultSet resultSet = command.executeQuery();
            // duyet ket qua
            while (resultSet.next()) {
                LUONG_CUSTOM luong_custom = new LUONG_CUSTOM();
                luong_custom.setManv(resultSet.getString("MaNV"));
                luong_custom.setHoten(resultSet.getString("HoTen"));
                luong_custom.setSongaythuclam(resultSet.getInt("SoNgayThucLam"));
                luong_custom.setLuongcoban((resultSet.getInt("LuongCoBan")));
                luong_custom.setTongdoanhthu(resultSet.getLong("TongDoanhThu"));
                luong_custom.setLuongthuclanh(resultSet.getLong("LuongThucLanh"));
                list.add(luong_custom);
            }
            // dong ket noi
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public static List<LUONG_CUSTOM> getList_ByMaNV(int thang, int nam, String maNV){
        List<LUONG_CUSTOM> list = new ArrayList<>();
        try {
            // thiet lap ke noi
            Connection conn = JDBCConnection.getJDBCConnection();

            // khoi tao loi goi thuc thi thu tuc
            CallableStatement command = conn.prepareCall("{call SP_List_Doanh_Thu_Cua_List_Nhan_Vien_Theo_Thang_Theo_MaNV (?,?,?)}");
            // cung cap gia tro cho bien
            command.setInt(1,thang);
            command.setInt(2,nam);
            command.setString(3,maNV);

            ResultSet resultSet = command.executeQuery();
            // duyet ket qua
            while (resultSet.next()) {
                LUONG_CUSTOM luong_custom = new LUONG_CUSTOM();
                luong_custom.setManv(resultSet.getString("MaNV"));
                luong_custom.setHoten(resultSet.getString("HoTen"));
                luong_custom.setSongaythuclam(resultSet.getInt("SoNgayThucLam"));
                luong_custom.setLuongcoban((resultSet.getInt("LuongCoBan")));
                luong_custom.setTongdoanhthu(resultSet.getLong("TongDoanhThu"));
                luong_custom.setLuongthuclanh(resultSet.getLong("LuongThucLanh"));
                list.add(luong_custom);
            }
            // dong ket noi
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

}
