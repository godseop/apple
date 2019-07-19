package org.godseop.apple.entity;

import java.time.LocalDateTime;
import java.util.*;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import lombok.*;
import org.apache.ibatis.type.Alias;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@Alias("member")
@Entity(name = "T_MEMBER")
@ToString(exclude = {"roleSet", "postList"})
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 30)
    private String uid;

    @Column(nullable = false, length = 200)
    @JsonProperty(access = Access.WRITE_ONLY) // JSON Serialize Only (for only setter!)
    private String password;

    @Column(nullable = false, unique = true, length = 50)
    private String nickname;

    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @Column
    private String location;

    @CreationTimestamp
    private LocalDateTime regDate;

    @UpdateTimestamp
    private LocalDateTime modDate;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.EAGER) // , orphanRemoval = true
    private Set<MemberRole> roleSet;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Post> postList;

}
