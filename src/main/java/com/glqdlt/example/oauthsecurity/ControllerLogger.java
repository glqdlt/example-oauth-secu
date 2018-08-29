package com.glqdlt.example.oauthsecurity;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Aspect
@Component
public class ControllerLogger {

    @Before("@annotation(SessionLogger)")
    public void writeLogging(JoinPoint joinPoint){

        for(Object arg :joinPoint.getArgs()){
            if(arg instanceof HttpServletRequest){
                HttpServletRequest httpServletRequest = (HttpServletRequest) arg;
                log.info("=========================================================================================");
                log.info("signature : {} ,method name : {} , httpServletRequest ID : {}",joinPoint.getSignature().toShortString(), joinPoint.getSignature().getName() ,httpServletRequest.getSession().getId());
                log.info("IP : {} .. {} ",httpServletRequest.getRemoteHost(),httpServletRequest.getRemoteAddr());
                log.info("=========================================================================================");
            }
        }
    }
}
