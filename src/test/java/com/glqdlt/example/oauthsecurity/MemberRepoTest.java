package com.glqdlt.example.oauthsecurity;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;


@Slf4j
@DataJpaTest
@RunWith(SpringRunner.class)
public class MemberRepoTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    MemberRepo memberRepo;

    @Autowired
    RoleRepo roleRepo;

    @Before
    public void setUp() {

        Role adminRole = new Role();
        adminRole.setRole("admin");

        roleRepo.save(adminRole);

        Member member = new Member();
        member.setUserId("testId");
        member.setUserPw("testPw");
        member.setEmail("test@test.com");
        member.setRegDate(new Date());

        member.setRole(adminRole);

        memberRepo.save(member);
    }

    @Test
    public void constTest() {

    }

    @Test
    public void testIdIsRoleAdmin() {
        Assert.assertEquals("testId",memberRepo.findAll().get(0).getUserId());
    }

    @Test
    public void roleAdminIsNotNull() {

        Assert.assertEquals("admin",roleRepo.findByRole("admin").getRole());
    }
}