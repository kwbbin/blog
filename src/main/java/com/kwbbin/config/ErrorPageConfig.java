package com.kwbbin.config;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
public class ErrorPageConfig implements ErrorPageRegistrar {

    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/templates/error/404.html");
        ErrorPage error400Page=new ErrorPage(HttpStatus.BAD_REQUEST,"/templates/error/400.html");
        ErrorPage error500Page=new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR,"/templates/error/500.html");
        registry.addErrorPages(error404Page,error400Page,error500Page);
    }
}