package qlnv.controller.quanli;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import qlnv.dao.TaiKhoanDAO;
import qlnv.entity.TAIKHOAN;
import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("admin/TaiKhoan/")
public class TaiKhoanController_admin {
    // hien thi form thong tin loaixe
    @RequestMapping(value = "DanhSachTaiKhoan")
    public String danhSachTaiKhoan(ModelMap modelMap) throws SQLException {
        List<TAIKHOAN> list = TaiKhoanDAO.getList();
        modelMap.addAttribute("listTaiKhoan", list);
        return "admin/DStaikhoan";
    }

    // hien thi thong tin taiKhoan
    @RequestMapping(value="DanhSachTaiKhoan/editTaiKhoan/{maTK}")
    public String editTaiKhoan(@PathVariable String maTK, Model model) throws SQLException {
        TAIKHOAN tk = TaiKhoanDAO.getTaiKhoan(maTK);
        model.addAttribute("taiKhoan", tk);
        // tra ve .jsp
        return "admin/ThongTinTaiKhoan";
    }

    @RequestMapping(value = "DanhSachTaiKhoan/editTaiKhoan/{maTK}", method = RequestMethod.POST)
    public String editSaveTaiKhoan(@PathVariable("maTK") String matk,
                                   @ModelAttribute("taiKhoan") TAIKHOAN tk, Model model) throws SQLException {

        String password_regex = "[a-zA-Z0-9]{3,30}";
        boolean check = true;
        if(tk.getMatkhau().isEmpty() || tk.getMatkhau().matches(password_regex)==false){
            model.addAttribute("message_mk","Mật khẩu không hợp lệ !");
            check=false;
        }

        if(check==true){
            TaiKhoanDAO.update(tk);
            model.addAttribute("message_mk","Lưu thành công !");
        }
        // tra ve url
        return "admin/ThongTinTaiKhoan";
    }

//    @RequestMapping(value="deleteTaiKhoan/{maTK}", method = RequestMethod.GET)
//    public String deleteTaiKhoan(@PathVariable String maTK) throws SQLException {
//        TaiKhoanDAO.delete(maTK);
//        return "redirect:/NhanVienQuanLi/DanhSachTaiKhoan";
//    }

    @RequestMapping(value="addTaiKhoan")
    public String showFormAddTaiKhoan(@ModelAttribute("taiKhoan") TAIKHOAN tk) throws SQLException {
        return "admin/ThemTaiKhoan";
    }

    @RequestMapping(value="addTaiKhoan", method = RequestMethod.POST)
    public String addTaiKhoan(Model model, @ModelAttribute("taiKhoan") TAIKHOAN tk) throws SQLException {
        // dung @ModelAttribute de lay tk ve
        List<TAIKHOAN> list = TaiKhoanDAO.getList();
        list.add(tk);
        TaiKhoanDAO.insert(tk);
        return "redirect:/NhanVienQuanLi/DanhSachTaiKhoan";
    }
}
