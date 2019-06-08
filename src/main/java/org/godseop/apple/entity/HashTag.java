package org.godseop.apple.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "T_HASHTAG")
@EqualsAndHashCode(of = "id")
@ToString
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
