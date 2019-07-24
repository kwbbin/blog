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

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    UserServiceImpl service;
    @Autowired
    GithubInfo gif;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/")
    public String DefultView(){
        return "index";
    }

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
            user.setOpenId(githubID);
            user.setOpenType("github");
            user.setNickname(LoginName);
            user.setAvatar(avatar_url);
            user.setOpenBio(bio);
            service.insertUser(user);
        }else{
            user=service.selectUserByOpenId(githubID);
        }

        req.getSession().setAttribute("user",user);

        return "redirect:/";
    }

}
