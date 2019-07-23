package com.kwbbin.dao;

import com.kwbbin.bean.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

//    @Insert("insert into user(id,name,email,password,register_time) values(#{id},#{name},#{email},#{password},#{register_time})")
    public void insertUser(User user);

//    @Select("select * from user where id=1")
    public User selectUserById(int id);
}
