package com.kwbbin.controller;

import com.kwbbin.bean.User;
import com.kwbbin.dao.UserDao;
import com.kwbbin.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserTestController {
    @Autowired
    UserServiceImpl service;

    @Autowired
    UserDao dao;

    @RequestMapping("/userTest")
    public String insertUser(){
        User user=new User();
        user.setName("kwbbin");
        service.insertUser(user);
        return "index";
    }

    @RequestMapping("/getuser")
    @ResponseBody
    public User getUser(){

        return service.selectUserById(1);
//        return dao.selectUserById(1);
    }
}
