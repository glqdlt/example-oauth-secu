package com.glqdlt.example.oauthsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
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
        adminRole.setRole("ADMIN");

        Role userRole = new Role();
        userRole.setRole("USER");

        roleRepo.save(Arrays.asList(adminRole,userRole));

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String pw = bCryptPasswordEncoder.encode("pw");

        Member member = new Member();
        member.setUserId("admin");
        member.setUserPw(pw);
        member.setEmail("test@test.com");
        member.setRegDate(new Date());

        member.setRole(adminRole);

        Member member2 = new Member();
        member2.setUserId("user");
        member2.setUserPw(pw);
        member2.setRole(userRole);
        member2.setRegDate(new Date());

        memberRepo.save(Arrays.asList(member,member2));



    }
}
