package at.spengergasse.fachtheorie.aufgabe3.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "book")
@Getter
@ToString(callSuper = true)
public class Book extends BaseEntity {

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, unique = true)
    private String isbn;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Genre genre;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal pricePerDay;

    @OneToMany(mappedBy = "book")
    @ToString.Exclude
    private final List<Loan> loans = new ArrayList<>();

    protected Book() {}

    public Book(String title, String isbn, Genre genre, BigDecimal pricePerDay) {
        this.title = title;
        this.isbn = isbn;
        this.genre = genre;
        this.pricePerDay = pricePerDay;
    }
}
