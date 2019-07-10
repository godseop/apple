package org.godseop.apple.entity.mysql;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Data
@Entity(name = "T_HASHTAG")
@JsonIgnoreProperties(value = {"post"})
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
