package qlnv.controller.quanli;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import qlnv.dao.LuongDAO;
import qlnv.dao.Luong_customDAO;
import qlnv.entity.LUONG;
import qlnv.entityCustom.LUONG_CUSTOM;
import qlnv.entityCustom.THANG_NAM_SONGAYTHUCLAM;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/Luong/")
public class LuongController_admin {

    @RequestMapping(value = "DanhSachLuongTheoThang/{thang}_{nam}", method = RequestMethod.GET)
    public String listLuongTheoThang(Model model,@ModelAttribute("thang_nam_songaythuclam") THANG_NAM_SONGAYTHUCLAM thang_nam_songaythuclam){

        thang_nam_songaythuclam = new THANG_NAM_SONGAYTHUCLAM();
        thang_nam_songaythuclam.setThang(LocalDateTime.now().getMonthValue());
        thang_nam_songaythuclam.setNam(LocalDateTime.now().getYear());


        List<LUONG_CUSTOM> listLuong_custom = Luong_customDAO.getList(thang_nam_songaythuclam.getThang(),thang_nam_songaythuclam.getNam());

        model.addAttribute("listLuongNhanVienTheoThang",listLuong_custom);

        // truyen vao 12 thang
        List<Integer> listThang = new ArrayList<>();
        for(int i=1;i<=12;i++){
            listThang.add(i);
        }
        model.addAttribute("listThang",listThang);

        // truyen vao 20 nam tinh tu nam hien tai
        List<Integer> listNam = new ArrayList<>();
        for(int i=LocalDateTime.now().getYear();i>=LocalDateTime.now().getYear()-20;i--){
            listNam.add(i);
        }
        model.addAttribute("listNam",listNam);

        // tong doanh thu thang ?
        Long tongDoanhThuTheoThang = 0L;
        for(int i=0;i< Luong_customDAO.getList(thang_nam_songaythuclam.getThang(), thang_nam_songaythuclam.getNam()).size();i++){
            tongDoanhThuTheoThang = tongDoanhThuTheoThang + Luong_customDAO.getList(thang_nam_songaythuclam.getThang(), thang_nam_songaythuclam.getNam()).get(i).getTongdoanhthu();
        }
        model.addAttribute("tongdoanhthutheothang",tongDoanhThuTheoThang);
        System.out.println("tong doanh thu theo thang: " + tongDoanhThuTheoThang);


        return "admin/DSluong";
    }

    @RequestMapping(value = "DanhSachLuongTheoThang/{thang}_{nam}", method = RequestMethod.POST)
    public String listLuongTheoThang2(Model model, @ModelAttribute("thang_nam_songaythuclam") THANG_NAM_SONGAYTHUCLAM thang_nam_songaythuclam){

        // truyen vao 12 thang
        List<Integer> listThang = new ArrayList<>();
        for(int i=1;i<=12;i++){
            listThang.add(i);
        }
        model.addAttribute("listThang",listThang);

        // tong doanh thu thang ?
        Long tongDoanhThuTheoThang = 0L;
        for(int i=0;i< Luong_customDAO.getList(thang_nam_songaythuclam.getThang(), thang_nam_songaythuclam.getNam()).size();i++){
            tongDoanhThuTheoThang = tongDoanhThuTheoThang + Luong_customDAO.getList(thang_nam_songaythuclam.getThang(), thang_nam_songaythuclam.getNam()).get(i).getTongdoanhthu();
        }
        model.addAttribute("tongdoanhthutheothang",tongDoanhThuTheoThang);

        // truyen vao 20 nam tinh tu nam hien tai
        List<Integer> listNam = new ArrayList<>();
        for(int i=LocalDateTime.now().getYear();i>=LocalDateTime.now().getYear()-20;i--){
            listNam.add(i);
        }
        model.addAttribute("listNam",listNam);


        List<LUONG_CUSTOM> listLuong_custom = Luong_customDAO.getList(thang_nam_songaythuclam.getThang(),thang_nam_songaythuclam.getNam());

        model.addAttribute("listLuongNhanVienTheoThang",listLuong_custom);


        return "admin/DSluong";
    }

    @RequestMapping(value = "/DanhSachLuongTheoThang/{manv}/{thang}/{nam}/{songaythuclam}",method = RequestMethod.GET)
    public String formSuaSoNgayThucLam(Model model, @PathVariable("manv")String manv,@PathVariable("thang")int thang,
                                       @PathVariable("nam")int nam, @PathVariable("songaythuclam")int songaythuclam){

        model.addAttribute("manv",manv);
        model.addAttribute("thang",thang);
        model.addAttribute("nam", nam);
        LUONG luong = new LUONG();
        luong = LuongDAO.get_1_thang_luong_cua_1_nhan_vien(manv, thang, nam);
        model.addAttribute("luong",luong);

        return "admin/FormEditSoNgayThucLam";
    }

    @RequestMapping(value = "/DanhSachLuongTheoThang/{manv}/{thang}/{nam}/{songaythuclam}",method = RequestMethod.POST)
    public String suaSoNgayThucLam(Model model, @ModelAttribute("luong")LUONG luong, @PathVariable("manv")String manv,@PathVariable("thang")int thang,
                                       @PathVariable("nam")int nam, @PathVariable("songaythuclam")int songaythuclam) throws SQLException {

        if(luong.getSongaythuclam()<0 || luong.getSongaythuclam()>20){
            model.addAttribute("message_songaythuclam","Số ngày làm không hợp lệ !");
            return "admin/FormEditSoNgayThucLam";
        }
        if(luong.getMaNV().isEmpty()){
            model.addAttribute("message_songaythuclam","Ma nhan vien không hợp lệ !");
            return "admin/FormEditSoNgayThucLam";
        }
        else{
            System.out.println("thang: " + luong.getThang());
            System.out.println("ma nhan vien: " + luong.getNam());
            System.out.println("nam: " + luong.getNam());
            System.out.println("so ngay thuc lam sau khi update: " + luong.getSongaythuclam());
            System.out.println("ket qua update luong: " + LuongDAO.update_so_ngay_thuc_lam(luong.getSongaythuclam(), manv, thang, nam));
            return "redirect:/admin/Luong/DanhSachLuongTheoThang/"+thang+"_"+nam;
        }

    }
}
