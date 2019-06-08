package org.godseop.apple;

import lombok.extern.slf4j.Slf4j;
import org.godseop.apple.entity.Member;
import org.godseop.apple.repository.MemberRepository;
import org.godseop.apple.service.PostServiceJooq;
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
    
    @Autowired
	MemberRepository memberRepository;
    
    @Autowired
    PostServiceJooq postService;


    @Test
    public void getAllMember() {
        List<Member> memberList = memberService.getMemberListAll();
        log.info("member : {}", memberList);
    }
    
    @Test
	public void jooqTest() {

		postService.test();
	}
    
    
	@Test
	public void getAllUserJpa() {

		Member member = new Member();
		member.setUid("ksh");
		member.setNickname("김성훈");
		member.setPassword("blahblah");
		member.setEmail("maifan@email.com");

		memberRepository.save(member);

		List<Member> memberList = memberRepository.findAll();
		log.info("members : {}", memberList);
	}

}
