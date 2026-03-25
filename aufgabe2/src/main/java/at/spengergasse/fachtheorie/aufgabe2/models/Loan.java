package at.spengergasse.fachtheorie.aufgabe2.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "loan")
@Getter
@ToString(callSuper = true)
public class Loan extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "book_id", nullable = false)
    @ToString.Exclude
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id", nullable = false)
    @ToString.Exclude
    private Member member;

    @Column(nullable = false)
    private LocalDate loanDate;

    @Setter
    private LocalDate returnDate;

    @Setter
    @Column(precision = 10, scale = 2)
    private BigDecimal fee;

    protected Loan() {}

    public Loan(Book book, Member member, LocalDate loanDate, LocalDate returnDate, BigDecimal fee) {
        this.book = book;
        this.member = member;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
        this.fee = fee;
    }
}
