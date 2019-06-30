package org.godseop.apple.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "T_DUMMY")
@Alias("dummy")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = {"id"})
@ToString(exclude = "yn")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Dummy {

    @Builder
    public Dummy(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }

    @Id
    private String id;

    private Integer count;

    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") // for modelattribute serialize
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul") // for responsebody
    private LocalDateTime time;

    @Column(columnDefinition = "bit(1) default true")
    private Boolean bool;

    private Character yn;

}
