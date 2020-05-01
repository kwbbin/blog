package com.kwbbin.service;

import com.kwbbin.bean.User;
import com.kwbbin.bean.UserExample;
import com.kwbbin.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    UserMapper userMapper;

    public void insertUser(User user){
        userMapper.insert(user);
    };


    public User selectUserById(int id){
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public User selectUserByOpenId(long id) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andOpenidEqualTo(id+"");
        return userMapper.selectByExample(userExample).get(0);
    }

    @Override
    public User selectByNameAndPassword(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andNameEqualTo(user.getName()).andPasswordEqualTo(user.getPassword());
        return userMapper.selectByExample(userExample).get(0);
    }

    ;
}
