package org.godseop.apple.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private MemberMapper memberMapper;

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
        if (memberRepository.getByUid(member.getUid()) != null) {
            throw new AppleException(Error.DUPLICATE_MEMBER_UID);
        } else if (memberRepository.findByNickname(member.getNickname()) != null) {
            throw new AppleException(Error.DUPLICATE_MEMBER_NICKNAME);
        } else {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            member.setPassword(encoder.encode(member.getPassword()));

            MemberRole adminRole = new MemberRole("ROLE_ADMIN", member);
            MemberRole basicRole = new MemberRole("ROLE_BASIC", member);
            MemberRole authorRole = new MemberRole("ROLE_AUTUOR", member);
            Set<MemberRole> roleSet = new HashSet<>();
            roleSet.add(adminRole);
            roleSet.add(basicRole);
            roleSet.add(authorRole);
            member.setRoleSet(roleSet);

            memberRepository.save(member);
        }
    }

    @Transactional
    public void modifyMember(Member member) {
        // TODO DynamicUPDATE 할수 있는 방법좀... 어노테이션 안먹음
        Member existsMember = memberRepository.getOne(member.getId());

        if (existsMember == null)
            throw new AppleException(Error.MEMBER_NOT_EXISTS);

        existsMember.setNickname(member.getNickname());

        memberRepository.save(existsMember);
    }

    // mapper
    public List<Member> getMemberListAll() {
        return memberMapper.selectMemberListAll();
    }

}
