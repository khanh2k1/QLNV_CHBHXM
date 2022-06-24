package qlnv.controller.quanli;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import qlnv.dao.*;
import qlnv.entity.BANGGIA;
import qlnv.entity.CT_CONGVIEC;
import qlnv.entity.DICHVU;
import qlnv.entity.LOAIXE;
import qlnv.entityCustom.BANGGIA_custom;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/BangGia/")

public class BangGiaController_admin {

    @ModelAttribute("listTrangThai")
    public List<String> getListTrangThai() {
        List<String> list = new ArrayList<>();
        list.add("Đang dùng");
        list.add("Ngưng");
        return list;
    }

    // form tra cuu bang gia
    @RequestMapping(value = "DanhSachBangGia", method = RequestMethod.GET)
    public String showDanhSachBangGia(ModelMap modelMap) throws SQLException {

        List<BANGGIA_custom> listBangGia_custom = BangGia_customDAO.getList();
        modelMap.addAttribute("listBangGia_custom", listBangGia_custom);
        return "admin/DSbanggia";
    }

    // form edit bang gia
    @RequestMapping(value = "DanhSachBangGia/editBangGia/{madg}", method = RequestMethod.GET)
    public String formEditBangGia(Model model,
                                  @PathVariable("madg") int madg) throws SQLException {

        BANGGIA_custom bg_custom = BangGia_customDAO.getBangGia_by_madg(madg);
        model.addAttribute("bg_custom",bg_custom);
        return "admin/ThongTinBangGia";
    }

    // edit bang gia
    @RequestMapping(value = "DanhSachBangGia/editBangGia/{madg}", method = RequestMethod.POST)
    public String EditBangGia(Model model,
                              @PathVariable("madg") int madg,
                              @ModelAttribute("bg_custom")BANGGIA_custom bg_custom) throws SQLException {

        boolean check = true;
        if(bg_custom.getTrangthai().equals("Ngưng")){
            if(BangGiaDAO.check_madg_banggia_congviec(madg)==true){
                check=false;
                model.addAttribute("message_trangthai", "Bảng giá chưa thể xóa !");
                bg_custom.setTrangthai("Đang dùng");
            }
        }
        String price_regex = "[0-9]";
        // validate
        if(bg_custom.getDongia().isEmpty()){
            check=false;
            model.addAttribute("message_dongia", "Đơn giá không hợp lệ !");
        }else{
            int dongia_int = Integer.parseInt(bg_custom.getDongia());
            if(dongia_int < 1000 || dongia_int > 1000000000){
                check=false;
                model.addAttribute("message_dongia", "Đơn giá không hợp lệ !");
            }
        }

        // neu that bai thi bg_custom tu method post se dua modelAttribute vao lai view admin/ThongTinDonGia
        if(check==false){
            return "admin/ThongTinBangGia";
        }else {
            model.addAttribute("message_dongia","");
            BANGGIA bg = BangGiaDAO.findBangGia(bg_custom.getMadg());

            bg.setTrangthai(bg_custom.getTrangthai());
            bg.setDongia(Integer.parseInt(bg_custom.getDongia()));

            System.out.println("ma don gia:" + bg.getMadg());
            System.out.println("Trang thai: " + bg.getTrangthai());
            System.out.println("don gia: " + bg.getDongia());


            if(BangGiaDAO.update(bg)>0) System.out.println("Da update !");

            else System.out.println("chua update !");
            model.addAttribute("message_trangthai","Lưu thành công !");
            return "admin/ThongTinBangGia";
        }
    }

    // form them bang gia
    @RequestMapping(value = "/DanhSachBangGia/ThemBangGia", method = RequestMethod.GET)
    public String formThemBangGia(Model model)throws SQLException  {

        // list ten loai xe
        List<LOAIXE> listLoaiXe = LoaiXeDAO.getList();
        // list dich vu
        List<DICHVU> listDichVu = DichVuDAO.getList();
        // list bang gia
        BANGGIA bg = new BANGGIA();

        model.addAttribute("listLoaiXe", listLoaiXe);
        model.addAttribute("listDichVu", listDichVu);
        model.addAttribute("bg",bg);

        return "admin/ThemBangGia";
    }

    // them bang gia
    @RequestMapping(value = "/DanhSachBangGia/ThemBangGia", method = RequestMethod.POST)
    public String themBangGia(Model model, @ModelAttribute("bg") BANGGIA bg) throws SQLException {


        System.out.println("ma dich vu: " + bg.getMadv());
        System.out.println("ma loai xe: " + bg.getMalx());
        // validate
        boolean check_dongia = true;

        if(bg.getDongia()<1000 || bg.getDongia()>999999999){
            model.addAttribute("message_dongia","Đơn giá không hợp lệ !");
            check_dongia = false;
        }

        // check có bị trùng bảng giá

        boolean check_trung_banggia = false;

        List<BANGGIA> listBangGia = BangGiaDAO.getList();

        for(int i=0;i< listBangGia.size();i++){
            if(listBangGia.get(i).getMalx()==bg.getMalx()
                    && listBangGia.get(i).getMadv()==bg.getMadv()){
                check_trung_banggia = true;
                model.addAttribute("message_banggia","Bảng giá đã có !");
                break;
            }
        }

        // neu thanh cong
        if(check_trung_banggia==false && check_dongia == true) {
            model.addAttribute("check_trung_banggia", check_trung_banggia);
            model.addAttribute("message_banggia","Thành công !");

            // moi lan insert phai sap xep lai thu tu

            BangGiaDAO.insert(bg);
            // them chi tiet cv

            // do bangr giá không sắp xếp theo khóa chính nên ta phải tự sắp xếp
            int max_madg = -1;
            for(int i=0;i<BangGiaDAO.getList().size();i++){
                if(max_madg < BangGiaDAO.getList().get(i).getMadg()) {
                    max_madg = BangGiaDAO.getList().get(i).getMadg();
                }
            }
            System.out.println("ma bang gia vua dc them vao : " + max_madg);
            List<LOAIXE> listLoaiXe = LoaiXeDAO.getList();
            // list dich vu
            List<DICHVU> listDichVu = DichVuDAO.getList();
            // list bang gia
            bg = new BANGGIA();

            model.addAttribute("listLoaiXe", listLoaiXe);
            model.addAttribute("listDichVu", listDichVu);


            // them vao ct_congviec
            boolean check_madg_ct_congviec= false;
            for(int i=0;i< CT_CongViecDAO.getList().size();i++){
                if(CT_CongViecDAO.getList().get(i).getMadg()==max_madg){
                    check_madg_ct_congviec = true;
                    break;
                }
            }

            // them vao ct_Cv
            if(check_madg_ct_congviec == false){
                CT_CONGVIEC ct_congviec = new CT_CONGVIEC();
                ct_congviec.setMadg(max_madg);
                ct_congviec.setSoluong(0);

                CT_CongViecDAO.insert(ct_congviec);
            }

            return "admin/ThemBangGia";
        }

        // neu that bai
        else{
            model.addAttribute("check_trung_banggia", check_trung_banggia);
            // neu that bai
            // list ten loai xe
            List<LOAIXE> listLoaiXe = LoaiXeDAO.getList();
            // list dich vu
            List<DICHVU> listDichVu = DichVuDAO.getList();
            // list bang gia
            bg = new BANGGIA();

            model.addAttribute("listLoaiXe", listLoaiXe);
            model.addAttribute("listDichVu", listDichVu);
            return "admin/ThemBangGia";
        }
    }

}
