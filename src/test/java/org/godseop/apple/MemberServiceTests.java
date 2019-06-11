package org.godseop.apple;

import lombok.extern.slf4j.Slf4j;
import org.godseop.apple.entity.Member;
import org.godseop.apple.repository.MemberRepository;
import org.godseop.apple.service.PostService;
import org.godseop.apple.service.MemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class MemberServiceTests {

    @Autowired
    MemberService memberService;

    @Test
    public void getMemberList() {
        List<Member> memberList = memberService.getMemberList();

        memberList.stream().forEach(member -> log.info("member : {}", member));
    }

    @Test
    public void getMemberListAll() {
        // query by mybatis mapper
        List<Member> memberList = memberService.getMemberListAll();

        memberList.stream().forEach(member -> log.info("member : {}", member));
    }

    @Test
    public void getMember() {
        Member member = memberService.findMember("godseop");
        log.info("member : {}", member);
    }

    @Test
    public void registerMember() {
        Member member = new Member();
        member.setUid("test");
        member.setNickname("테스트");
        member.setPassword("1234");
        member.setEmail("test@email.com");

        memberService.registerMember(member);
    }

    @Test
    public void registerDuplicateUid() {
        Member member = new Member();
        member.setUid("aaa");
        member.setNickname("알파");
        member.setPassword("1234");
        member.setEmail("aaa@email.com");

        memberService.registerMember(member);
    }

    @Test
    public void registerDuplicateNickname() {
        Member member = new Member();
        member.setUid("aaa");
        member.setNickname("알파");
        member.setPassword("1234");
        member.setEmail("aaa@email.com");

        memberService.registerMember(member);
    }

}
