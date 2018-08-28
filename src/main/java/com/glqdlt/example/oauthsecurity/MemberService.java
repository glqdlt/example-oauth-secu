package com.glqdlt.example.oauthsecurity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Slf4j
public class MemberService implements UserDetailsService {

    @Autowired
    MemberRepo memberRepo;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Member member = memberRepo.findByUserId(s);
        UserDetails userDetails = User.withUsername(member.getUserId()).password(member.getUserPw()).roles(member.getRole().getRole()).build();
        return userDetails;
    }
}
