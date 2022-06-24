package qlnv.controller.nhanvien;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import qlnv.dao.BangGiaDAO;
import qlnv.dao.BangGia_customDAO;
import qlnv.dao.DichVuDAO;
import qlnv.dao.LoaiXeDAO;
import qlnv.entity.BANGGIA;
import qlnv.entity.DICHVU;
import qlnv.entity.LOAIXE;
import qlnv.entityCustom.BANGGIA_custom;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/nhanvien/BangGia/")
public class BangGiaController_nhanvien {


    // form tra cuu bang gia
    @RequestMapping(value = "DanhSachBangGia", method = RequestMethod.GET)
    public String showDanhSachBangGia(Model model) throws SQLException {

        List<BANGGIA_custom> listBangGia_custom = BangGia_customDAO.getList();
        model.addAttribute("listBangGia_custom", listBangGia_custom);

        return "nhanvien/DSbanggia";
    }
}
