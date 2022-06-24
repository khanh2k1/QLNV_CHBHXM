package qlnv.controller.nhanvien;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import qlnv.dao.DichVuDAO;
import qlnv.dao.NangLucDAO;
import qlnv.dao.NhanVienDAO;
import qlnv.entity.DICHVU;
import qlnv.entity.NANGLUC;
import qlnv.entity.NHANVIEN;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/nhanvien/NangLuc/")
public class NangLucController_nhanvien {


    // form danh sach nhan vien
    @RequestMapping(value = "DanhSachNhanVien")
    public String danhSachNhanVien(ModelMap model) throws SQLException {
        List<NHANVIEN> list = NhanVienDAO.sapXepNhanVienTheoMa();
        model.addAttribute("listNhanVien", list);
        boolean daXoa = true;


        return "nhanvien/DSnhanvien";
    }

    // form danh sach nang luc cua nhan vien
    @RequestMapping("DanhSachNhanVien/DanhSachNangLuc/{maNV}")
    public String formListNangLuc(Model model, @PathVariable("maNV")String maNV) throws SQLException {


        NANGLUC nl = NangLucDAO.getListNangLuc(maNV);
        List<DICHVU> listDichVu = DichVuDAO.getList();

        model.addAttribute("listNangLucCuaMaNV", nl);
        model.addAttribute("listDichVu", listDichVu);


        return "nhanvien/DSnangluc";
    }

}
