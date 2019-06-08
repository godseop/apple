package org.godseop.apple.entity;

import java.time.LocalDateTime;


import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "T_COMMENT")
@EqualsAndHashCode(of = "id")
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
