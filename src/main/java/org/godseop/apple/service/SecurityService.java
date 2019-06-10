package org.godseop.apple.service;

import lombok.extern.slf4j.Slf4j;
import org.godseop.apple.entity.Member;
import org.godseop.apple.exception.AppleException;
import org.godseop.apple.model.Error;
import org.godseop.apple.model.SecurityMember;
import org.godseop.apple.repository.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@Transactional
public class SecurityService implements UserDetailsService {

    private final MemberRepository memberRepository;

    public SecurityService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    public Member authenticate(String uid, String password) {
        Member member = memberRepository.getByUid(uid);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if (member == null) {
            throw new AppleException(Error.MEMBER_NOT_EXISTS);
        } else if (encoder.matches(password, member.getPassword())) {
            return member;
        } else {
            return null;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String uid) {
        Member member = memberRepository.getByUid(uid);

        if (member == null) {
            throw new AppleException(Error.MEMBER_NOT_EXISTS);
        }

        return new SecurityMember(member);
    }





}
