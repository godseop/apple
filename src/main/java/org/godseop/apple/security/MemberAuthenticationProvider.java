package org.godseop.apple.security;


import lombok.extern.slf4j.Slf4j;
import org.godseop.apple.entity.mysql.Member;
import org.godseop.apple.exception.AppleException;
import org.godseop.apple.model.Error;
import org.godseop.apple.service.SecurityService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MemberAuthenticationProvider implements AuthenticationProvider {

    private final SecurityService securityService;

    public MemberAuthenticationProvider(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) {

        String uid = authentication.getName();
        String password = (String) authentication.getCredentials();

        Member member = securityService.authenticate(uid, password);
        if (member == null)
            throw new AppleException(Error.LOGIN_FAIL);
        member.setPassword(null);

        return new UsernamePasswordAuthenticationToken(member, null, member.getRoleSet());
    }

    @Override
    public boolean supports(Class authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}