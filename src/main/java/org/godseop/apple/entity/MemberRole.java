package org.godseop.apple.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "T_MEMBER_ROLE")
@JsonIgnoreProperties(value = {"member"})
public class MemberRole implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roleName;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public MemberRole(String roleName, Member member) {
        this.roleName = roleName;
        this.member = member;
    }

    @Override
    public String getAuthority() {
        return this.roleName;
    }

    @Override
    public String toString() {
        return this.roleName;
    }
}
