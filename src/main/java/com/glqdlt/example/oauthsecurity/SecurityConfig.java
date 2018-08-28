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

// oauth http://projects.spring.io/spring-security-oauth/docs/oauth2.html
// spring security architecture :  https://spring.io/guides/topicals/spring-security-architecture/
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    LogoutHandler logoutHandler;

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
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.NEVER);

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
                .deleteCookies("JSESSIONID")
                .logoutUrl("/logout")
//                .logoutSuccessUrl("/login")
                .logoutSuccessHandler(logoutHandler)
                .permitAll()
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


}
