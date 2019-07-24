package com.kwbbin.manage.controller;

import com.kwbbin.bean.Admin;
import com.kwbbin.manage.service.AdminLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/manage")
public class ManageLoginController {
    @Autowired
    AdminLoginService service;

    @RequestMapping(value = {"/",""})
    public String defaultView(){
        return "manage/index";
    }

    @RequestMapping("/login")
    public String manageLogin(String userName, String userPwd, HttpServletRequest request){
        Admin admin=service.selectByNameAndPassword(new Admin(userName,userPwd));
        if(admin!=null){
            request.getSession().setAttribute("admin",admin);
            return "manage/index-manage";
        }
        return "manage/index";
    }
}
