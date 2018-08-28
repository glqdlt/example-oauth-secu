package com.glqdlt.example.oauthsecurity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepo extends JpaRepository<Member, Integer> {

    Member findByUserId(String id);

}
