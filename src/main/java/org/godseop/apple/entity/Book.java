package org.godseop.apple.entity;

import lombok.Data;
import org.apache.ibatis.type.Alias;
import org.godseop.apple.entity.type.BookGenre;
import org.godseop.apple.entity.type.BookType;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Alias("book")
@Entity(name = "T_BOOK")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
