package qlnv.controller.quanli;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import qlnv.dao.BangGiaDAO;
import qlnv.dao.DichVuDAO;
import qlnv.dao.NangLucDAO;
import qlnv.entity.DICHVU;

import java.sql.SQLException;
import java.util.List;
@Controller
@RequestMapping("/admin/DichVu/")
public class DichVuController_admin {


    @ModelAttribute("listDichVu")
    public List<DICHVU> getListDichVu() throws SQLException {
        List<DICHVU> list = DichVuDAO.getList();
        return list;
    }

    // form danh sach loai xe
    @RequestMapping(value = "DanhSachDichVu")
    public String getDanhSachDichVu(@ModelAttribute("listDichVu") List<DICHVU> listDichVu){
        return "admin/DSdichvu";
    }

    // form sua thong tin dich vu
    @RequestMapping(value="DanhSachDichVu/editDichVu/{madv}", method = RequestMethod.GET)
    public String formEditDichVu(@PathVariable("madv") int madv, Model model) throws SQLException {
        DICHVU dv = DichVuDAO.findByID(madv);
        model.addAttribute("dichVu", dv);
        // tra ve .jsp
        return "admin/ThongTinDichVu";
    }

    // sua dich vu
    @RequestMapping(value = "DanhSachDichVu/editDichVu/{madv}", method = RequestMethod.POST)
    public String editSaveDichVu(@PathVariable("madv") int madv,
                                 @ModelAttribute("dichVu") DICHVU dv,
                                 Model model,
                                 @ModelAttribute("listDichVu") List<DICHVU> listDichVu) throws SQLException {
        // check validate
        boolean check = true;

        if(dv.getTendv().isEmpty()){
            model.addAttribute("message_dichvu","Tên dịch vụ không được rỗng !");
            check=false;
        }
        if(dv.getTendv().length()<3){
            model.addAttribute("message_dichvu","Tên dịch vụ không hợp lệ !");
            check=false;
        }
        for(int i=0;i<listDichVu.size();i++){
            if(dv.getTendv().equals(listDichVu.get(i).getTendv())){
                model.addAttribute("message_dichvu","Tên dịch vụ đã có !");
                check=false;
                break;
            }
        }
        if(check==true) DichVuDAO.update(dv);
        else return "admin/ThongTinDichVu";

        // tra ve url
        return "redirect:/admin/DichVu/DanhSachDichVu";
    }

    // form them dich vu
    @RequestMapping(value="DanhSachDichVu/addDichVu")
    public String showFormAddDichVu(@ModelAttribute("dichVu") DICHVU dv) throws SQLException {
        // tu dong tang ma dich vu
        dv.setMadv(DichVuDAO.get_max_maLX()+1);
        return "admin/ThemDichVu";
    }

    // them dich vu
    @RequestMapping(value="DanhSachDichVu/addDichVu", method = RequestMethod.POST)
    public String addDichVu(Model model,
                            @ModelAttribute("dichVu") DICHVU dv,
                            @ModelAttribute("listDichVu") List<DICHVU> listDichVu) throws SQLException {
        // dung @ModelAttribute de lay dv ve
        // check validate
        boolean check = true;

        if(dv.getTendv().isEmpty()){
            model.addAttribute("message_dichvu","Tên dịch vụ không được rỗng !");
            check=false;
        }
        if(dv.getTendv().length()<3){
            model.addAttribute("message_dichvu","Tên dịch vụ không hợp lệ !");
            check=false;
        }
        for(int i=0;i<listDichVu.size();i++){
            if(dv.getTendv().equals(listDichVu.get(i).getTendv())){
                model.addAttribute("message_dichvu","Tên dịch vụ đã có !");
                check=false;
                break;
            }
        }
        if(check==true) DichVuDAO.insert(dv);
        else return "admin/ThemDichVu";

        // tra ve url
        return "redirect:/admin/DichVu/DanhSachDichVu";
    }


    @RequestMapping("DanhSachDichVu/XoaDichVu/{madv}")
    public String xoaDichVu(@PathVariable("madv")int madv, Model model, @ModelAttribute("dichVu")DICHVU dichvu) throws SQLException {

        // check dichvu co trong bang gia
        boolean check_xoa = true;
        if(DichVuDAO.check_madv_banggia_nangluc(madv)){
            model.addAttribute("message_dichvu","Dịch vụ này chưa thể xóa !");
            dichvu.setTendv(DichVuDAO.findByID(madv).getTendv());
        }else{
            DichVuDAO.delete(madv);
            return "redirect:/admin/DichVu/DanhSachDichVu";
        }

        return "admin/ThongTinDichVu";
    }
}
