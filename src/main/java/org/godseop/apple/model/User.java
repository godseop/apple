package org.godseop.apple.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.apache.ibatis.type.Alias;
import org.godseop.apple.entity.Comment;

import lombok.Data;

@Data
@Entity(name="users")
@Alias("user")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer seq;
    private String id;
    private String name;
    
    @OneToMany(mappedBy="user")
    private List<Comment> comments;

}
