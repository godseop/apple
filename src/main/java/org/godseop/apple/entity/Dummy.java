package org.godseop.apple.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

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

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") // for modelattribute serialize
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul") // for responsebody
    private LocalDateTime time;

    @Column(columnDefinition = "bit(1) default true")
    private Boolean bool;

    private Character yn;

}
