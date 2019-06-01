package org.godseop.apple.model;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("user")
public class User {

    private Integer seq;
    private String id;
    private String name;

}
