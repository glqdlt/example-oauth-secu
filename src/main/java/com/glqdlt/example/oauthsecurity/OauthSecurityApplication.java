package com.glqdlt.example.oauthsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.Date;

@ComponentScan(basePackages = "com.glqdlt.example.**")
@SpringBootApplication
public class OauthSecurityApplication  implements CommandLineRunner {

    @Autowired
    MemberRepo memberRepo;

    @Autowired
    RoleRepo roleRepo;

    public static void main(String[] args) {
        SpringApplication.run(OauthSecurityApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {


        Role adminRole = new Role();
        adminRole.setName("admin");

        roleRepo.save(adminRole);

        Member member = new Member();
        member.setUserId("testId");
        member.setUserPw("testPw");
        member.setEmail("test@test.com");
        member.setRegDate(new Date());

        member.setRole(adminRole);

        memberRepo.save(member);



    }
}
