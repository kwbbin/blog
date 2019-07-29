package com.kwbbin.manage.service;

import com.kwbbin.bean.Admin;
import com.kwbbin.manage.dao.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminLoginService {

    @Autowired
    AdminMapper dao;

    public Admin selectByNameAndPassword(Admin admin){
        return dao.selectByNameAndPassword(admin);
    }


}
