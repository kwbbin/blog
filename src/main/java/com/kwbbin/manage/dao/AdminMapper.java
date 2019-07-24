package com.kwbbin.manage.dao;

import com.kwbbin.bean.Admin;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminMapper {
    Admin selectByNameAndPassword(Admin admin);
}