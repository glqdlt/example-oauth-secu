package com.glqdlt.example.oauthsecurity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Configuration
public class LogoutHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        log.info("logout auth : {}",authentication);
        log.info("logout session id: {}",httpServletRequest.getSession().getId());

//        https://stackoverflow.com/questions/28774049/jsp-session-invalidate-vs-request-logout
        httpServletRequest.logout();
        httpServletRequest.getSession().invalidate();

        httpServletResponse.sendRedirect("/login");

    }
}
