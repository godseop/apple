package org.godseop.apple.entity;

import java.util.List;

import javax.persistence.*;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Entity(name="users")
@Alias("user")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer seq;

    @Column(unique = true)
    private String id;
    private String name;
    
    @OneToMany(mappedBy="user")
    private List<Comment> comments;

}
