package org.godseop.apple.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

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


    @OneToMany(mappedBy="comment")
    private List<Demo> demos;


    @CreationTimestamp
    private LocalDateTime createdOn;


    @ManyToOne
    @JoinColumn(name = "user_seq")
    private User user;


    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
}
