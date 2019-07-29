package com.kwbbin.dao;

import com.kwbbin.bean.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    public void insertUser(User user);
    public User selectUserById(int id);
    public User selectUserByOpenId(long id);
    public User selectByNickNameAndPassword(User user);
}
