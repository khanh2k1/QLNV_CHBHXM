package qlnv.controller.quanli;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import qlnv.dao.DichVuDAO;
import qlnv.dao.NangLucDAO;
import qlnv.entity.DICHVU;
import qlnv.entity.NANGLUC;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/NangLuc/")
public class NangLucController_admin {


    // form danh sach nang luc cua nhan vien
    @RequestMapping("DanhSachNangLuc/{maNV}")
    public String formListNangLuc(Model model, @PathVariable("maNV")String maNV) throws SQLException {
        NANGLUC nl = NangLucDAO.getListNangLuc(maNV);
        List<DICHVU> listDichVu = DichVuDAO.getList();

        model.addAttribute("listNangLucCuaMaNV", nl);
        model.addAttribute("listDichVu", listDichVu);

        return "admin/DSnangluc";
    }

    @RequestMapping(value = "DanhSachNangLuc/editNangLuc/{maNV}", method = RequestMethod.GET)
    public String formNangLuc(@PathVariable("maNV")String maNV, Model model, HttpServletRequest request) throws SQLException {

        List<DICHVU> listDichVu = DichVuDAO.getList();
        NANGLUC nl = new NANGLUC();

        model.addAttribute("nangLuc",nl);
        model.addAttribute("listDichVu", listDichVu);

        return "admin/SuaNangLuc";
    }

    @RequestMapping(value = "DanhSachNangLuc/showNangLuc/{maNV}", method = RequestMethod.POST)
    public String formNangLuc(@PathVariable("maNV")String maNV, @ModelAttribute("nl")NANGLUC nl) throws SQLException {

        NANGLUC nl_1 = new NANGLUC();
        nl_1.setMaNV(maNV);
        nl_1.setListMaDV(nl.getListMaDV());

        NangLucDAO.update(nl_1);
        return "redirect:/admin/NangLuc/DanhSachNangLuc/"+maNV;
    }
}
