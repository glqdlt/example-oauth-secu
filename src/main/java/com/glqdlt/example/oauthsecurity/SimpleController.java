package com.glqdlt.example.oauthsecurity;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Slf4j
@Controller
@RequestMapping("/")
public class SimpleController {

    @Autowired
    MemberRepo memberRepo;

    @Autowired
    RoleRepo roleRepo;

//    @PostMapping("/")
//    public String savemember(Member member){
//
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        member.setUserPw(bCryptPasswordEncoder.encode(member.getUserPw()));
//        member.setRole(roleRepo.findByRole("user"));
//        memberRepo.save(member);
//
//        return "redirect:/";
//    }

    @GetMapping("/")
    public String home(Principal principal,HttpServletRequest httpServletRequest){

        log.info("principal : {}",principal.toString());
        Object ss = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info(ss.toString());
        log.info("session id : {}",httpServletRequest.getSession().getId());

        return "/index.html";
    }

//    @GetMapping("/login")
//    public String logins(HttpServletRequest httpServletRequest){
//
//        log.info("serssion id :  {}",httpServletRequest.getSession().getId());
//
//        return "/login.html";
//    }

}
