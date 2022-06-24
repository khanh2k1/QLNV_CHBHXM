package qlnv.controller.nhanvien;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import qlnv.dao.LoaiXeDAO;
import qlnv.entity.LOAIXE;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/nhanvien/LoaiXe/")
public class LoaiXeController_nhanvien {

    //form danh sach loai xe
    @RequestMapping(value = "DanhSachLoaiXe")
    public String danhSachLoaiXe(ModelMap modelMap) throws SQLException {
        List<LOAIXE> list = LoaiXeDAO.getList();
        modelMap.addAttribute("listLoaiXe", list);
        return "nhanvien/DSloaixe";
    }
}
