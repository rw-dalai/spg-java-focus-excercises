package at.spengergasse.fachtheorie.aufgabe4;

import at.spengergasse.fachtheorie.aufgabe4.models.*;
import at.spengergasse.fachtheorie.aufgabe4.persistence.*;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;

import static at.spengergasse.fachtheorie.aufgabe4.fixtures.LibraryFixtures.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
@ContextConfiguration(classes = Aufgabe4Application.class)
class OptionalWorksheetTests {

    @Autowired private BookRepository bookRepository;
    @Autowired private MemberRepository memberRepository;
    @Autowired private EntityManager entityManager;

    private OptionalWorksheet worksheet;
    private Book javaBook;
    private Member anna;

    @BeforeEach
    void setUp() {
        worksheet = new OptionalWorksheet();
        anna = memberRepository.save(anna());
        javaBook = bookRepository.save(javaBook());
        entityManager.flush();
        entityManager.clear();
    }

    @Test
    void exercise01_findBookOrThrow_found() {
        var book = worksheet.exercise01_findBookOrThrow(javaBook.getId(), bookRepository);
        assertThat(book.getTitle()).isEqualTo("Effective Java");
    }

    @Test
    void exercise01_findBookOrThrow_notFound() {
        assertThatThrownBy(() -> worksheet.exercise01_findBookOrThrow(999L, bookRepository))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Book not found.");
    }

    @Test
    void exercise02_findBookOrDefault_found() {
        var fallback = new Book("Fallback", "000-0000000000", Genre.FICTION, BigDecimal.ONE);
        var book = worksheet.exercise02_findBookOrDefault(javaBook.getId(), bookRepository, fallback);
        assertThat(book.getTitle()).isEqualTo("Effective Java");
    }

    @Test
    void exercise02_findBookOrDefault_notFound() {
        var fallback = new Book("Fallback", "000-0000000000", Genre.FICTION, BigDecimal.ONE);
        var book = worksheet.exercise02_findBookOrDefault(999L, bookRepository, fallback);
        assertThat(book.getTitle()).isEqualTo("Fallback");
    }

    @Test
    void exercise03_borrowBook_valid() {
        var loan = worksheet.exercise03_borrowBook(javaBook.getId(), anna.getId(), bookRepository, memberRepository);
        assertThat(loan).isNotNull();
        assertThat(loan.getBook().getTitle()).isEqualTo("Effective Java");
        assertThat(loan.getMember().getEmail()).isEqualTo("anna@student.spengergasse.at");
        assertThat(loan.getReturnDate()).isNull();
    }

    @Test
    void exercise03_borrowBook_invalidBook() {
        assertThatThrownBy(() -> worksheet.exercise03_borrowBook(999L, anna.getId(),
            bookRepository, memberRepository))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Invalid book.");
    }

    @Test
    void exercise03_borrowBook_invalidMember() {
        assertThatThrownBy(() -> worksheet.exercise03_borrowBook(javaBook.getId(), 999L,
            bookRepository, memberRepository))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Invalid member.");
    }

    @Test
    void exercise04_getBookTitle_found() {
        var title = worksheet.exercise04_getBookTitle(javaBook.getId(), bookRepository);
        assertThat(title).isPresent().contains("Effective Java");
    }

    @Test
    void exercise04_getBookTitle_notFound() {
        var title = worksheet.exercise04_getBookTitle(999L, bookRepository);
        assertThat(title).isEmpty();
    }

    @Test
    void exercise05_findExpensiveBook_aboveMin() {
        // javaBook has pricePerDay 2.50, minPrice 2.00 -> present
        var book = worksheet.exercise05_findExpensiveBook(javaBook.getId(), bookRepository, new BigDecimal("2.00"));
        assertThat(book).isPresent();
        assertThat(book.get().getTitle()).isEqualTo("Effective Java");
    }

    @Test
    void exercise05_findExpensiveBook_belowMin() {
        // javaBook has pricePerDay 2.50, minPrice 3.00 -> empty
        var book = worksheet.exercise05_findExpensiveBook(javaBook.getId(), bookRepository, new BigDecimal("3.00"));
        assertThat(book).isEmpty();
    }
}
