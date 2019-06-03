package org.godseop.apple.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.ibatis.type.Alias;

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

}
