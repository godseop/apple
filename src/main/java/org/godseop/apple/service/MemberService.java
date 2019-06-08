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

    private MemberMapper memberMapper;

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Autowired
    @Qualifier("memberMapper")
    public void setMemberMapper(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }


    public List<Member> getMemberList() {
        return memberRepository.findAll();
    }

    public Member getMember(String uid) {
        return memberRepository.findByUid(uid);
    }

    public void registerMember(Member member) {
        if (memberRepository.findByUid(member.getUid()) != null) {
            throw new AppleException(Error.DUPLICATE_MEMBER_UID);
        } else if (memberRepository.findByNickname(member.getNickname()) != null) {
            throw new AppleException(Error.DUPLICATE_MEMBER_NICKNAME);
        } else {
            memberRepository.save(member);
        }
    }

    public void modifyMember(Member member) {
        if (!memberRepository.existsById(member.getId())) {
            throw new AppleException(Error.MEMBER_NOT_EXISTS);
        } else {
            memberRepository.save(member);
        }
    }

    // mapper
    public List<Member> getMemberListAll() {
        return memberMapper.selectMemberListAll();
    }

}
