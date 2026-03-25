package at.spengergasse.fachtheorie.aufgabe1;

import at.spengergasse.fachtheorie.aufgabe1.models.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

/**
 * ══════════════════════════════════════════════════════════════
 *  STREAM WORKSHEET — Java Stream API Übungen
 * ══════════════════════════════════════════════════════════════
 *
 *  Stream Pipeline:  collection.stream() -> intermediate ops -> terminal op
 *  ─────────────────────────────────────────────────────────────
 *  map(x -> y)              Transformation         -> Stream<Y>
 *  filter(x -> bool)        Filterung              -> Stream<X>
 *  sorted(Comparator)       Sortierung             -> Stream<X>
 *  distinct()               Duplikate entfernen    -> Stream<X>
 *  flatMap(x -> Stream<Y>)  Verschachtelte Listen  -> Stream<Y>
 *  ─────────────────────────────────────────────────────────────
 *  toList()                 → List<X>              Terminal
 *  count()                  → long                 Terminal
 *  max/min(Comparator)      → Optional<X>          Terminal
 *  mapToInt(x -> int).sum() → int                  Terminal
 *  anyMatch(x -> bool)      → boolean              Terminal
 *  noneMatch(x -> bool)     → boolean              Terminal
 *  ─────────────────────────────────────────────────────────────
 *  Collectors.groupingBy(classifier)                -> Map<K, List<V>>
 *  Collectors.groupingBy(classifier, counting())    -> Map<K, Long>
 *  Collectors.toMap(keyFn, valueFn)                 -> Map<K, V>
 *  Collectors.reducing(identity, mapper, op)        -> Map<K, R>
 */
public class StreamWorksheet {

    // ══════════════════════════════════════════════════════════
    //  LEICHT — Einzelne Stream-Operationen (Übung 1-10)
    // ══════════════════════════════════════════════════════════

    // Übung 1: Extrahiere alle Buchtitel aus den Ausleihen
    // Verwende: .stream().map(...).toList()
    public List<String> exercise01_getBookTitles(List<Loan> loans) {
        throw new UnsupportedOperationException("TODO");
    }

    // Übung 2: Filtere alle zurückgegebenen Ausleihen (returnDate ist NICHT null)
    // Verwende: .stream().filter(...).toList()
    public List<Loan> exercise02_getReturnedLoans(List<Loan> loans) {
        throw new UnsupportedOperationException("TODO");
    }

    // Übung 3: Zähle wie viele Ausleihen ein bestimmtes Genre haben
    // Verwende: .stream().filter(...).count()
    public long exercise03_countLoansByGenre(List<Loan> loans, Genre genre) {
        throw new UnsupportedOperationException("TODO");
    }

    // Übung 4: Sortiere Ausleihen nach Leihdatum (aufsteigend)
    // Verwende: .stream().sorted(Comparator.comparing(...)).toList()
    public List<Loan> exercise04_getLoansSortedByDate(List<Loan> loans) {
        throw new UnsupportedOperationException("TODO");
    }

    // Übung 5: Finde alle unterschiedlichen Genres aus den Ausleihen
    public List<Genre> exercise05_getUniqueGenres(List<Loan> loans) {
        throw new UnsupportedOperationException("TODO");
    }

    // Übung 6: Finde die höchste Gebühr
    public Optional<BigDecimal> exercise06_getMaxFee(List<Loan> loans) {
        throw new UnsupportedOperationException("TODO");
    }

    // Übung 7: Finde den niedrigsten Tagespreis aller ausgeliehenen Bücher
    public Optional<BigDecimal> exercise07_getMinPricePerDay(List<Loan> loans) {
        throw new UnsupportedOperationException("TODO");
    }

    // Übung 8: Projiziere Loans auf LoanDto Records
    // LoanDto(Long id, String bookTitle, String memberEmail, LocalDate loanDate, LocalDate returnDate, BigDecimal fee)
    public List<LoanDto> exercise08_toLoanDtos(List<Loan> loans) {
        throw new UnsupportedOperationException("TODO");
    }

    // Übung 9: Prüfe ob es aktive Ausleihen gibt (returnDate == null)
    public boolean exercise09_hasActiveLoans(List<Loan> loans) {
        throw new UnsupportedOperationException("TODO");
    }

    // Übung 10: Prüfe ob alle Ausleihen zurückgegeben wurden
    public boolean exercise10_allLoansReturned(List<Loan> loans) {
        throw new UnsupportedOperationException("TODO");
    }

    // ══════════════════════════════════════════════════════════
    //  MITTEL — Kombinierte Operationen (Übung 11-20)
    // ══════════════════════════════════════════════════════════

    // Übung 11: Finde die E-Mails aller Mitglieder mit aktiven Ausleihen
    // Verwende: .filter(l -> l.getReturnDate() == null).map(l -> l.getMember().getEmail()).toList()
    public List<String> exercise11_getActiveLoanEmails(List<Loan> loans) {
        throw new UnsupportedOperationException("TODO");
    }

    // Übung 12: Berechne die Summe aller Leihtage (nur zurückgegebene Ausleihen)
    // Verwende: .filter(...).mapToInt(l -> (int) ChronoUnit.DAYS.between(l.getLoanDate(), l.getReturnDate())).sum()
    public int exercise12_getTotalLoanDays(List<Loan> loans) {
        throw new UnsupportedOperationException("TODO");
    }

    // Übung 13: Summiere alle Gebühren (überspringe null-Gebühren)
    // Verwende: .filter(l -> l.getFee() != null).map(Loan::getFee).reduce(BigDecimal.ZERO, BigDecimal::add)
    public BigDecimal exercise13_getTotalFees(List<Loan> loans) {
        throw new UnsupportedOperationException("TODO");
    }

    // Übung 14: Projiziere zurückgegebene Loans auf LoanDto, mit berechneten Leihtagen als fee
    // Verwende: filter(returned) + map to DTO mit BigDecimal.valueOf(ChronoUnit.DAYS.between(...))
    public List<LoanDto> exercise14_toLoanDtosWithDays(List<Loan> loans) {
        throw new UnsupportedOperationException("TODO");
    }

    // Übung 15: Finde die maximale Anzahl an Leihtagen (nur zurückgegebene)
    public OptionalInt exercise15_getMaxLoanDays(List<Loan> loans) {
        throw new UnsupportedOperationException("TODO");
    }

    // Übung 16: Sammle alle Leihdaten aller Mitglieder (flatMap über Member.getLoans())
    public List<LocalDate> exercise16_getAllLoanDatesFromMembers(List<Member> members) {
        throw new UnsupportedOperationException("TODO");
    }

    // Übung 17: Finde E-Mails aktiver Ausleihen, alphabetisch sortiert
    public List<String> exercise17_getActiveLoanEmailsSorted(List<Loan> loans) {
        throw new UnsupportedOperationException("TODO");
    }

    // Übung 18: Finde die N teuersten Ausleihen (sortiert nach Gebühr absteigend)
    public List<Loan> exercise18_getTopNExpensiveLoans(List<Loan> loans, int n) {
        throw new UnsupportedOperationException("TODO");
    }

    // Übung 19: Prüfe ob es überfällige Ausleihen gibt (aktiv und loanDate > 30 Tage her)
    public boolean exercise19_hasOverdueLoans(List<Loan> loans, LocalDate today) {
        throw new UnsupportedOperationException("TODO");
    }

    // Übung 20 🔥: Erstelle eine Liste von BookLoanCountDto (Buchtitel + Anzahl Ausleihen)
    public List<BookLoanCountDto> exercise20_getBookWithLoanCount(List<Book> books) {
        throw new UnsupportedOperationException("TODO");
    }

    // ══════════════════════════════════════════════════════════
    //  SCHWER — Collectors, Nested Streams (Übung 21-30)
    // ══════════════════════════════════════════════════════════

    // Übung 21: Gruppiere Ausleihen nach dem Genre des Buches
    // Verwende: .collect(Collectors.groupingBy(l -> l.getBook().getGenre()))
    public Map<Genre, List<Loan>> exercise21_groupLoansByGenre(List<Loan> loans) {
        throw new UnsupportedOperationException("TODO");
    }

    // Übung 22: Zähle die Ausleihen pro Mitglied-E-Mail
    // Verwende: Collectors.groupingBy(..., Collectors.counting())
    public Map<String, Long> exercise22_countLoansPerMember(List<Loan> loans) {
        throw new UnsupportedOperationException("TODO");
    }

    // Übung 23 🔥: Finde Mitglieder die mindestens eine aktive Ausleihe haben
    // Verschachtelter Stream: filter(m -> m.getLoans().stream().anyMatch(...))
    public List<Member> exercise23_getMembersWithActiveLoans(List<Member> members) {
        throw new UnsupportedOperationException("TODO");
    }

    // Übung 24 🔥: Finde Mitglieder die in einem bestimmten Jahr KEINE Ausleihe hatten
    // Verschachtelter Stream: filter(m -> m.getLoans().stream().noneMatch(...))
    public List<Member> exercise24_getMembersWithoutLoansInYear(List<Member> members, int year) {
        throw new UnsupportedOperationException("TODO");
    }

    // Übung 25 🔥: Projiziere Loans auf LoanDto, aber fee nur wenn includeFee == true
    public List<LoanDto> exercise25_toLoanDtosConditional(List<Loan> loans, boolean includeFee) {
        throw new UnsupportedOperationException("TODO");
    }

    // Übung 26: Berechne die Gesamtgebühren pro Mitglied-E-Mail
    public Map<String, BigDecimal> exercise26_totalFeesPerMember(List<Loan> loans) {
        throw new UnsupportedOperationException("TODO");
    }

    // Übung 27: Erstelle eine Map von ISBN → Book
    public Map<String, Book> exercise27_booksByIsbn(List<Book> books) {
        throw new UnsupportedOperationException("TODO");
    }

    // Übung 28: Erstelle eine Map von Genre → Liste der Buchtitel
    public Map<Genre, List<String>> exercise28_genreToBookTitles(List<Loan> loans) {
        throw new UnsupportedOperationException("TODO");
    }

    // Übung 29 🔥: Finde Mitglieder mit mehr als 'min' Ausleihen
    public List<Member> exercise29_getMembersWithLoanCountAbove(List<Member> members, int min) {
        throw new UnsupportedOperationException("TODO");
    }

    // Übung 30: Sortiere Mitglieder nach Anzahl ihrer Ausleihen (absteigend)
    public List<Member> exercise30_getMembersSortedByLoanCount(List<Member> members) {
        throw new UnsupportedOperationException("TODO");
    }
}
