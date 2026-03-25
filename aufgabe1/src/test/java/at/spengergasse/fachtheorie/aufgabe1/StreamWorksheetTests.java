package at.spengergasse.fachtheorie.aufgabe1;

import at.spengergasse.fachtheorie.aufgabe1.models.*;
import at.spengergasse.fachtheorie.aufgabe1.persistence.*;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static at.spengergasse.fachtheorie.aufgabe1.fixtures.LibraryFixtures.*;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ContextConfiguration(classes = Aufgabe1Application.class)
class StreamWorksheetTests {

    @Autowired private BookRepository bookRepository;
    @Autowired private MemberRepository memberRepository;
    @Autowired private LoanRepository loanRepository;
    @Autowired private EntityManager em;

    private StreamWorksheet worksheet;
    private List<Loan> allLoans;
    private List<Book> allBooks;
    private List<Member> allMembers;

    @BeforeEach
    void setUp() {
        worksheet = new StreamWorksheet();

        var anna = memberRepository.save(anna());
        var karl = memberRepository.save(karl());
        var javaBook = bookRepository.save(javaBook());
        var hobbitBook = bookRepository.save(hobbitBook());
        var historyBook = bookRepository.save(historyBook());

        loanRepository.save(activeLoan(javaBook, anna, LocalDate.of(2025, 1, 10)));
        loanRepository.save(returnedLoan(hobbitBook, karl,
                LocalDate.of(2025, 1, 5), LocalDate.of(2025, 1, 20), new BigDecimal("27.00")));
        loanRepository.save(returnedLoan(historyBook, anna,
                LocalDate.of(2025, 2, 1), LocalDate.of(2025, 2, 15), new BigDecimal("42.00")));

        em.flush();
        em.clear();

        allLoans = loanRepository.findAll();
        allBooks = bookRepository.findAll();
        allMembers = memberRepository.findAll();
    }

    // ══════════════════════════════════════════════════════════
    //  LEICHT (Übung 1-10)
    // ══════════════════════════════════════════════════════════

    @Test
    void exercise01_getBookTitles() {
        var titles = worksheet.exercise01_getBookTitles(allLoans);
        assertThat(titles).containsExactlyInAnyOrder("Effective Java", "The Hobbit", "Sapiens");
    }

    @Test
    void exercise02_getReturnedLoans() {
        var returned = worksheet.exercise02_getReturnedLoans(allLoans);
        assertThat(returned).hasSize(2);
        assertThat(returned).allSatisfy(l -> assertThat(l.getReturnDate()).isNotNull());
    }

    @Test
    void exercise03_countLoansByGenre() {
        assertThat(worksheet.exercise03_countLoansByGenre(allLoans, Genre.SCIENCE)).isEqualTo(1);
        assertThat(worksheet.exercise03_countLoansByGenre(allLoans, Genre.FICTION)).isEqualTo(0);
    }

    @Test
    void exercise04_getLoansSortedByDate() {
        var sorted = worksheet.exercise04_getLoansSortedByDate(allLoans);
        assertThat(sorted).hasSize(3);
        assertThat(sorted.get(0).getLoanDate()).isEqualTo(LocalDate.of(2025, 1, 5));
        assertThat(sorted.get(1).getLoanDate()).isEqualTo(LocalDate.of(2025, 1, 10));
        assertThat(sorted.get(2).getLoanDate()).isEqualTo(LocalDate.of(2025, 2, 1));
    }

    @Test
    void exercise05_getUniqueGenres() {
        var genres = worksheet.exercise05_getUniqueGenres(allLoans);
        assertThat(genres).containsExactlyInAnyOrder(Genre.SCIENCE, Genre.FANTASY, Genre.HISTORY);
    }

    @Test
    void exercise06_getMaxFee() {
        assertThat(worksheet.exercise06_getMaxFee(allLoans))
                .isPresent()
                .get().satisfies(fee -> assertThat(fee).isEqualByComparingTo(new BigDecimal("42.00")));
    }

    @Test
    void exercise07_getMinPricePerDay() {
        assertThat(worksheet.exercise07_getMinPricePerDay(allLoans))
                .isPresent()
                .get().satisfies(price -> assertThat(price).isEqualByComparingTo(new BigDecimal("1.80")));
    }

    @Test
    void exercise08_toLoanDtos() {
        var dtos = worksheet.exercise08_toLoanDtos(allLoans);
        assertThat(dtos).hasSize(3);
        assertThat(dtos).anyMatch(dto -> dto.bookTitle().equals("Effective Java"));
        assertThat(dtos).anyMatch(dto -> dto.memberEmail().equals("karl@spengergasse.at"));
    }

    @Test
    void exercise09_hasActiveLoans() {
        assertThat(worksheet.exercise09_hasActiveLoans(allLoans)).isTrue();
    }

    @Test
    void exercise10_allLoansReturned() {
        assertThat(worksheet.exercise10_allLoansReturned(allLoans)).isFalse();
    }

    // ══════════════════════════════════════════════════════════
    //  MITTEL (Übung 11-20)
    // ══════════════════════════════════════════════════════════

    @Test
    void exercise11_getActiveLoanEmails() {
        var emails = worksheet.exercise11_getActiveLoanEmails(allLoans);
        assertThat(emails).containsExactly("anna@student.spengergasse.at");
    }

    @Test
    void exercise12_getTotalLoanDays() {
        // Jan 5 -> Jan 20 = 15 days, Feb 1 -> Feb 15 = 14 days -> 29
        assertThat(worksheet.exercise12_getTotalLoanDays(allLoans)).isEqualTo(29);
    }

    @Test
    void exercise13_getTotalFees() {
        // 27.00 + 42.00 = 69.00
        assertThat(worksheet.exercise13_getTotalFees(allLoans)).isEqualByComparingTo(new BigDecimal("69.00"));
    }

    @Test
    void exercise14_toLoanDtosWithDays() {
        var dtos = worksheet.exercise14_toLoanDtosWithDays(allLoans);
        // Only returned loans (2): hobbit (15 days), history (14 days)
        assertThat(dtos).hasSize(2);

        assertThat(dtos).anyMatch(dto ->
            dto.bookTitle().equals("The Hobbit") && dto.fee().compareTo(new BigDecimal("15")) == 0);

        assertThat(dtos).anyMatch(dto ->
            dto.bookTitle().equals("Sapiens") && dto.fee().compareTo(new BigDecimal("14")) == 0);
    }

    @Test
    void exercise15_getMaxLoanDays() {
        // max(15, 14) = 15
        assertThat(worksheet.exercise15_getMaxLoanDays(allLoans)).hasValue(15);
    }

    @Test
    void exercise16_getAllLoanDatesFromMembers() {
        var dates = worksheet.exercise16_getAllLoanDatesFromMembers(allMembers);
        assertThat(dates).hasSize(3);
    }

    @Test
    void exercise17_getActiveLoanEmailsSorted() {
        var emails = worksheet.exercise17_getActiveLoanEmailsSorted(allLoans);
        assertThat(emails).containsExactly("anna@student.spengergasse.at");
    }

    @Test
    void exercise18_getTopNExpensiveLoans() {
        var top = worksheet.exercise18_getTopNExpensiveLoans(allLoans, 1);
        assertThat(top).hasSize(1);
        assertThat(top.get(0).getFee()).isEqualByComparingTo(new BigDecimal("42.00"));
    }

    @Test
    void exercise19_hasOverdueLoans() {
        // Active loan from Jan 10, today Mar 1 -> 50 days > 30 -> true
        assertThat(worksheet.exercise19_hasOverdueLoans(allLoans, LocalDate.of(2025, 3, 1))).isTrue();

        // Today Jan 15 -> only 5 days -> false
        assertThat(worksheet.exercise19_hasOverdueLoans(allLoans, LocalDate.of(2025, 1, 15))).isFalse();
    }

    @Test
    void exercise20_getBookWithLoanCount() {
        var counts = worksheet.exercise20_getBookWithLoanCount(allBooks);
        assertThat(counts).hasSize(3);
        assertThat(counts).allSatisfy(dto -> assertThat(dto.loanCount()).isEqualTo(1));
    }

    // ══════════════════════════════════════════════════════════
    //  SCHWER (Übung 21-30)
    // ══════════════════════════════════════════════════════════

    @Test
    void exercise21_groupLoansByGenre() {
        var grouped = worksheet.exercise21_groupLoansByGenre(allLoans);
        assertThat(grouped).hasSize(3);
        assertThat(grouped).containsKeys(Genre.SCIENCE, Genre.FANTASY, Genre.HISTORY);
    }

    @Test
    void exercise22_countLoansPerMember() {
        var counts = worksheet.exercise22_countLoansPerMember(allLoans);
        assertThat(counts.get("anna@student.spengergasse.at")).isEqualTo(2L);
        assertThat(counts.get("karl@spengergasse.at")).isEqualTo(1L);
    }

    @Test
    void exercise23_getMembersWithActiveLoans() {
        var members = worksheet.exercise23_getMembersWithActiveLoans(allMembers);
        assertThat(members).hasSize(1);
        assertThat(members.get(0).getEmail()).isEqualTo("anna@student.spengergasse.at");
    }

    @Test
    void exercise24_getMembersWithoutLoansInYear() {
        var members = worksheet.exercise24_getMembersWithoutLoansInYear(allMembers, 2024);
        assertThat(members).hasSize(2);
    }

    @Test
    void exercise25_toLoanDtosConditional() {
        var dtos = worksheet.exercise25_toLoanDtosConditional(allLoans, false);
        assertThat(dtos).hasSize(3);
        assertThat(dtos).allSatisfy(dto -> assertThat(dto.fee()).isNull());
    }

    @Test
    void exercise26_totalFeesPerMember() {
        var fees = worksheet.exercise26_totalFeesPerMember(allLoans);
        assertThat(fees.get("anna@student.spengergasse.at")).isEqualByComparingTo(new BigDecimal("42.00"));
        assertThat(fees.get("karl@spengergasse.at")).isEqualByComparingTo(new BigDecimal("27.00"));
    }

    @Test
    void exercise27_booksByIsbn() {
        var map = worksheet.exercise27_booksByIsbn(allBooks);
        assertThat(map).hasSize(3);
        assertThat(map).containsKey("978-0134685991");
    }

    @Test
    void exercise28_genreToBookTitles() {
        var map = worksheet.exercise28_genreToBookTitles(allLoans);
        assertThat(map.get(Genre.SCIENCE)).containsExactly("Effective Java");
        assertThat(map.get(Genre.FANTASY)).containsExactly("The Hobbit");
        assertThat(map.get(Genre.HISTORY)).containsExactly("Sapiens");
    }

    @Test
    void exercise29_getMembersWithLoanCountAbove() {
        var members = worksheet.exercise29_getMembersWithLoanCountAbove(allMembers, 1);
        assertThat(members).hasSize(1);
        assertThat(members.get(0).getEmail()).isEqualTo("anna@student.spengergasse.at");
    }

    @Test
    void exercise30_getMembersSortedByLoanCount() {
        var sorted = worksheet.exercise30_getMembersSortedByLoanCount(allMembers);
        assertThat(sorted).hasSize(2);
        assertThat(sorted.get(0).getEmail()).isEqualTo("anna@student.spengergasse.at");
        assertThat(sorted.get(1).getEmail()).isEqualTo("karl@spengergasse.at");
    }
}
