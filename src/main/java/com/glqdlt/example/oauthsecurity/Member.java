package com.glqdlt.example.oauthsecurity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@EqualsAndHashCode(of = "userId")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long seq;

    private String userId;

    private String userPw;

    private String email;

    private Date regDate;

    @OneToOne(targetEntity = Role.class)
    private Role role;

}
