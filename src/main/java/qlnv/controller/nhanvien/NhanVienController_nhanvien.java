package qlnv.controller.nhanvien;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import qlnv.custom.NhanVien;
import qlnv.dao.CongViecDAO;
import qlnv.dao.NangLucDAO;
import qlnv.dao.NhanVienDAO;
import qlnv.dao.TaiKhoanDAO;
import qlnv.entity.NHANVIEN;
import qlnv.entity.TAIKHOAN;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Controller
@RequestMapping("/nhanvien/NhanVien/")
public class NhanVienController_nhanvien {

    // form edit thong tin nhan vien
    @RequestMapping(value = "editNhanVien/{maNV}", method = RequestMethod.GET)
    public String formEditNhanVien(@PathVariable(value = "maNV") String maNV,
                                   Model model) throws SQLException {
        // dua thong tin nhan vien vao form
        NHANVIEN nv = NhanVienDAO.getNhanVienByID(maNV);
        model.addAttribute("tenLoaiNV", NhanVien.getTenLoaiNhanVien(nv.getLoaiNV()));
        model.addAttribute("nhanVien", nv);
        // tra ve .jsp
        System.out.println("loai nhan vien :" + nv.getLoaiNV());
        return "nhanvien/ThongTinNhanVien";
    }

    // edit nhan vien
    @RequestMapping(value = "editNhanVien/{maNV}", method = RequestMethod.POST)
    public String editNhanVien(@PathVariable("maNV") String maNV,
                               @ModelAttribute(value = "nhanVien") NHANVIEN nv,
                               Model model, HttpServletRequest request) throws SQLException {


        boolean check = true;
        System.out.println("ma nhan vien trong edit nhan vien: " + maNV);
        // check cong viec va nang luc khi nhan vien co trang thai la :NGHI


        // lay tuoi cua nhan vien
        LocalDate test_end = LocalDate.now();
        LocalDate test_start = nv.getNgaysinh().toLocalDate();
        long years = ChronoUnit.YEARS.between(test_start, test_end);
        //System.out.println(years); // 17

        // lay ten loai nhan vien
        model.addAttribute("tenLoaiNV", NhanVien.getTenLoaiNhanVien(nv.getLoaiNV()));

        // regex

        String phoneNumber_regex = "0[0-9]{9}";
        String email_regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}";
        String basic_salary_regex = "[1-9]{1}[0-9]{3,7}";
        String ngaysinh_regex = "^\\d{4}-\\d{2}-\\d{2}$";
        // ====validate=====

        // validate ngaysinh
        if (nv.getNgaysinh().toLocalDate().toString().matches(ngaysinh_regex) == false) {

        }
        // ==============================
        if (nv.getHoten().isEmpty()) {
            check = false;
            model.addAttribute("message_hoten", "Tên nhân viên không được để trống !");
        }
        if (nv.getHoten().length() < 2 || nv.getHoten().length() > 100) {
            check = false;
            model.addAttribute("message_hoten", "Họ tên nhân viên từ 2 kí tự và bé hơn 100 kí tự !");
        }
        // dia chi
        if (nv.getDiachi().isEmpty()) {
            check = false;
            model.addAttribute("message_diachi", "Địa chỉ không được để trống !");
        }
        // sdt
        if (nv.getSdt().isEmpty()) {
            check = false;
            model.addAttribute("message_sdt", "Số điện thoại không được để trống !");
        }
        if (!nv.getSdt().matches(phoneNumber_regex)) {
            check = false;
            model.addAttribute("message_sdt", "Nhập sai định dang số điện thoại ( chỉ có 10 số từ 1 đến 9 ) !");
        }
        if (nv.getEmail().isEmpty()) {
            check = false;
            model.addAttribute("message_email", "Email không được bỏ trống !");
        }
        if (!nv.getEmail().matches(email_regex)) {
            check = false;
            model.addAttribute("message_email", "Sai định dạng email !");
        }
        if (years < 18) {
            check = false;
            model.addAttribute("message_age", "Nhân viên chưa đủ 18 để làm việc !");
        }
        if (years > 40) {
            check = false;
            model.addAttribute("message_age", "Nhân viên quá tuổi lao động ( lớn hơn 40 ) !");
        }

        if(check==true){
            NhanVienDAO.update(nv);
        }

        return "nhanvien/ThongTinNhanVien";
    }

}
