package qlnv.custom;

import qlnv.dao.LoaiNhanVienDAO;
import qlnv.entity.LOAINHANVIEN;

import java.sql.SQLException;
import java.util.List;

public class NhanVien {
    // ten loai nhan vien
    public static String getTenLoaiNhanVien(int x) throws SQLException {
        // lay ten loai nhan vien
        List<LOAINHANVIEN> listLoaiNhanVien = LoaiNhanVienDAO.getList();
        String tenLoaiNhanVien = new String();
        for (int i = 0; i < listLoaiNhanVien.size(); i++) {
            if (x == listLoaiNhanVien.get(i).getMaloai()) {
                tenLoaiNhanVien = listLoaiNhanVien.get(i).getTenloai();
                break;
            }
        }
        return tenLoaiNhanVien;
    }
}
