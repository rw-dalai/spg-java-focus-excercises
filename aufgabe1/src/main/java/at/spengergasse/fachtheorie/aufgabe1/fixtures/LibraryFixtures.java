package at.spengergasse.fachtheorie.aufgabe1.fixtures;

import at.spengergasse.fachtheorie.aufgabe1.models.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public final class LibraryFixtures {

    private LibraryFixtures() {}

    public static StudentMember anna() {
        return new StudentMember("anna@student.spengergasse.at", "Anna Müller", "STU001", 20);
    }

    public static StaffMember karl() {
        return new StaffMember("karl@spengergasse.at", "Karl Huber", "IT");
    }

    public static Book javaBook() {
        return new Book("Effective Java", "978-0134685991", Genre.SCIENCE, new BigDecimal("2.50"));
    }

    public static Book hobbitBook() {
        return new Book("The Hobbit", "978-0547928227", Genre.FANTASY, new BigDecimal("1.80"));
    }

    public static Book historyBook() {
        return new Book("Sapiens", "978-0062316097", Genre.HISTORY, new BigDecimal("3.00"));
    }

    public static Loan activeLoan(Book book, Member member, LocalDate loanDate) {
        return new Loan(book, member, loanDate, null, null);
    }

    public static Loan returnedLoan(Book book, Member member, LocalDate loanDate,
                                     LocalDate returnDate, BigDecimal fee) {
        return new Loan(book, member, loanDate, returnDate, fee);
    }
}
