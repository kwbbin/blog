package com.kwbbin.controller;

import com.kwbbin.bean.User;
import com.kwbbin.service.UserServiceImpl;
import com.kwbbin.utilbean.GithubInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    UserServiceImpl service;
    @Autowired
    GithubInfo gif;

    @Autowired
    private RestTemplate restTemplate;



    @RequestMapping("/github/login")
    public String githubLogin(){
        String githubState = "adgasgdsdhgi";
        return "redirect:https://github.com/login/oauth/authorize?client_id=0f2cf4f6898f9da0123c&redirect_uri=http://localhost:8080/github/callback&scope=user&state=1";
    }

    @RequestMapping("/github/callback")
    public String githubLoginCallback(String code, String state, Model model, HttpServletRequest req){

        String url="https://github.com/login/oauth/access_token?code="+code+"&client_id="+gif.getClient_id()+"&client_secret="+gif.getClient_secret();
        String url2="https://api.github.com/user?access_token=";

        ResponseEntity<Map> results=restTemplate.exchange(url, HttpMethod.GET, null, Map.class);
        url2=url2+results.getBody().get("access_token");

        ResponseEntity<Map> results2=restTemplate.exchange(url2, HttpMethod.GET, null, Map.class);

        String LoginName=null;
        long githubID=-1;
        String  avatar_url=null;
        String bio=null;
        try {
            LoginName=results2.getBody().get("login").toString();
            githubID=Integer.parseInt(results2.getBody().get("id").toString());
            avatar_url=results2.getBody().get("avatar_url").toString();
            bio=results2.getBody().get("bio").toString();
        }catch (Exception e){

        }
        User user=null;

        if(service.selectUserByOpenId(githubID)==null){
            user=new User();
            user.setOpenid(githubID+"");
            user.setOpentype("github");
            user.setNickname(LoginName);
            user.setAvatar(avatar_url);
            user.setOpenbio(bio);
            service.insertUser(user);
        }else{
            user=service.selectUserByOpenId(githubID);
        }

        req.getSession().setAttribute("user",user);

        return "redirect:/";
    }


    @RequestMapping("/login")
    public String dataBaseLogin(String userName, String password, HttpServletRequest request, HttpServletResponse response){
        //判断session是否存在user
        User user=(User)request.getSession().getAttribute("user");
        if(user==null){
            User u = new User();
            u.setName(userName);
            u.setPassword(password);
            user=service.selectByNameAndPassword(u);
        }

        if(user!=null){
            request.getSession().setAttribute("user",user);

            //判断cookie是否存在
            Cookie[] cookies=request.getCookies();
            String userC=null;
            String passwordC=null;
            for(Cookie cookie : cookies){
                //遍历查看用户名和密码是否存在（可能存在其他的东西，不能只判断cookies是否为null)
                if("loginUserNumber".equals(cookie.getName())){
                    userC=cookie.getValue();
                }
                if("loginUserPassword".equals(cookie.getName())){
                    passwordC=cookie.getValue();
                }
            }
            //不存在则保存
            if((userC==""&&passwordC=="")||(userC==null&&passwordC==null)){
                Cookie cookie1 = new Cookie("loginUserNumber", userName);
                cookie1.setMaxAge(24 * 3600);
                response.addCookie(cookie1);
                Cookie cookie2 = new Cookie("loginUserPassword", password);
                cookie2.setMaxAge(24 * 3600);
                response.addCookie(cookie2);
            }
        }

        return "redirect:/";
    }

}
