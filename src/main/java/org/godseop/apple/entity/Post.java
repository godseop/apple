package org.godseop.apple.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity
public class Post {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
    private String title;
    
    private String content;
    
    @CreationTimestamp
    private LocalDateTime createdOn;
    
    @OneToMany(mappedBy="post")
    private List<Comment> comments;
    
}
