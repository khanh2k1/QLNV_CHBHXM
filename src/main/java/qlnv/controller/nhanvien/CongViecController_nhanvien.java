package qlnv.controller.nhanvien;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import qlnv.dao.CongViecDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("nhanvien/CongViec/")
public class CongViecController_nhanvien {

    @RequestMapping("DanhSachCongViec")
    public String danhSachCongViec(Model model, HttpServletRequest request){
        // lay thanh cong ma nhan vien
        HttpSession session = request.getSession();
        String manv = (String) session.getAttribute("maNV");
        System.out.println("ma nhan vien ( chuc nang nhan vien) : " + manv);

        // lay ra list cong viec cua moi nhan vien do
        model.addAttribute("listCongViecCuaMotNhanVien",CongViecDAO.listCongViecCuaMotNhanVien(manv));
        return "nhanvien/DScongviec";
    }
}
