package com.kwbbin.bean;


import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class User {
    private int id;
    private String name;
    private String email;
    private String Password;
    private String register_time;


}
