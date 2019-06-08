package org.godseop.apple.security;


import org.godseop.apple.entity.Member;
import org.godseop.apple.entity.MemberRole;
import org.godseop.apple.service.SecurityService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class MemberAuthenticationProvider implements AuthenticationProvider {

    private final SecurityService securityService;

    public MemberAuthenticationProvider(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        String uid = authentication.getName();
        String password = (String) authentication.getCredentials();

        Member member = securityService.authenticate(uid, password);
        if (member == null)
            throw new BadCredentialsException("Login Error !!");
        member.setPassword(null);

        Set<MemberRole> memberRoleList = member.getRoleSet();

        return new UsernamePasswordAuthenticationToken(member, null, memberRoleList);
    }

    @Override
    public boolean supports(Class authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}