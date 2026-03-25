package at.spengergasse.fachtheorie.aufgabe3;

import at.spengergasse.fachtheorie.aufgabe3.models.*;
import at.spengergasse.fachtheorie.aufgabe3.persistence.*;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;
import java.time.LocalDate;

import static at.spengergasse.fachtheorie.aufgabe3.fixtures.LibraryFixtures.*;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ContextConfiguration(classes = Aufgabe3Application.class)
class FinderTests {

    @Autowired private BookRepository bookRepository;
    @Autowired private MemberRepository memberRepository;
    @Autowired private LoanRepository loanRepository;
    @Autowired private EntityManager em;

    private Member anna;
    private Member karl;
    private Book javaBook;
    private Book hobbitBook;
    private Book historyBook;

    @BeforeEach
    void setUp() {
        anna = memberRepository.save(anna());
        karl = memberRepository.save(karl());
        javaBook = bookRepository.save(javaBook());
        hobbitBook = bookRepository.save(hobbitBook());
        historyBook = bookRepository.save(historyBook());

        // Loan 1: javaBook an anna, Jan 10, aktiv (kein return, keine fee)
        loanRepository.save(activeLoan(javaBook, anna, LocalDate.of(2025, 1, 10)));
        // Loan 2: hobbitBook an karl, Jan 5 bis Jan 20, fee=27.00
        loanRepository.save(returnedLoan(hobbitBook, karl,
                LocalDate.of(2025, 1, 5), LocalDate.of(2025, 1, 20), new BigDecimal("27.00")));
        // Loan 3: historyBook an anna, Feb 1 bis Feb 15, fee=42.00
        loanRepository.save(returnedLoan(historyBook, anna,
                LocalDate.of(2025, 2, 1), LocalDate.of(2025, 2, 15), new BigDecimal("42.00")));

        em.flush();
        em.clear();
    }

    // ══════════════════════════════════════════════════════════
    //  LEICHT (Aufgabe 1-7)
    // ══════════════════════════════════════════════════════════

    @Test
    void exercise01_findByGenre() {
        var books = bookRepository.findByGenre(Genre.SCIENCE);
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getTitle()).isEqualTo("Effective Java");
    }

    @Test
    void exercise02_findByReturnDateIsNull() {
        var active = loanRepository.findByReturnDateIsNull();
        assertThat(active).hasSize(1);
        assertThat(active.get(0).getBook().getIsbn()).isEqualTo("978-0134685991");
    }

    @Test
    void exercise03_findByReturnDateIsNotNull() {
        var returned = loanRepository.findByReturnDateIsNotNull();
        assertThat(returned).hasSize(2);
    }

    @Test
    void exercise04_findByEmail() {
        var member = memberRepository.findByEmail("anna@student.spengergasse.at");
        assertThat(member).isPresent();
        assertThat(member.get().getName()).isEqualTo("Anna Müller");
        var nobody = memberRepository.findByEmail("nobody@example.com");
        assertThat(nobody).isEmpty();
    }

    @Test
    void exercise05_existsByEmail() {
        assertThat(memberRepository.existsByEmail("anna@student.spengergasse.at")).isTrue();
        assertThat(memberRepository.existsByEmail("nobody@example.com")).isFalse();
    }

    @Test
    void exercise06_findByTitleContainingIgnoreCase() {
        var books = bookRepository.findByTitleContainingIgnoreCase("java");
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getTitle()).isEqualTo("Effective Java");
    }

    @Test
    void exercise07_findByLoanDateBefore() {
        var loans = loanRepository.findByLoanDateBefore(LocalDate.of(2025, 1, 10));
        assertThat(loans).hasSize(1);
    }

    // ══════════════════════════════════════════════════════════
    //  MITTEL (Aufgabe 8-14)
    // ══════════════════════════════════════════════════════════

    @Test
    void exercise08_findByMemberEmail() {
        var loans = loanRepository.findByMemberEmail("anna@student.spengergasse.at");
        assertThat(loans).hasSize(2);
    }

    @Test
    void exercise09_findByBookGenre() {
        var scienceLoans = loanRepository.findByBookGenre(Genre.SCIENCE);
        assertThat(scienceLoans).hasSize(1);
        var fictionLoans = loanRepository.findByBookGenre(Genre.FICTION);
        assertThat(fictionLoans).isEmpty();
    }

    @Test
    void exercise10_findByLoanDateBetween() {
        var loans = loanRepository.findByLoanDateBetween(
                LocalDate.of(2025, 1, 1), LocalDate.of(2025, 1, 31));
        assertThat(loans).hasSize(2);
    }

    @Test
    void exercise11_findByFeeGreaterThan() {
        var loans = loanRepository.findByFeeGreaterThan(new BigDecimal("30.00"));
        assertThat(loans).hasSize(1);
        assertThat(loans.get(0).getFee()).isEqualByComparingTo(new BigDecimal("42.00"));
    }

    @Test
    void exercise12_findByBookPricePerDayLessThanEqual() {
        var loans = loanRepository.findByBookPricePerDayLessThanEqual(new BigDecimal("2.00"));
        assertThat(loans).hasSize(1);
        assertThat(loans.get(0).getBook().getTitle()).isEqualTo("The Hobbit");
    }

    @Test
    void exercise13_findByLoanDateAfter() {
        var loans = loanRepository.findByLoanDateAfter(LocalDate.of(2025, 1, 10));
        assertThat(loans).hasSize(1);
        assertThat(loans.get(0).getBook().getTitle()).isEqualTo("Sapiens");
    }

    @Test
    void exercise14_countByMemberId() {
        long count = loanRepository.countByMemberId(anna.getId());
        assertThat(count).isEqualTo(2);
    }

    // ══════════════════════════════════════════════════════════
    //  SCHWER (Aufgabe 15-21)
    // ══════════════════════════════════════════════════════════

    @Test
    void exercise15_existsByBookIsbnAndReturnDateIsNull() {
        assertThat(loanRepository.existsByBookIsbnAndReturnDateIsNull("978-0134685991")).isTrue();
        assertThat(loanRepository.existsByBookIsbnAndReturnDateIsNull("978-0547928227")).isFalse();
    }

    @Test
    void exercise16_findByReturnDateIsNullOrderByLoanDateAsc() {
        var loans = loanRepository.findByReturnDateIsNullOrderByLoanDateAsc();
        assertThat(loans).hasSize(1);
        assertThat(loans.get(0).getLoanDate()).isEqualTo(LocalDate.of(2025, 1, 10));
    }

    @Test
    void exercise17_findTop3ByOrderByFeeDesc() {
        var loans = loanRepository.findTop3ByOrderByFeeDesc();
        assertThat(loans).hasSizeGreaterThanOrEqualTo(2);
        assertThat(loans.get(0).getFee()).isEqualByComparingTo(new BigDecimal("42.00"));
    }

    @Test
    void exercise18_findByMemberIdAndReturnDateIsNull() {
        var loans = loanRepository.findByMemberIdAndReturnDateIsNull(anna.getId());
        assertThat(loans).hasSize(1);
        var karlLoans = loanRepository.findByMemberIdAndReturnDateIsNull(karl.getId());
        assertThat(karlLoans).isEmpty();
    }

    @Test
    void exercise19_findByPricePerDayBetween() {
        var books = bookRepository.findByPricePerDayBetween(
                new BigDecimal("1.50"), new BigDecimal("2.60"));
        assertThat(books).hasSize(2);
    }

    @Test
    void exercise20_findAllByOrderByPricePerDayAsc() {
        var books = bookRepository.findAllByOrderByPricePerDayAsc();
        assertThat(books).hasSize(3);
        assertThat(books.get(0).getPricePerDay()).isEqualByComparingTo(new BigDecimal("1.80"));
        assertThat(books.get(2).getPricePerDay()).isEqualByComparingTo(new BigDecimal("3.00"));
    }

    @Test
    void exercise21_findByBookGenreAndFeeGreaterThan() {
        // historyBook loan hat fee 42.00, HISTORY genre
        var loans = loanRepository.findByBookGenreAndFeeGreaterThan(Genre.HISTORY, new BigDecimal("30.00"));
        assertThat(loans).hasSize(1);
        // Keine SCIENCE loans haben fees (aktive Ausleihe, fee ist null)
        var noLoans = loanRepository.findByBookGenreAndFeeGreaterThan(Genre.SCIENCE, new BigDecimal("1.00"));
        assertThat(noLoans).isEmpty();
    }
}
