package org.godseop.apple.entity;

import java.time.LocalDateTime;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "T_COMMENT")
@EqualsAndHashCode(of = "id")
@ToString
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @CreationTimestamp
    private LocalDateTime regDate;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
}
