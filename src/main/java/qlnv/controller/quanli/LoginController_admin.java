package qlnv.controller.quanli;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import qlnv.dao.TaiKhoanDAO;
import qlnv.entity.TAIKHOAN;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@Controller
public class LoginController_admin {

    @Autowired
    HttpSession httpSession;

    // form dang nhap admin
    @RequestMapping(value = "/admin/login", method = RequestMethod.GET)
    public String showLoginForm(ModelMap modelMap){

        TAIKHOAN tk = new TAIKHOAN();
        modelMap.addAttribute("admin",tk);
        return "admin/Login";
    }

    // xu li dang nhap admin
    @RequestMapping(value = "/admin/login", method = RequestMethod.POST)
    public String checkLoginForm(ModelMap modelMap, @ModelAttribute("admin") TAIKHOAN taiKhoan,
                                 HttpServletRequest request, HttpServletResponse response) throws SQLException {
        // validate tai khoan
        // neu ten dang nhap hoac mat khau bi sai thi check_tendangnhap_mk = false;
        // tendangnhap.isEmpty va tendangnhap va matkhau sai la 2 truong hop
        //


        boolean check_validation = true;

        // neu ten dang nhap trong !
        if(taiKhoan.getTendangnhap().isEmpty()){
            modelMap.addAttribute("message_tendangnhap","Tài khoản không được bỏ trống !");
            check_validation=false;
            // nguoc lai neu tai khoan nhap dung ten dang nhap thi check mat khau
        }else{

            taiKhoan.setTendangnhap(taiKhoan.getTendangnhap().toUpperCase());
            boolean check_tendangnhap = false;
            boolean check_matkhau = false;
            for(int i=0;i<TaiKhoanDAO.getList().size();i++){
                if(taiKhoan.getTendangnhap().equals(TaiKhoanDAO.getList().get(i).getTendangnhap())){
                    // nhap dung mat khau
                    if(taiKhoan.getMatkhau().equals(TaiKhoanDAO.getList().get(i).getMatkhau())){
                        taiKhoan.setQuyen(TaiKhoanDAO.getList().get(i).isQuyen());

                        // quyen admin
                        if(taiKhoan.isQuyen()==true){
                            HttpSession session = request.getSession();
                            session.setAttribute("admin",taiKhoan);
                            check_tendangnhap=true;
                            check_matkhau=true;
                            // dang nhap thanh cong !

                            return "redirect:/admin/CongViec/DanhSachCongViec";
                        }
                    }
                }
            }
            // neu ten dang nhap va mat khau KHONG dung
            if(check_matkhau==false){
                modelMap.addAttribute("message_taikhoan","Tên đăng nhập hoặc mật khẩu không đúng !");
            }
        }
        // neu false thi van o lai login
        // neu dang nhap that bai
        return "admin/Login";
    }

    // dang xuat admin
    @RequestMapping(value = "admin/logout")
    public String logout_admin(HttpSession httpSession){

        httpSession.removeAttribute("admin");
        return "Login_phanquyen";
    }

    // doi mat khau admin
    //=======================================================================
    //=========================NHAN VIEN=====================================
    //=======================================================================

    // form dang nhap nhanvien
    @RequestMapping(value = "nhanvien/login", method = RequestMethod.GET)
    public String showLoginForm_2(ModelMap modelMap){


        modelMap.addAttribute("nhanvien",new TAIKHOAN());
        return "nhanvien/Login";
    }

    // check login : nhan vien
    @RequestMapping(value = "nhanvien/login", method = RequestMethod.POST)
    public String checkLoginForm2(ModelMap modelMap, @ModelAttribute("nhanvien") TAIKHOAN taiKhoan,
                                 HttpServletRequest request, HttpServletResponse response) throws SQLException {
        // validate tai khoan
        // neu ten dang nhap hoac mat khau bi sai thi check_tendangnhap_mk = false;
        // tendangnhap.isEmpty va tendangnhap va matkhau sai la 2 truong hop
        //

        System.out.println("ten dang nhap: " + taiKhoan.getTendangnhap());
        boolean check_validation = true;

        // neu ten dang nhap trong !
        if(taiKhoan.getTendangnhap().isEmpty()){
            modelMap.addAttribute("message_tendangnhap","Tài khoản không được bỏ trống !");
            check_validation=false;
            // nguoc lai neu tai khoan nhap dung ten dang nhap thi check mat khau
        }else{

            taiKhoan.setTendangnhap(taiKhoan.getTendangnhap().toUpperCase());
            boolean check_tendangnhap = false;
            boolean check_matkhau = false;
            for(int i=0;i<TaiKhoanDAO.getList().size();i++){
                if(taiKhoan.getTendangnhap().equals(TaiKhoanDAO.getList().get(i).getTendangnhap())){
                    // nhap dung mat khau
                    if(taiKhoan.getMatkhau().equals(TaiKhoanDAO.getList().get(i).getMatkhau())){
                        taiKhoan.setQuyen(TaiKhoanDAO.getList().get(i).isQuyen());
                        // quyen nhan vien
                        if(taiKhoan.isQuyen()==false){
                            HttpSession session = request.getSession();
                            session.setAttribute("nhanvien",taiKhoan);
                            check_tendangnhap=true;
                            check_matkhau=true;
                            // dang nhap thanh cong !
                            System.out.println("dang nhap thanh cong !");
                            return "redirect:/nhanvien/CongViec/DanhSachCongViec";
                        }
                    }
                }
            }

            // neu ten dang nhap va mat khau KHONG dung
            if(check_matkhau==false){
                modelMap.addAttribute("message_taikhoan","Tên đăng nhập hoặc mật khẩu không đúng !");
            }
        }
        // neu false thi van o lai login
        // neu dang nhap that bai
        return "nhanvien/Login";
    }

    // dang xuat nhanvien
    @RequestMapping(value = "nhanvien/logout")
    public String logout_nhanvien(HttpSession httpSession){

        httpSession.removeAttribute("nhanvien");
        return "Login_phanquyen";
    }
}
