package com.kwbbin.utilbean;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class GithubInfo {
    @Value("0f2cf4f6898f9da0123c")
    private String client_id;
    @Value("http://localhost:8080/github/callback")
    private String redirect_uri;
    private String scope;
    @Value("59364e14433474487dfd40ad291d3d60ff9e0647")
    private String client_secret;
    private String state;


}
