package org.godseop.apple.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@Alias("member")
@Entity(name = "T_MEMBER")
@EqualsAndHashCode(of = "uid")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 30)
    private String uid;

    @Column(nullable = false, length = 200)
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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="uid")
    private List<MemberRole> roleList;

    @OneToMany(mappedBy = "member")
    private List<Post> postList;

}
