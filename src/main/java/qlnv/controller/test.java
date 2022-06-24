package qlnv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;


import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/test/")
public class test {

    // show form edit loai xe
    @RequestMapping(value = "test")
    public String showForm(Model model) throws SQLException {
        return "admin/DScongviec";
    }


}
