package com.kwbbin.bean;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Admin {
    private Integer id;

    private String name;

    private String password;

    private String phone;

    private String nickName;

    public Admin(String nickName, String password) {
        this.nickName = nickName;
        this.password = password;
    }
}