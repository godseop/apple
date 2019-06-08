package org.godseop.apple.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@Entity(name = "T_POST")
@EqualsAndHashCode(of = "id")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "uid")
    private Member member;

    @Column(nullable = false)
    private String title;

    private String content;

    @CreationTimestamp
    private LocalDateTime regDate;

    @UpdateTimestamp
    private LocalDateTime modDate;

    @OneToMany(mappedBy = "post")
    private List<Comment> commentList;

    @OneToMany(mappedBy = "post")
    private List<HashTag> hashTagList;

}
