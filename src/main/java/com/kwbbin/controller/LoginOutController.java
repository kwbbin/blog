package com.kwbbin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginOutController {

    @RequestMapping("/LoginOut")
    public String loginOut(HttpServletRequest request){
        request.getSession().removeAttribute("user");
        return "index";
    }
}
