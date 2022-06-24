package qlnv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping(value = "/welcome")
    public String login_phanquyen(){
        return "Login_phanquyen";
    }
}
