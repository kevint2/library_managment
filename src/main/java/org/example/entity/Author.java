package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "authors")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private Long id;
    private String firstname;
    private String lastname;
    private String bio;
    @ManyToMany
    @JoinTable(name = "authors_books", joinColumns =
            {@JoinColumn(name = "author_id", referencedColumnName = "author_id")},
            inverseJoinColumns =
                    {@JoinColumn(name = "book_id", referencedColumnName = "book_id")})
    private List<Book>books;
}
