package org.godseop.apple.entity;

import java.time.LocalDateTime;
import java.util.*;

import javax.persistence.*;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Setter
@Entity(name = "T_POST")
@EqualsAndHashCode(of = "id")
@ToString
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String content;

    @CreationTimestamp
    private LocalDateTime regDate;

    @UpdateTimestamp
    private LocalDateTime modDate;

    @OneToMany(mappedBy = "post")
    private List<Comment> commentList = new LinkedList<>();

    @OneToMany(mappedBy = "post")
    private Set<HashTag> hashTagSet = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

}
