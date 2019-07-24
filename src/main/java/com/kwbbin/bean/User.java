package com.kwbbin.bean;


import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class User {
    //主键
    private int id;
    //姓名
    private String name;
    //邮箱
    private String email;
    //第三方登录类别
    private String openType;
    //第三方登录id
    private long openId;
    //密码
    private String Password;
    //加入时间
    private String register_time;
    //昵称
    private String nickname;
    //头像
    private String avatar;
    //简介
    private String openBio;
    //过期时间
    private long expiredTime;

    private String accessToken;


}
