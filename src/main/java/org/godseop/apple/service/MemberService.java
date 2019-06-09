package org.godseop.apple.service;

import org.godseop.apple.entity.Member;
import org.godseop.apple.entity.MemberRole;
import org.godseop.apple.exception.AppleException;
import org.godseop.apple.model.Error;
import org.godseop.apple.repository.mapper.MemberMapper;
import org.godseop.apple.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
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

    @Transactional
    public void registerMember(Member member) {
        if (memberRepository.findByUid(member.getUid()) != null) {
            throw new AppleException(Error.DUPLICATE_MEMBER_UID);
        } else if (memberRepository.findByNickname(member.getNickname()) != null) {
            throw new AppleException(Error.DUPLICATE_MEMBER_NICKNAME);
        } else {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            member.setPassword(encoder.encode(member.getPassword()));

            //고아객체 삭제(delete일어남)
            //member.getRoleSet().clear();

            MemberRole memberRole = new MemberRole();
            memberRole.setMember(member);
            memberRole.setRoleName("ROLE_BASIC");
            member.setRoleSet(Set.of(memberRole));

            memberRepository.save(member);
        }
    }

    @Transactional
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
