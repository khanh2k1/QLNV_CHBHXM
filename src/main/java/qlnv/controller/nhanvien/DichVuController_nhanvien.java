package qlnv.controller.nhanvien;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import qlnv.dao.DichVuDAO;

import java.sql.SQLException;

@Controller
@RequestMapping("nhanvien/DichVu/")
public class DichVuController_nhanvien {
    @RequestMapping("DanhSachDichVu")
    public String showListDichVu(Model model) throws SQLException {
        model.addAttribute("listDichVu", DichVuDAO.getList());
        return "nhanvien/DSdichvu";
    }
}
