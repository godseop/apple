package org.godseop.apple.model;


import org.godseop.apple.entity.mysql.Member;
import org.springframework.security.core.userdetails.User;

public class SecurityMember extends User {

    private String ip;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public SecurityMember(Member member) {
        super(member.getUid(), member.getPassword(), member.getRoleSet());

    }

}
