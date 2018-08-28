package com.glqdlt.example.oauthsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    MemberRepo memberRepo;

    @Autowired
    RoleRepo roleRepo;

    @PostMapping("/")
    public String savemember(Member member){

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        member.setUserPw(bCryptPasswordEncoder.encode(member.getUserPw()));
        member.setRole(roleRepo.findByName("user"));
        memberRepo.save(member);

        return "redirect:/";
    }


}
