package com.glqdlt.example.oauthsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.session.HttpSessionEventPublisher;

// oauth http://projects.spring.io/spring-security-oauth/docs/oauth2.html
// spring security architecture :  https://spring.io/guides/topicals/spring-security-architecture/
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    LogoutHandler logoutHandler;

    @Autowired
    DeniedHandler deniedHandler;

    @Override
    public void configure(WebSecurity web) throws Exception 	{
        web.ignoring().antMatchers("/css/**", "/script/**", "image/**", "/fonts/**", "lib/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        스프링 시큐리티와 서블릿 세션간의 매커니즘 차이
//        https://www.baeldung.com/spring-security-session
//        https://docs.spring.io/spring-security/site/docs/4.2.7.RELEASE/reference/htmlsingle/
//        로그인 전 세션과 로그인 후 세션이 다른 것은 스프링 시큐리티의 세션 고정 공격 방지를 위한 설정의 default가 true이기 때문이다.
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .sessionManagement()
                .maximumSessions(1);

//        아래 옵션을 보면 none으로 하면 세션 고정 방어를 위한 세션 변경을 하지 않는다.
        http.sessionManagement()
                .sessionFixation().none();
//                .sessionFixation().newSession();
//                .sessionFixation().changeSessionId()
//                .sessionFixation().none();

        http
                .authorizeRequests()
                .antMatchers("/","/some").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
//                .deleteCookies("JSESSIONID")
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
//                .logoutSuccessHandler(logoutHandler)
//                .permitAll()
                .and()
//                .exceptionHandling().accessDeniedPage("/denied")
//                .and()
                .exceptionHandling().accessDeniedHandler(deniedHandler)
                .and()
                .csrf()
                .disable();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return new MemberService();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public AuthenticationProvider authenticationProvider(){
//        return new AuthProvider();
//    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher(){
        HttpSessionEventPublisher httpSessionEventPublisher = new HttpSessionEventPublisher();
        return httpSessionEventPublisher;
    }


}
