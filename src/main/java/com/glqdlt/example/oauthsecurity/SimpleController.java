package com.glqdlt.example.oauthsecurity;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

@Slf4j
@Controller
@RequestMapping("/")
@ControllerAdvice
public class SimpleController {

    @Autowired
    MemberRepo memberRepo;

    @Autowired
    RoleRepo roleRepo;

    @GetMapping("/")
    public String home(){

        return "/index";
    }

    @GetMapping("/login")
    public String logins(HttpServletRequest httpServletRequest, HttpSession httpSession){


        return "/login";
    }


    @GetMapping("/some")
    public String some(Principal principal,HttpServletRequest httpServletRequest){

        return "/some";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession){
        httpSession.invalidate();
        return "redirect:/login";
    }

    @ModelAttribute
    public void addSessionId(Model model, HttpSession httpSession){
        model.addAttribute("sessionId",httpSession.getId());
    }
}
