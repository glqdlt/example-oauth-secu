package com.glqdlt.example.oauthsecurity;

import com.glqdlt.sessionlogger.SessionLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping("/")
@ControllerAdvice
public class SimpleController {

    @Autowired
    MemberRepo memberRepo;

    @Autowired
    RoleRepo roleRepo;


    @SessionLogger
    @GetMapping("/")
    public String home(HttpServletRequest httpServletRequest){

        return "/index";
    }

    @SessionLogger
    @GetMapping("/login")
    public String logins(HttpServletRequest httpServletRequest){


        return "/login";
    }

    @SessionLogger
    @GetMapping("/some")
    public String some(HttpServletRequest httpServletRequest){

        return "/some";
    }

    @SessionLogger
    @GetMapping("/logout")
    public String logout(HttpSession httpSession){
        httpSession.invalidate();
        return "redirect:/login";
    }

    @SessionLogger
    @ModelAttribute
    public void addSessionId(Model model, HttpSession httpSession){
        model.addAttribute("sessionId",httpSession.getId());
    }
}
