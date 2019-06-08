package org.godseop.apple.service;

import org.godseop.apple.entity.Member;
import org.godseop.apple.exception.AppleException;
import org.godseop.apple.model.Error;
import org.godseop.apple.repository.mapper.MemberMapper;
import org.godseop.apple.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MemberService {

    @Autowired
    @Qualifier("memberMapper")
    private MemberMapper memberMapper;
    
    @Autowired
    private MemberRepository memberRepository;

    public List<Member> getMemberListAll() {
        return memberMapper.selectMemberListAll();
    }
    
    public List<Member> getMemberListAllJpa() {
        return memberRepository.findAll();
    }

    public Member getMember(Integer id) {
        return memberRepository.getOne(id);
    }

    public void registerMember(Member member) {
        try {
            memberRepository.save(member);
        } catch(Exception e) {
            throw new AppleException(Error.DUPLICATE_USER_ID);
        }
    }
}
