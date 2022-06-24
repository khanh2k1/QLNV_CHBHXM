package qlnv.dao;

import qlnv.entity.DICHVU;
import qlnv.entity.NANGLUC;
import qlnv.entity.NHANVIEN;
import qlnv.entity.TAIKHOAN;
import qlnv.jdbc.JDBCConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NangLucDAO {
    public static NANGLUC getListNangLuc(String maNV) throws SQLException {

        NANGLUC nl = new NANGLUC();
        List<Integer> listMaDV = new ArrayList<>();
        String sql = "{call SP_GET_LIST_NANGLUC_BY_MANV(?)}";
        try (
                Connection con = JDBCConnection.getJDBCConnection();
                CallableStatement cstm = con.prepareCall(sql);
        ) {
            // Set parameter values
            cstm.setString(1, maNV);

            // Executes the Procedure statement


            try (ResultSet rs = cstm.executeQuery();) {
                while (rs.next()) {
                    nl.setMaNV(rs.getString("MaNV"));
                    listMaDV.add(rs.getInt("MaDV"));
                }
            }
            nl.setListMaDV(listMaDV);
            return nl;
        }
    }

    public static boolean delete(String maNV) throws SQLException {
        String sql = "delete from NangLuc where MaNV=?";
        try (
                Connection connection = JDBCConnection.getJDBCConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setString(1, maNV);

            return preparedStatement.executeUpdate() > 0;
        }

    }

    public static boolean insert(String maNV, int maDV) throws SQLException {
        String sql = "insert into NangLuc(MaNV,MaDV) values (?,?)";
        try (
                Connection connection = JDBCConnection.getJDBCConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setString(1, maNV);
            preparedStatement.setInt(2, maDV);

            return preparedStatement.executeUpdate() > 0;
        }
    }

    public static void update(NANGLUC nl) throws SQLException {
        // truoc khi update thi ta xoa het cac nang luc cu cua NV
        NangLucDAO.delete(nl.getMaNV());

        // them cac nang luc moi vao
        for(int i = 0;i< nl.getListMaDV().size();i++){
            NangLucDAO.insert(nl.getMaNV(),nl.getListMaDV().get(i));
        }
    }

    public static boolean check_nhan_vien_co_nang_luc(String maNV) throws SQLException {

        //Preparing a CallableStatement to call a function
        Connection con = JDBCConnection.getJDBCConnection();
        CallableStatement cstmt = con.prepareCall("{? = call FU_CHECK_NANGLUC_NHANVIEN_BY_MANV(?)}");
        //Registering the out parameter of the function (return type)
        cstmt.registerOutParameter(1, Types.BIT);
        //Setting the input parameters of the function
        cstmt.setString(2,maNV);
        //Executing the statement
        cstmt.execute();
        boolean check = cstmt.getBoolean(1);

        return check;
    }

}
