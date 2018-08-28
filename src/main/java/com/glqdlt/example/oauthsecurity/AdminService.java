package com.glqdlt.example.oauthsecurity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminService {

    public String callRole(){
        return "call";
    }

}
