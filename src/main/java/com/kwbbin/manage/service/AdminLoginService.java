package com.kwbbin.manage.service;

import com.kwbbin.bean.Admin;
import com.kwbbin.bean.AdminExample;
import com.kwbbin.dao.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminLoginService {

    @Autowired
    AdminMapper adminMapper;



    public Admin selectByNameAndPassword(Admin admin){
        AdminExample adminExample = new AdminExample();
        adminExample.createCriteria().andNameEqualTo(admin.getName()).andPasswordEqualTo(admin.getPassword());
        List<Admin> list= adminMapper.selectByExample(adminExample);
        if (list.size()>0)
        return list.get(0);
        else return null;
    }


}
