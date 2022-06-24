package qlnv.dao;

import qlnv.entity.DICHVU;
import qlnv.entity.NHANVIEN;
import qlnv.jdbc.JDBCConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DichVuDAO {
    public static List<DICHVU> getList() throws SQLException {
        String sql = "select*from DichVu";
        try (
                Connection connection = JDBCConnection.getJDBCConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                List<DICHVU> list = new ArrayList<>();
                while (resultSet.next()) {
                    DICHVU dv = new DICHVU();
                    dv.setMadv(resultSet.getInt("MaDV"));
                    dv.setTendv(resultSet.getString("TenDV"));

                    list.add(dv);
                }
                return list;
            }
        }
    }

    public static boolean insert(DICHVU dv) throws SQLException {
        String sql = "insert into DichVu(TenDV) values(?)";
        try (
                Connection connection = JDBCConnection.getJDBCConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {

            preparedStatement.setString(1, dv.getTendv());


            return preparedStatement.executeUpdate() > 0;

        }
    }

    // kiem tra madv co ton tai trong bang gia va nang luc hay khong ?
    // neu tra ve true thi khong the xoa dc
    public static boolean check_madv_banggia_nangluc(int madv){

        boolean check = false;
        try{
            //Preparing a CallableStatement to call a function
            Connection con = JDBCConnection.getJDBCConnection();
            CallableStatement cstmt = con.prepareCall("{? = call FU_CHECK_XOA_DICHVU (?)}");
            //Registering the out parameter of the function (return type)
            cstmt.registerOutParameter(1, Types.BIT);
            //Setting the input parameters of the function
            cstmt.setInt(2,madv);
            //Executing the statement
            cstmt.execute();

            check=cstmt.getBoolean(1);


        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return check;
    }
    public static boolean delete(int MaDV) throws SQLException {
        String sql = "delete from DichVu where MaDV=?";
        try (
                Connection connection = JDBCConnection.getJDBCConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setInt(1, MaDV);

            return preparedStatement.executeUpdate() > 0;
        }
    }
    public static boolean update(DICHVU dv) throws SQLException {

        String sql = "update dbo.DichVu set TenDV=? where MaDV=?";
        try (
                Connection connection = JDBCConnection.getJDBCConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {

            preparedStatement.setInt(2, dv.getMadv());
            preparedStatement.setString(1,dv.getTendv());

            //int res = preparedStatement.executeUpdate();
            return preparedStatement.executeUpdate() > 0;
        }
    }

    public static DICHVU findByID(int MaDV) throws SQLException {

        String sql = "select*from DichVu";
        DICHVU dv_ = new DICHVU();
        try (
                Connection connection = JDBCConnection.getJDBCConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                List<DICHVU> list = new ArrayList<>();
                while (resultSet.next()) {
                    DICHVU dv = new DICHVU();
                    dv.setMadv(resultSet.getInt("MaDV"));
                    dv.setTendv(resultSet.getString("Tendv"));

                    list.add(dv);
                }
                for(int i=0;i<list.size();i++){
                    if(list.get(i).getMadv()==MaDV){
                        dv_.setMadv(list.get(i).getMadv());
                        dv_.setTendv(list.get(i).getTendv());
                        break;
                    }
                }
                return dv_;
            }
        }
    }

    public static int get_max_maLX() throws SQLException {
        Connection connection = JDBCConnection.getJDBCConnection();
        //Preparing a CallableStatement
        CallableStatement cstmt = connection.prepareCall("{? = call fu_max_getMaDICHVU()}");

        cstmt.registerOutParameter(1, Types.INTEGER);
        cstmt.execute();

        int maxMaDV = cstmt.getInt(1);

        return maxMaDV;
    }
}
