//package qlnv.controller.admin;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import qlnv.custom.NhanVien;
//import qlnv.dao.NhanVienDAO;
//import qlnv.entity.NHANVIEN;
//
//import java.sql.SQLException;
//import java.time.LocalDate;
//import java.time.temporal.ChronoUnit;
//
//@Controller
//@RequestMapping("/NhanVienQuanLi/")
//public class ThongTinCaNhanConTroller {
//
//    // form edit thong tin nhan vien
//    @RequestMapping(value = "ThongTinCaNhan/{maNV}", method = RequestMethod.GET)
//    public String formEditThongTinCaNhan(@PathVariable(value = "maNV") String maNV,
//                                   Model model,
//                                   String tenLoaiNhanVien) throws SQLException {
//        // dua thong tin nhan vien vao form
//        NHANVIEN nv = NhanVienDAO.getNhanVienByID(maNV);
//        model.addAttribute("tenLoaiNV", NhanVien.getTenLoaiNhanVien(nv.getLoaiNV()));
//        model.addAttribute("nhanVien", nv);
//        // tra ve .jsp
//        return "admin/ThongTinNhanVien";
//    }
//
//    // edit nhan vien
//    @RequestMapping(value = "editNhanVien/{maNV}", method = RequestMethod.POST)
//    public String editThongTinCaNhan(@PathVariable("maNV") String maNV,
//                               @ModelAttribute(value = "nhanVien") NHANVIEN nv,
//                               Model model) throws SQLException {
//
//        // lay tuoi cua nhan vien
//        LocalDate test_end = LocalDate.now();
//        LocalDate test_start = nv.getNgaysinh().toLocalDate();
//        long years = ChronoUnit.YEARS.between(test_start, test_end);
//        //System.out.println(years); // 17
//
//        // lay ten loai nhan vien
//        model.addAttribute("tenLoaiNV",NhanVien.getTenLoaiNhanVien(nv.getLoaiNV()));
//
//        // regex
//        boolean check = true;
//        String phoneNumber_regex="0[0-9]{9}";
//        String email_regex="^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}";
//        String basic_salary_regex = "[1-9]{1}[0-9]{3,7}";
//        // ====validate=====
//        // ten nhan vien
//
//        if (nv.getHoten().isEmpty()) {
//            check=false;
//            model.addAttribute("message_hoten", "Tên nhân viên không được để trống !");
//        }
//        if (nv.getHoten().length() < 2 || nv.getHoten().length() > 100) {
//            check=false;
//            model.addAttribute("message_hoten", "Họ tên nhân viên từ 2 kí tự và bé hơn 100 kí tự !");
//        }
//        // dia chi
//        if (nv.getDiachi().isEmpty()) {
//            check=false;
//            model.addAttribute("message_diachi", "Địa chỉ không được để trống !");
//        }
//        // sdt
//        if(nv.getSdt().isEmpty()){
//            check=false;
//            model.addAttribute("message_sdt", "Số điện thoại không được để trống !");
//        }
//        if(nv.getSdt().matches(phoneNumber_regex)==false){
//            check=false;
//            model.addAttribute("message_sdt","Nhập sai định dang số điện thoại ( chỉ có 10 số từ 1 đến 9 ) !");
//        }
//        if(nv.getEmail().isEmpty()){
//            check=false;
//            model.addAttribute("message_email","Email không được bỏ trống !");
//        }
//        if(nv.getEmail().matches(email_regex)==false){
//            check=false;
//            model.addAttribute("message_email","Sai định dạng email !");
//        }
//        if(years < 18){
//            check=false;
//            model.addAttribute("message_age","Nhân viên chưa đủ 18 để làm việc !");
//        }
//        if(years > 40){
//            check=false;
//            model.addAttribute("message_age","Nhân viên quá tuổi lao động ( lớn hơn 40 ) !");
//        }
//        // luongcoban
//        if(nv.getLuongcoban().isEmpty()){
//            check=false;
//            model.addAttribute("message_luongcoban","Lương không thể rỗng !");
//        }
//        if(nv.getLuongcoban().matches(basic_salary_regex)==false){
//            check=false;
//            model.addAttribute("message_luongcoban","Lương phải lớn hơn 1000 và bé hơn 99.999.999 !");
//        }
//        // thanh cong
//        if(check==false) return "admin/ThongTinNhanVien";
//        else NhanVienDAO.update(nv);
//
//        return "redirect:/NhanVienQuanLi/DanhSachNhanVien";
//    }
//}
