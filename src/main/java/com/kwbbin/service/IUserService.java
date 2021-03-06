package com.kwbbin.service;

import com.kwbbin.bean.User;


public interface IUserService {
    public void insertUser(User user);
    public User selectUserById(int id);
    public User selectUserByOpenId(long id);

    public User selectByNameAndPassword(User user);
}
