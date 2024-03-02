package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.static_data.Status;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "requests")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private Long id;
    private String note;
    @Enumerated(value = EnumType.STRING)
    private Status status;
    private LocalDate createtAt;
    private  LocalDate returnedAt;
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToMany
    @JoinTable(name = "requests_books", joinColumns =
            {@JoinColumn(name = "request_id", referencedColumnName = "request_id")},
            inverseJoinColumns =
                    {@JoinColumn(name = "book_id", referencedColumnName = "book_id")})
    private List<Book>books;

}
