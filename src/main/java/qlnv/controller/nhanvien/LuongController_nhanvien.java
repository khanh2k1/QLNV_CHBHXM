package qlnv.controller.nhanvien;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import qlnv.dao.Luong_customDAO;
import qlnv.entityCustom.LUONG_CUSTOM;
import qlnv.entityCustom.THANG_NAM_SONGAYTHUCLAM;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/nhanvien/Luong/")
public class LuongController_nhanvien {

    @RequestMapping(value = "DanhSachLuongTheoThang/{thang}_{nam}", method = RequestMethod.GET)
    public String listLuongTheoThangCuaMotNhanVien(Model model, @PathVariable("thang")int thang, @PathVariable("nam")int nam,
                                                   HttpServletRequest request){

        THANG_NAM_SONGAYTHUCLAM thang_nam_songaythuclam = new THANG_NAM_SONGAYTHUCLAM();
        thang_nam_songaythuclam.setThang(thang);
        thang_nam_songaythuclam.setNam(nam);

        HttpSession session = request.getSession();
        String manv = (String) session.getAttribute("maNV");

        List<LUONG_CUSTOM> listLuong_custom = Luong_customDAO.getList_ByMaNV(thang,nam,manv);

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


        model.addAttribute("thang_nam_songaythuclam",thang_nam_songaythuclam);

        return "nhanvien/DSluongcuanhanvien";
    }

    @RequestMapping(value = "DanhSachLuongTheoThang/{thang}_{nam}", method = RequestMethod.POST)
    public String listLuongTheoThangCuaMotNhanVien2(HttpServletRequest request, Model model, @ModelAttribute("thang_nam_songaythuclam") THANG_NAM_SONGAYTHUCLAM thang_nam_songaythuclam){

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

        // lay ma nhan vien
        HttpSession session = request.getSession();
        String manv = (String) session.getAttribute("maNV");

        // tong doanh thu thang ?
        long tongDoanhThuTheoThang = 0L;
        for(int i=0;i< Luong_customDAO.getList_ByMaNV(thang_nam_songaythuclam.getThang(), thang_nam_songaythuclam.getNam(),manv).size();i++){
            tongDoanhThuTheoThang = tongDoanhThuTheoThang + Luong_customDAO.getList_ByMaNV(thang_nam_songaythuclam.getThang(), thang_nam_songaythuclam.getNam(),manv).get(i).getTongdoanhthu();
        }
        model.addAttribute("tongdoanhthutheothang",tongDoanhThuTheoThang);



        List<LUONG_CUSTOM> listLuong_custom = Luong_customDAO.getList_ByMaNV(thang_nam_songaythuclam.getThang(),thang_nam_songaythuclam.getNam(),manv);

        model.addAttribute("listLuongNhanVienTheoThang",listLuong_custom);


        return "nhanvien/DSluongcuanhanvien";
    }
}
