package org.godseop.apple.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "T_MEMBER_ROLE")
@EqualsAndHashCode(of = "id")
public class MemberRole implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roleName;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Override
    public String getAuthority() {
        return this.roleName;
    }

    @Override
    public String toString() {
        return this.roleName;
    }
}
