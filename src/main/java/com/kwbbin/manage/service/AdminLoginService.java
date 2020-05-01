package com.kwbbin.manage.service;

import com.kwbbin.bean.Admin;
import com.kwbbin.bean.AdminExample;
import com.kwbbin.dao.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminLoginService {

    @Autowired
    AdminMapper adminMapper;



    public Admin selectByNameAndPassword(Admin admin){
        AdminExample adminExample = new AdminExample();
        adminExample.createCriteria().andNameEqualTo(admin.getName()).andPasswordEqualTo(admin.getPassword());
        return adminMapper.selectByExample(adminExample).get(0);
    }


}
