package com.glqdlt.example.oauthsecurity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Integer> {

    Role findByRole(String name);
}
