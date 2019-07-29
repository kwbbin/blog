package com.kwbbin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginOutController {

    @RequestMapping("/LoginOut")
    public String loginOut(HttpServletRequest request, HttpServletResponse response){
        //删除session的user
        request.getSession().removeAttribute("user");

        //删除cookie
        Cookie newCookie1=new Cookie("loginUserNumber",null); //假如要删除名称为username的Cookie
        Cookie newCookie2=new Cookie("loginUserPassword",null);
        newCookie1.setMaxAge(0); //立即删除型
        newCookie1.setPath("/"); //项目所有目录均有效，这句很关键，否则不敢保证删除
        newCookie2.setMaxAge(0); //立即删除型
        newCookie2.setPath("/"); //项目所有目录均有效，这句很关键，否则不敢保证删除
        response.addCookie(newCookie1); //重新写入，将覆盖之前的
        response.addCookie(newCookie2); //重新写入，将覆盖之前的

        return "index";
    }
}
