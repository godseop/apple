package org.godseop.apple.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.godseop.apple.model.User;
import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity
public class Comment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	    
    private String name;
    
    private String email;
    
    private String content;
    
    @CreationTimestamp
    private LocalDateTime createdOn;
    
    @ManyToOne
    @JoinColumn(name = "user_seq")
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
}
