package org.godseop.apple.entity;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Alias("dummy")
@Entity(name = "T_DUMMY")
public class Dummy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer count;

    private String name;

    private LocalDateTime time;

    @Column(columnDefinition = "bit(1) default true")
    private Boolean bool;

    private Character yn;

}
