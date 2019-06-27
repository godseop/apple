package org.godseop.apple.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.godseop.apple.entity.Member;
import org.godseop.apple.exception.AppleException;
import org.godseop.apple.model.Error;
import org.godseop.apple.model.SecurityMember;
import org.godseop.apple.repository.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@RequiredArgsConstructor
public class SecurityService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
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
    public UserDetails loadUserByUsername(String uid) throws UsernameNotFoundException {
        Member member = memberRepository.getByUid(uid);

        if (member == null) {
            throw new UsernameNotFoundException("사용자가 없습니다");
        }
        return new SecurityMember(member);
    }

}
