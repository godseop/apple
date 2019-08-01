package org.godseop.apple.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.apache.ibatis.type.Alias;
import org.godseop.apple.entity.type.BookGenre;
import org.godseop.apple.entity.type.BookType;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "T_BOOK")
@Getter
@Alias("book")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = {"id"})
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Book {

    @Builder
    protected Book(String title) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
    }


    @Id
    private String id;

    @Column(nullable = false)
    @Size(min = 10)
    private String title;

    @Column(length = 50)        // JPA Generation SCHEMA
    @Size(max = 50)             // Bean Validation
    private String author;

    @Enumerated(EnumType.STRING)
    private BookType type;

    @Enumerated(EnumType.STRING)
    private BookGenre genre;

    private String publisher;

    private Integer price;

    @Column(unique = true)
    private String isbn;

    private Integer numberOfPages;

    private LocalDateTime publicationDate;


}
