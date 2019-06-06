package org.godseop.apple.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Demo {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private String a;

    private Integer b;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;

}
