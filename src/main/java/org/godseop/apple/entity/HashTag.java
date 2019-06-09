package org.godseop.apple.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Data
@Entity(name = "T_HASHTAG")
@EqualsAndHashCode(of = "id")
@ToString(exclude = {"post"})
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer"})
public class HashTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String tag;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

}
