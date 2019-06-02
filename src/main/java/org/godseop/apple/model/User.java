package org.godseop.apple.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.apache.ibatis.type.Alias;

@Data
@Entity
@Alias("user")
public class User {

	@Id
    private Integer seq;
    private String id;
    private String name;

}
