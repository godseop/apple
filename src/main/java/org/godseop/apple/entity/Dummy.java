package org.godseop.apple.entity;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

    private Boolean bool;

    private Character yn;

}
