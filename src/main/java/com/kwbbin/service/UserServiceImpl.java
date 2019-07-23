package com.kwbbin.service;

import com.kwbbin.bean.User;
import com.kwbbin.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    UserDao dao;
    public void insertUser(User user){
        dao.insertUser(user);
    };


    public User selectUserById(int id){
        return dao.selectUserById(id);
    };
}
