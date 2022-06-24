package qlnv.controller.quanli;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import qlnv.dao.CT_CongViecDAO;
import qlnv.dao.DichVuDAO;
import qlnv.entity.CT_CONGVIEC;
import qlnv.entity.DICHVU;
import qlnv.entityCustom.BANGCONGVIEC;
import qlnv.entityCustom.CT_CONGVIEC_CUSTOM;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/admin/CT_CongViec/")
public class CT_CongViecController_admin {

    @RequestMapping("SoLuongCongViec")
    public String formDanhSach(Model model) throws SQLException {

        List<CT_CONGVIEC_CUSTOM> listCTCV_custom = CT_CongViecDAO.listSoLuongCongViec();
        model.addAttribute("listCTCV_custom",listCTCV_custom);

        return "admin/CT_CongViec";
    }




}
