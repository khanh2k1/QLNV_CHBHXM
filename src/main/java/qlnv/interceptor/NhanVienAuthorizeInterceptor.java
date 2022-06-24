package qlnv.interceptor;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import qlnv.entity.TAIKHOAN;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class NhanVienAuthorizeInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("truoc khi vao controller");
        // session neu co tai khoan no se luu den khi logout, nho vay no giup cho chung ta di chuyen qua cac url ma da chan
        HttpSession session = request.getSession();

        // neu dang nhap thanh cong
        if(session.getAttribute("nhanvien") != null){
            // return true giup cho viec di chuyen qua cac url
            return true;
        }
        // neu dang nhap that bai
        else response.sendRedirect(request.getContextPath()+"/welcome");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("sau khi vao controller");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("sau khi view dc show");
    }
}
