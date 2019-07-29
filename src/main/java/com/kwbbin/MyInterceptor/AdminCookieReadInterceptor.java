package com.kwbbin.MyInterceptor;


import com.kwbbin.bean.Admin;
import com.kwbbin.manage.service.AdminLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class AdminCookieReadInterceptor implements HandlerInterceptor {
    @Autowired
    AdminLoginService service;

    //这个方法是在访问接口之前执行的，我们只需要在这里写验证登陆状态的业务逻辑，就可以在用户调用指定接口之前验证登陆状态了
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //每一个项目对于登陆的实现逻辑都有所区别，我这里使用最简单的Session提取User来验证登陆。
        HttpSession session = request.getSession();

        //这里的User是登陆时放入session的
        Admin admin = (Admin) session.getAttribute("admin");
        //如果session中没有admin，表示没登陆

        //查看是否保存cookie
        if(admin==null){
            String userNumber=null;
            String password=null;
            Cookie[] cookies = request.getCookies();
            if (null!=cookies){
                for(Cookie cookie : cookies){
                    if("loginAdminNumber".equals(cookie.getName())){
                        userNumber=cookie.getValue();
                    }
                    if("loginAdminPassword".equals(cookie.getName())){
                        password=cookie.getValue();
                    }
                }
                if(userNumber!=""||password!=""){
                    Admin ad=new Admin(userNumber,password);
                    admin=service.selectByNameAndPassword(ad);
                    if(admin!=null){
                        request.getSession().setAttribute("admin", admin);
                    }
                }
            }
        }

        return true;    //如果session里有user，表示该用户已经登陆，放行，用户即可继续调用自己需要的接口
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }
}
