package qlnv.controller.quanli;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import qlnv.dao.*;
import qlnv.entity.*;
import qlnv.entityCustom.BANGGIA_custom;
import qlnv.entityCustom.CONGVIECCUANHANVIEN;

import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/admin/CongViec/")
public class CongViecController_admin {

    // danh sach cong viec da hoan thanh va chua hoan thanh cua cac nhan vien
    @RequestMapping("DanhSachCongViec")
    public String formDanhSachCongViec(Model model){

        List<CONGVIECCUANHANVIEN> listCongViecCuaNhanVien = CongViecDAO.listCongViecCuaNhanVien();
        model.addAttribute("listCongViecCuaNhanVien", listCongViecCuaNhanVien);

        return "admin/DScongviec";
    }

    // button Load lai cong viec de check cong viec cua nhan vien


    //=====================================================================================
    //=====================================================================================
    // qua 1 url moi de them cong viec cho nhan vien
    @RequestMapping(value = "/DanhSachCongViec/ThemCongViec/DanhSachBangGia", method = RequestMethod.GET)
    public String formThemCongViec(Model model) throws SQLException {

        // lay bang gia ma trang thai la dang dung
        List<BANGGIA_custom> listBG_custom = new ArrayList<>();

        for(int i=0;i<BangGia_customDAO.getList().size();i++){
            if(BangGia_customDAO.getList().get(i).getTrangthai().equals("Đang dùng")){
                listBG_custom.add(BangGia_customDAO.getList().get(i));
            }
        }
        model.addAttribute("listBG_custom",listBG_custom);

        return "admin/DSbanggia_de_them_cong_viec";
    }

    // hien ra list nhan vien sua chua co nang luc tuong duong va co trang thai la DANG LAM
    @RequestMapping(value = "/DanhSachCongViec/ThemCongViec/DanhSachBangGia/{madg}/DanhSachNhanVien",method = RequestMethod.GET)
    public String formChonNhanVienCoNangLucChoCongViec(Model model, @PathVariable("madg")int madg) throws SQLException {

//        // lay dich vu va loai xe tu ma don gia
//        BANGGIA_custom bg_custom = new BANGGIA_custom();
//        bg_custom = BangGia_customDAO.getBangGia_by_madg(madg);


        BANGGIA bg = BangGiaDAO.findBangGia(madg);
        int madv = bg.getMadv();
        // list nhan vien
        List<NHANVIEN> listNhanVien = NhanVienDAO.listNhanVienCanTimTheoNangLuc(madv);
        model.addAttribute("listNhanVien",listNhanVien);
        return "admin/DS_nhanvien_de_them_cong_viec";
    }

    // hien ra bang ten dich vu, ten loai xe, ma nhan vien, ten nhan vien,
    @RequestMapping(value = "/DanhSachCongViec/ThemCongViec/DanhSachBangGia/{madg}/DanhSachNhanVien/{manv}",method = RequestMethod.GET)
    public String formDangKiCongViecChoNhanVien(@PathVariable("madg")int madg, @PathVariable("manv")String manv,
                                                Model model) throws SQLException {

        // tao ra cac doi tuong LoaiXe, DichVu,
        BANGGIA bg = BangGiaDAO.findBangGia(madg);
        DICHVU dv = DichVuDAO.findByID(bg.getMadv());
        LOAIXE lx = LoaiXeDAO.findByID(bg.getMalx());
        // dich vu
        model.addAttribute("dv",dv);
        // loai xe
        model.addAttribute("lx",lx);
        // bang gia
        model.addAttribute("bg",bg);

        // nhan vien
        NHANVIEN nv = NhanVienDAO.getNhanVienByID(manv);
        model.addAttribute("nv",nv);

        // truyen cac tham so
        // manv
        String maNV= "";
        model.addAttribute("manv",maNV);
        // ngay lam
        Timestamp ngaylam = Timestamp.from(Instant.now());
        model.addAttribute("ngaylam",ngaylam);

        return "admin/ThongTinCongViec";
    }

    // dang ki cong viec cho nhan vien
    @RequestMapping(value = "/DanhSachCongViec/ThemCongViec/DanhSachBangGia/{madg}/DanhSachNhanVien/{manv}",method = RequestMethod.POST)
    public String dangKiCongViecChoNhanVien(@PathVariable("madg")int madg, @PathVariable("manv")String manv) throws SQLException {

        // them vao chi tiet cong viec va cong viec cho nhan vien khi 1 cong viec duoc tao thanh cong
        try{
            CONGVIEC cv = new CONGVIEC();
            cv.setMadg(madg);
            cv.setManv(manv);
            cv.setNgaylam(Timestamp.from(Instant.now()));
            // don gia bi rong
            BANGGIA bg = BangGiaDAO.findBangGia(madg);
            cv.setTongtien(bg.getDongia());
            // THEM 1 CONG VIEC
            CongViecDAO.insert(cv);

            return "redirect:/admin/CongViec/DanhSachCongViec";
        }catch (Exception ex){
            ex.printStackTrace();
        }
       return "/admin/ThongTinCongViec";
    }

    @RequestMapping("/DanhSachCongViec/XoaCongViec/{ngaylam}_{manv}_{dongia}")
    public String xoaCongViec(@PathVariable("ngaylam")Timestamp ngaylam,
                              @PathVariable("manv")String manv, @PathVariable("dongia")int dongia) throws SQLException {

        // KHI XOA DI 1 CONG VIEC THI CAP NHAT LAI TONG DOANH THU CHO NHAN VIEN DO TRONG THANG

        int monthValue = ngaylam.toLocalDateTime().getMonthValue();
        int year = ngaylam.toLocalDateTime().getYear();


        LuongDAO.updateTongDoanhThu(manv,monthValue,year,dongia);
        CongViecDAO.delete(manv,ngaylam);


        return "redirect:/admin/CongViec/DanhSachCongViec";
    }
}
