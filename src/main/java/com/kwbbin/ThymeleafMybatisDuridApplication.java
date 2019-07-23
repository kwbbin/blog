package com.kwbbin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.kwbbin.*")
public class ThymeleafMybatisDuridApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThymeleafMybatisDuridApplication.class, args);
    }

}
