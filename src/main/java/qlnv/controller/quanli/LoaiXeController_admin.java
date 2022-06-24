package qlnv.controller.quanli;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import qlnv.dao.BangGiaDAO;
import qlnv.dao.LoaiXeDAO;
import qlnv.entity.LOAIXE;


import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("admin/LoaiXe/")
public class LoaiXeController_admin {

    //form danh sach loai xe
    @RequestMapping(value = "DanhSachLoaiXe")
    public String danhSachLoaiXe(ModelMap modelMap) throws SQLException {
        List<LOAIXE> list = LoaiXeDAO.getList();
        modelMap.addAttribute("listLoaiXe", list);
        return "admin/DSloaixe";
    }

    // form sua thong tin loai xe
    @RequestMapping(value = "DanhSachLoaiXe/editLoaiXe/{maLX}", method = RequestMethod.GET)
    public String formEditLoaiXe(@PathVariable(value = "maLX") int maLX,
                                 Model model) throws SQLException {
        LOAIXE loaiXe = LoaiXeDAO.findByID(maLX);
        model.addAttribute("loaiXe",loaiXe);
        // tra ve .jsp
        return "admin/ThongTinLoaiXe";
    }

    // sua thong tin loai xe
    @RequestMapping(value = "DanhSachLoaiXe/editLoaiXe/{maLX}", method = RequestMethod.POST)
    public String editLoaiXe(@PathVariable(value = "maLX") int maLX,
                                 @ModelAttribute(value = "loaiXe") LOAIXE loaiXe,
                                 Model model) throws SQLException {
        // validation
        boolean check=true;

        if(loaiXe.getTenLX().isEmpty()){
            model.addAttribute("message_tenlx","Tên loại xe không được bỏ trống !");
            check=false;
        }
        else if(loaiXe.getTenLX().length()<4 || loaiXe.getTenLX().length()>100){
            model.addAttribute("message_tenlx","Tên loại xe không hợp lệ !");
            check=false;
        }

        if(check==true) LoaiXeDAO.update(loaiXe);
        // neu that bai
        else return "/admin/ThongTinLoaiXe";

        // tra ve url
        return "redirect:/admin/LoaiXe/DanhSachLoaiXe";
    }

    // xoa loai xe
    @RequestMapping(value = "DanhSachLoaiXe/deleteLoaiXe/{maLX}", method = RequestMethod.GET)
    public String deleteLoaiXe(@PathVariable("maLX") int maLX, Model model, @ModelAttribute("loaiXe")LOAIXE loaixe) throws SQLException {

        // xoa khoa ngoai thi moi xoa khoa chinh dc
        // BANG GIA
        // chen them popup trc khi xoa
        // xoa loai xe thi phai xoa bang gia
        if( BangGiaDAO.check_malx_banggia(maLX)){
            model.addAttribute("message_tenlx","Chưa thể xóa xe này !");
            loaixe = LoaiXeDAO.findByID(maLX);
            model.addAttribute("loaiXe",loaixe);
            return "admin/ThongTinLoaiXe";
        }else{
            model.addAttribute("message_tenlx","Xóa loại xe thành công !");
            LoaiXeDAO.delete(maLX);
            return "admin/ThongTinLoaiXe";
        }

    }

    // form them loai xe
    @RequestMapping(value = "DanhSachLoaiXe/addLoaiXe", method = RequestMethod.GET)
    public String showFormAddLoaiXe(@ModelAttribute("loaiXe") LOAIXE lx) throws SQLException {
        lx.setMaLX(LoaiXeDAO.get_max_maLX() + 1);
        return "admin/ThemLoaiXe";
    }

    // them loai xe
    @RequestMapping(value = "DanhSachLoaiXe/addLoaiXe", method = RequestMethod.POST)
    public String addLoaiXe(Model model, @ModelAttribute("loaiXe") LOAIXE lx) throws SQLException {
        List<LOAIXE> list = LoaiXeDAO.getList();
        boolean check = true;
        // kiem tra trung ten loai xe

        if(lx.getTenLX().isEmpty()){
            model.addAttribute("message_tenlx","Tên loại xe không được trống !");
            check=false;
        }
        if(lx.getTenLX().length()<3){
            model.addAttribute("message_tenlx","Tên loại xe không hợp lệ !");
            check=false;
        }
        for (LOAIXE loaixe: list
             ) {
            if(loaixe.getTenLX().equals(lx.getTenLX())){
                model.addAttribute("message_tenlx","Tên loại xe không được trùng nhau !");
                check=false;
                break;
            }
        }

        // neu khong trung ten loai xe thi them vao
        if(check==true){
            LoaiXeDAO.insert(lx);
        }
        // van o lai trang
        else return "admin/ThemLoaiXe";

        return "redirect:/admin/LoaiXe/DanhSachLoaiXe";
    }

}
