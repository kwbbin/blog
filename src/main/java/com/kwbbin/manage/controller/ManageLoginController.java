package com.kwbbin.manage.controller;

import com.kwbbin.bean.Admin;
import com.kwbbin.manage.service.AdminLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/manage")
public class ManageLoginController {
    @Autowired
    AdminLoginService service;

    @RequestMapping(value = {"/",""})
    public String defaultView(HttpServletRequest request){
        Admin admin=(Admin)request.getSession().getAttribute("admin");
        if(admin!=null){
            return "/manage/main_box";
        }
        return "/manage/index";
    }

    @RequestMapping("/login")
    public String manageLogin(String userName, String userPwd, HttpServletResponse response, HttpServletRequest request){
        Admin admin=(Admin)request.getSession().getAttribute("admin");
        if(admin==null){
            Admin ad = new Admin();
            ad.setName(userName);
            ad.setPassword(userPwd);
            admin=service.selectByNameAndPassword(ad);
        }

        if(admin!=null){
            request.getSession().setAttribute("admin",admin);

            //判断cookie是否存在
            Cookie[] cookies=request.getCookies();
            String userNumber=null;
            String password=null;
            for(Cookie cookie : cookies){
                if("loginAdminNumber".equals(cookie.getName())){
                    userNumber=cookie.getValue();
                }
                if("loginAdminPassword".equals(cookie.getName())){
                    password=cookie.getValue();
                }
            }
            //不存在则保存
            if((userNumber==""&&password=="")||(userNumber==null&&password==null)){
                Cookie cookie1 = new Cookie("loginAdminNumber", userName);
                cookie1.setMaxAge(24 * 3600);
                response.addCookie(cookie1);
                Cookie cookie2 = new Cookie("loginAdminPassword", userPwd);
                cookie2.setMaxAge(24 * 3600);
                response.addCookie(cookie2);
            }

            return "/manage/main_box";
        }

        return "/manage/index";
    }

    @RequestMapping("/LoginOut")
    public String SesstionRemove(HttpServletRequest request,HttpServletResponse response){
        //删除session的user
        request.getSession().removeAttribute("admin");

        //删除cookie
        Cookie newCookie1=new Cookie("loginAdminNumber",null); //假如要删除名称为username的Cookie
        Cookie newCookie2=new Cookie("loginAdminPassword",null);
        newCookie1.setMaxAge(0); //立即删除型
        newCookie1.setPath("/manage"); //项目所有目录均有效，这句很关键，否则不敢保证删除
        newCookie2.setMaxAge(0); //立即删除型
        newCookie2.setPath("/manage"); //项目所有目录均有效，这句很关键，否则不敢保证删除
        response.addCookie(newCookie1); //重新写入，将覆盖之前的
        response.addCookie(newCookie2); //重新写入，将覆盖之前的

        return "/manage/index";
    }

}
