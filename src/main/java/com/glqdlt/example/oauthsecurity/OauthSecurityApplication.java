package com.glqdlt.example.oauthsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.glqdlt.example.**")
@SpringBootApplication
public class OauthSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(OauthSecurityApplication.class, args);
    }
}
