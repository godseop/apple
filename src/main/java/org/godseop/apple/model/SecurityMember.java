package org.godseop.apple.model;


import org.godseop.apple.entity.Member;
import org.springframework.security.core.userdetails.User;

public class SecurityMember extends User {

    private String ip;

    public SecurityMember(Member member) {
        super(member.getUid(), member.getPassword(), member.getRoleSet());
    }

}
