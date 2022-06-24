package qlnv.controller.quanli;
import javax.servlet.http.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import qlnv.custom.NhanVien;
import qlnv.dao.*;
import qlnv.entity.*;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Controller
@RequestMapping("/admin/NhanVien/")
public class NhanVienController_admin {


    // form danh sach nhan vien
    @RequestMapping(value = "DanhSachNhanVien")
    public String danhSachNhanVien(ModelMap model) throws SQLException {
        List<NHANVIEN> list = NhanVienDAO.sapXepNhanVienTheoMa();
        model.addAttribute("listNhanVien", list);
        boolean daXoa = true;


        return "admin/DSnhanvien";
    }

    // form edit thong tin nhan vien
    @RequestMapping(value = "DanhSachNhanVien/editNhanVien/{maNV}", method = RequestMethod.GET)
    public String formEditNhanVien(@PathVariable(value = "maNV") String maNV,
                                   Model model) throws SQLException {
        // dua thong tin nhan vien vao form
        NHANVIEN nv = NhanVienDAO.getNhanVienByID(maNV);
        model.addAttribute("tenLoaiNV", NhanVien.getTenLoaiNhanVien(nv.getLoaiNV()));
        model.addAttribute("nhanVien", nv);
        // tra ve .jsp
        System.out.println("loai nhan vien :" + nv.getLoaiNV());
        return "admin/ThongTinNhanVien";
    }

    // edit nhan vien
    @RequestMapping(value = "DanhSachNhanVien/editNhanVien/{maNV}", method = RequestMethod.POST)
    public String editNhanVien(@PathVariable("maNV") String maNV,
                               @ModelAttribute(value = "nhanVien") NHANVIEN nv,
                               Model model,HttpServletRequest request) throws SQLException {


        boolean check = true;
        System.out.println("ma nhan vien trong edit nhan vien: " + maNV);
        // check cong viec va nang luc khi nhan vien co trang thai la :NGHI
        if (nv.getTrangthai().equals("Nghỉ")) {

            model.addAttribute("tenLoaiNV", NhanVien.getTenLoaiNhanVien(NhanVienDAO.getNhanVienByID(maNV).getLoaiNV()));

            // lay ma nhan vien de check xem co xoa dc chinh ban than minh hoong
            HttpSession session = request.getSession();
            String manv_request = (String) session.getAttribute("maNV") ;
            System.out.println("ma nhan vien request: " + manv_request);
            if (NangLucDAO.check_nhan_vien_co_nang_luc(maNV) || CongViecDAO.check_nhan_vien_co_cong_viec(maNV) || manv_request.equals(maNV)) {
                model.addAttribute("message_trangthai", "Nhân viên chưa thể nghỉ làm !");
            } else {

                TaiKhoanDAO.delete(maNV);
                NhanVienDAO.delete(maNV);

                model.addAttribute("message_trangthai", "Cho nhân viên nghỉ làm thành công !");
                return "admin/ThongTinNhanVien";
            }
        } else {

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
                check=false;
                model.addAttribute("message_hoten", "Tên nhân viên không được để trống !");
            }
            if (nv.getHoten().length() < 2 || nv.getHoten().length() > 100) {
                check=false;
                model.addAttribute("message_hoten", "Họ tên nhân viên từ 2 kí tự và bé hơn 100 kí tự !");
            }
            // dia chi
            if (nv.getDiachi().isEmpty()) {
                check=false;
                model.addAttribute("message_diachi", "Địa chỉ không được để trống !");
            }
            // sdt
            if (nv.getSdt().isEmpty()) {
                check=false;
                model.addAttribute("message_sdt", "Số điện thoại không được để trống !");
            }
            if (!nv.getSdt().matches(phoneNumber_regex)) {
                check=false;
                model.addAttribute("message_sdt", "Nhập sai định dang số điện thoại ( chỉ có 10 số từ 1 đến 9 ) !");
            }
            if (nv.getEmail().isEmpty()) {
                check=false;
                model.addAttribute("message_email", "Email không được bỏ trống !");
            }
            if (!nv.getEmail().matches(email_regex)) {
                check=false;
                model.addAttribute("message_email", "Sai định dạng email !");
            }
            if (years < 18) {
                check=false;
                model.addAttribute("message_age", "Nhân viên chưa đủ 18 để làm việc !");
            }
            if (years > 40) {
                check=false;
                model.addAttribute("message_age", "Nhân viên quá tuổi lao động ( lớn hơn 40 ) !");
            }
            // luongcoban
            if (nv.getLuongcoban().isEmpty()) {
                check=false;
                model.addAttribute("message_luongcoban", "Lương không thể rỗng !");
            }
            if (!nv.getLuongcoban().matches(basic_salary_regex)) {
                check=false;
                model.addAttribute("message_luongcoban", "Lương phải lớn hơn 1000 và bé hơn 99.999.999 !");
            }
            if(check){

                NhanVienDAO.update(nv);
                boolean check_co_tai_khoan = false;
                for (int i = 0; i < TaiKhoanDAO.getList().size(); i++) {
                    if (TaiKhoanDAO.getList().get(i).getMaNV().equals(maNV)) {
                        check_co_tai_khoan = true;
                        System.out.println("da co tai khoan !");
                        break;
                    }
                }
                if (check_co_tai_khoan == false) {
                    TAIKHOAN tk = new TAIKHOAN();
                    System.out.println("chua co tai khoan !");
                    tk.setTendangnhap(maNV);
                    tk.setMatkhau("123");
                    tk.setMaNV(maNV);
                    tk.setQuyen(nv.getLoaiNV() != 1);

                    TaiKhoanDAO.insert(tk);
                }
                System.out.println("da update !");
            }
        }

        return "admin/ThongTinNhanVien";
    }


    // form them nhan vien quan ly
    @RequestMapping(value = "DanhSachNhanVien/addNhanVienQuanLi", method = RequestMethod.GET)
    public String formAddNhanVienQuanLi(@ModelAttribute("nhanVien") NHANVIEN nv, Model model) throws SQLException {
        // qui dinh trong csdl so 1 la NVQL, so 2 la NVSC

        nv.setMaNV(NhanVienDAO.taoMaNV(1));
        nv.setTrangthai("Đang làm");
        nv.setLoaiNV(1);
        nv.setNgaysinh(Date.valueOf(LocalDate.now()));

        model.addAttribute("tenLoaiNV", NhanVien.getTenLoaiNhanVien(nv.getLoaiNV()));
        return "admin/ThemNhanVienQuanLi";
    }

    // them nhan vien quan li
    @RequestMapping(value = "DanhSachNhanVien/addNhanVienQuanLi", method = RequestMethod.POST)
    public String addAddNhanVienQuanLi(@ModelAttribute("nhanVien") NHANVIEN nv, Model model) throws SQLException {
        // lay tuoi cua nhan vien
        LocalDate test_end = LocalDate.now();
        LocalDate test_start = nv.getNgaysinh().toLocalDate();

        long years = ChronoUnit.YEARS.between(test_start, test_end);
        //System.out.println(years); // 17


        System.out.println("tuoi của nhân viên quản lí: " + years);
        System.out.println("nam sinh cua nhan vien quan li: " + nv.getNgaysinh());


        // ten loai nhan vien
        model.addAttribute("tenLoaiNV", NhanVien.getTenLoaiNhanVien(nv.getLoaiNV()));
        // regex
        boolean check = true;
        String phoneNumber_regex = "0[0-9]{9}";
        String email_regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}";
        String basic_salary_regex = "[1-9]{1}[0-9]{3,7}";
        String ngaysinh_regex = "^\\d{4}-\\d{2}-\\d{2}$";
        // ====validate=====

        // ten nhan vien
        if (nv.getHoten().isEmpty()) {
            check=false;
            model.addAttribute("message_hoten", "Tên nhân viên không được để trống !");
        }
        if (nv.getHoten().length() < 2 || nv.getHoten().length() > 100) {
            check=false;
            model.addAttribute("message_hoten", "Họ tên nhân viên từ 2 kí tự và bé hơn 100 kí tự !");
        }
        // dia chi
        if (nv.getDiachi().isEmpty()) {
            check=false;
            model.addAttribute("message_diachi", "Địa chỉ không được để trống !");
        }
        // sdt
        if (nv.getSdt().isEmpty()) {
            check=false;
            model.addAttribute("message_sdt", "Số điện thoại không được để trống !");
        }
        if (nv.getSdt().matches(phoneNumber_regex) == false) {
            check=false;
            model.addAttribute("message_sdt", "Nhập sai định dang số điện thoại ( chỉ có 10 số từ 1 đến 9 ) !");
        }
        if (nv.getEmail().isEmpty()) {
            check=false;
            model.addAttribute("message_email", "Email không được bỏ trống !");
        }
        if (nv.getEmail().matches(email_regex) == false) {
            check=false;
            model.addAttribute("message_email", "Sai định dạng email !");
        }
        //===============================================================================
        // validate ngay sinh

        if (nv.getNgaysinh().toLocalDate().toString().isEmpty()) {
            check=false;
            model.addAttribute("message_ngaysinh", "Ngày sinh không được để trống !");
        } else if (nv.getNgaysinh().toLocalDate().toString().matches(ngaysinh_regex) == false) {

            model.addAttribute("message_ngaysinh", "Ngày sinh không hợp lệ !");
        }
        //====================================================================================

        if (years < 18) {
            check=false;
            model.addAttribute("message_age", "Nhân viên chưa đủ 18 để làm việc !");
        }
        if (years > 40) {
            check=false;
            model.addAttribute("message_age", "Nhân viên quá tuổi lao động ( lớn hơn 40 ) !");
        }
        // luongcoban
        if (nv.getLuongcoban().isEmpty()) {
            check=false;
            model.addAttribute("message_luongcoban", "Lương không thể rỗng !");
        }
        if (nv.getLuongcoban().matches(basic_salary_regex) == false) {
            check=false;
            model.addAttribute("message_luongcoban", "Lương phải lớn hơn 1000 và bé hơn 99.999.999 !");
        }


        if (check == false) return "admin/ThemNhanVienQuanLi";
            // thanh cong
        else {
            NhanVienDAO.insert(nv);
            LuongDAO.khoiTaoLuongChoNhanVien(nv.getMaNV());
            // them tai khoan
            TAIKHOAN tk = new TAIKHOAN();
            tk.setMaNV(nv.getMaNV());
            tk.setTendangnhap(nv.getMaNV());
            tk.setMatkhau("123");
            if(nv.getLoaiNV()==2) tk.setQuyen(true);
            else tk.setQuyen(false);
            TaiKhoanDAO.insert(tk);
        }

        return "redirect:/admin/NhanVien/DanhSachNhanVien";
    }

    // form them nhan vien sua chua
    @RequestMapping(value = "DanhSachNhanVien/addNhanVienSuaChua", method = RequestMethod.GET)
    public String showFormAddNhanVienSuaChua(@ModelAttribute("nhanVien") NHANVIEN nv, Model model) throws SQLException {
        nv.setMaNV(NhanVienDAO.taoMaNV(2));
        nv.setTrangthai("Đang làm");
        nv.setLoaiNV(2);
        model.addAttribute("tenLoaiNV", NhanVien.getTenLoaiNhanVien(nv.getLoaiNV()));
        return "admin/ThemNhanVienSuaChua";
    }

    // them nhan vien sua chua
    @RequestMapping(value = "DanhSachNhanVien/addNhanVienSuaChua", method = RequestMethod.POST)
    public String addNhanVien(Model model, @ModelAttribute("nhanVien") NHANVIEN nv) throws SQLException {
        // lay tuoi cua nhan vien

        LocalDate test_end = LocalDate.now();
        LocalDate test_start = nv.getNgaysinh().toLocalDate();
        long years = ChronoUnit.YEARS.between(test_start, test_end);
        //System.out.println(years); // 17
        // ten loai nhan vien

        model.addAttribute("tenLoaiNV", NhanVien.getTenLoaiNhanVien(nv.getLoaiNV()));
        // regex

        nv.setNgaysinh(Date.valueOf(LocalDate.now()));

        boolean check = true;
        String phoneNumber_regex = "0[0-9]{9}";
        String email_regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}";
        String basic_salary_regex = "[1-9]{1}[0-9]{3,7}";
        // ====validate=====
        // ten nhan vien

        if (nv.getHoten().isEmpty()) {
            check=false;
            model.addAttribute("message_hoten", "Tên nhân viên không được để trống !");
        }
        if (nv.getHoten().length() < 2 || nv.getHoten().length() > 100) {
            check=false;
            model.addAttribute("message_hoten", "Họ tên nhân viên từ 2 kí tự và bé hơn 100 kí tự !");
        }
        // dia chi
        if (nv.getDiachi().isEmpty()) {
            check=false;
            model.addAttribute("message_diachi", "Địa chỉ không được để trống !");
        }
        // sdt
        if (nv.getSdt().isEmpty()) {
            check=false;
            model.addAttribute("message_sdt", "Số điện thoại không được để trống !");
        }
        if (nv.getSdt().matches(phoneNumber_regex) == false) {
            check=false;
            model.addAttribute("message_sdt", "Nhập sai định dang số điện thoại ( chỉ có 10 số từ 1 đến 9 ) !");
        }
        if (nv.getEmail().isEmpty()) {
            check=false;
            model.addAttribute("message_email", "Email không được bỏ trống !");
        }
        if (nv.getEmail().matches(email_regex) == false) {
            check=false;
            model.addAttribute("message_email", "Sai định dạng email !");
        }
        if (years < 18) {
            check=false;
            model.addAttribute("message_age", "Nhân viên chưa đủ 18 để làm việc !");
        }
        if (years > 40) {
            check=false;
            model.addAttribute("message_age", "Nhân viên quá tuổi lao động ( lớn hơn 40 ) !");
        }
        // luongcoban
        if (nv.getLuongcoban().isEmpty()) {
            check=false;
            model.addAttribute("message_luongcoban", "Lương không thể rỗng !");
        }
        if (nv.getLuongcoban().matches(basic_salary_regex) == false) {
            check=false;
            model.addAttribute("message_luongcoban", "Lương phải lớn hơn 1000 và bé hơn 99.999.999 !");
        }

        if (check == false) return "admin/ThemNhanVienSuaChua";
            // thanh cong
        else {
            NhanVienDAO.insert(nv);
            LuongDAO.khoiTaoLuongChoNhanVien(nv.getMaNV());
            // them tai khoan
            TAIKHOAN tk = new TAIKHOAN();
            tk.setMaNV(nv.getMaNV());
            tk.setTendangnhap(nv.getMaNV());
            tk.setMatkhau("123");
            if(nv.getLoaiNV()==1) tk.setQuyen(true);
            else tk.setQuyen(false);
            TaiKhoanDAO.insert(tk);
        }

        return "redirect:/admin/NhanVien/DanhSachNhanVien";
    }

}
