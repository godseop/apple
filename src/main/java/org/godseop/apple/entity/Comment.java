package org.godseop.apple.entity;

import java.time.LocalDateTime;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;

@Data
@Entity(name = "T_COMMENT")
@EqualsAndHashCode(of = "id")
@ToString(exclude = {"post"})
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer"})
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
