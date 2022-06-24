package qlnv.dao;
import qlnv.entity.NHANVIEN;
import qlnv.jdbc.JDBCConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NhanVienDAO {

    public static String manv="";

    public static NHANVIEN getNhanVienByID(String maNV) throws SQLException {

        String sql = "{call find_nhanvien_by_maNV(?)}";
        try (
                Connection con = JDBCConnection.getJDBCConnection();
                CallableStatement cstm = con.prepareCall(sql);
        ) {
            // Set parameter values
            cstm.setString(1, maNV);

            // Executes the Procedure statement
            NHANVIEN nv = new NHANVIEN();
            try (ResultSet rs = cstm.executeQuery();) {
                while (rs.next()) {
                    nv.setMaNV(rs.getString(1));
                    nv.setHoten(rs.getString(2));
                    nv.setGioitinh(rs.getString(3));
                    nv.setNgaysinh(rs.getDate(4));
                    nv.setDiachi(rs.getString(5));
                    nv.setSdt(rs.getString(6));
                    nv.setEmail(rs.getString(7));
                    nv.setLuongcoban(String.valueOf(rs.getLong(8)));
                    nv.setLoaiNV(rs.getInt(9));
                    nv.setTrangthai(rs.getString(10));
                }
            }
            return nv;
        }
    }

    public static List<NHANVIEN> getList() throws SQLException {
        String sql = "select*from NhanVien";
        try (
                Connection connection = JDBCConnection.getJDBCConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                List<NHANVIEN> list = new ArrayList<>();
                while (resultSet.next()) {
                    NHANVIEN nv = new NHANVIEN();
                    nv.setMaNV(resultSet.getString("MaNV"));
                    nv.setHoten(resultSet.getString("HoTen"));
                    nv.setGioitinh(resultSet.getString("GioiTinh"));
                    nv.setNgaysinh(resultSet.getDate("NgaySinh"));
                    nv.setDiachi(resultSet.getString("DiaChi"));
                    nv.setSdt(resultSet.getString("SoDT"));
                    nv.setEmail(resultSet.getString("Email"));
                    nv.setLuongcoban(String.valueOf(resultSet.getLong("LuongCoBan")));
                    nv.setLoaiNV(resultSet.getInt("MaLoai"));
                    nv.setTrangthai(resultSet.getString("TrangThai"));

                    list.add(nv);
                }
                return list;
            }
        }
    }

    public static boolean insert(NHANVIEN nv) throws SQLException {
        String sql = "insert into NhanVien(MaNV,Hoten,GioiTinh, NgaySinh, DiaChi, SoDT, Email, LuongCoBan, MaLoai, TrangThai)" +
                " values (?,?,?,?,?,?,?,?,?,?)";
        try (
                Connection connection = JDBCConnection.getJDBCConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setString(1, nv.getMaNV());
            preparedStatement.setString(2, nv.getHoten());
            preparedStatement.setString(3, nv.getGioitinh());
            preparedStatement.setDate(4, nv.getNgaysinh());
            preparedStatement.setString(5, nv.getDiachi());
            preparedStatement.setString(6, nv.getSdt());
            preparedStatement.setString(7, nv.getEmail());
            // chuyen String thanh Long de luu vao csdl
            preparedStatement.setLong(8, Long.parseLong(nv.getLuongcoban()));
            preparedStatement.setInt(9, nv.getLoaiNV());
            preparedStatement.setString(10, nv.getTrangthai());

            return preparedStatement.executeUpdate() > 0;
        }


    }

    public static boolean delete(String maNV) throws SQLException {
        String sql = "update dbo.NhanVien set TrangThai=? where MaNV=?";

        try (
                Connection connection = JDBCConnection.getJDBCConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {

            preparedStatement.setString(2, maNV);
            preparedStatement.setString(1, "Nghỉ");


            return preparedStatement.executeUpdate() > 0;
        }
    }

    public static boolean update(NHANVIEN nv) throws SQLException {

        String sql = "update dbo.NhanVien set Hoten=?, GioiTinh=?, NgaySinh=?, DiaChi=?, SoDT=?, Email=?, LuongCoBan=?," +
                "MaLoai=?, TrangThai=? where MaNV=?";

        try (
                Connection connection = JDBCConnection.getJDBCConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {

            preparedStatement.setString(10, nv.getMaNV());
            preparedStatement.setString(1, nv.getHoten());
            preparedStatement.setString(2, nv.getGioitinh());
            preparedStatement.setDate(3, nv.getNgaysinh());
            preparedStatement.setString(4, nv.getDiachi());
            preparedStatement.setString(5, nv.getSdt());
            preparedStatement.setString(6, nv.getEmail());
            preparedStatement.setLong(7, Long.parseLong(nv.getLuongcoban()));
            preparedStatement.setInt(8, nv.getLoaiNV());
            preparedStatement.setString(9, nv.getTrangthai());

            return preparedStatement.executeUpdate() > 0;
        }
    }

    public static List<NHANVIEN> sapXepNhanVienTheoMa() {
        List<NHANVIEN> list = new ArrayList<>();
        try {
            // thiet lap ke noi
            Connection conn = JDBCConnection.getJDBCConnection();

            // khoi tao loi goi thuc thi thu tuc
            CallableStatement command = conn.prepareCall("{call SP_SAPXEPTANGDAN_NHANVIEN_THEO_MANV ()}");
            // cung cap gia tro cho bien
            ResultSet resultSet = command.executeQuery();
            // duyet ket qua
            while (resultSet.next()) {
                NHANVIEN nv = new NHANVIEN();
                nv.setMaNV(resultSet.getString("MaNV"));
                nv.setHoten(resultSet.getString("HoTen"));
                nv.setGioitinh(resultSet.getString("GioiTinh"));
                nv.setNgaysinh(resultSet.getDate("NgaySinh"));
                nv.setDiachi(resultSet.getString("DiaChi"));
                nv.setSdt(resultSet.getString("SoDT"));
                nv.setEmail(resultSet.getString("Email"));
                nv.setLuongcoban(String.valueOf(resultSet.getLong("LuongCoBan")));
                nv.setLoaiNV(resultSet.getInt("MaLoai"));
                nv.setTrangthai(resultSet.getString("TrangThai"));

                list.add(nv);
            }
            // dong ket noi
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public static String taoMaNV(int loaiNV) throws SQLException {
        String maNV="";
        //Preparing a CallableStatement to call a function
        Connection con = JDBCConnection.getJDBCConnection();
        CallableStatement cstmt = con.prepareCall("{? = call FU_AUTO_MANV(?)}");
        //Registering the out parameter of the function (return type)
        cstmt.registerOutParameter(1, Types.VARCHAR);
        //Setting the input parameters of the function
        cstmt.setInt(2,loaiNV);
        //Executing the statement
        cstmt.execute();
        maNV=cstmt.getString(1);
        return maNV;
    }

    public static List<NHANVIEN> listNhanVienCanTimTheoNangLuc(int madv){
        List<NHANVIEN> list = new ArrayList<>();
        try {
            // thiet lap ke noi
            Connection conn = JDBCConnection.getJDBCConnection();

            // khoi tao loi goi thuc thi thu tuc
            CallableStatement command = conn.prepareCall("{call SP_LIST_NHANVIEN_CO_NANGLUC_CAN_TIM (?)}");
            // cung cap gia tro cho bien
            command.setInt(1,madv);
            ResultSet resultSet = command.executeQuery();
            // duyet ket qua
            while (resultSet.next()) {
                NHANVIEN nv = new NHANVIEN();
                nv.setMaNV(resultSet.getString("MaNV"));
                nv.setHoten(resultSet.getString("HoTen"));
                nv.setGioitinh(resultSet.getString("GioiTinh"));
                nv.setNgaysinh(resultSet.getDate("NgaySinh"));
                nv.setDiachi(resultSet.getString("DiaChi"));
                nv.setSdt(resultSet.getString("SoDT"));
                nv.setEmail(resultSet.getString("Email"));
                nv.setLuongcoban(String.valueOf(resultSet.getLong("LuongCoBan")));
                nv.setLoaiNV(resultSet.getInt("MaLoai"));
                nv.setTrangthai(resultSet.getString("TrangThai"));

                list.add(nv);
            }
            // dong ket noi
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

}
