package at.spengergasse.fachtheorie.aufgabe3.persistence;

import at.spengergasse.fachtheorie.aufgabe3.models.Genre;
import at.spengergasse.fachtheorie.aufgabe3.models.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * ══════════════════════════════════════════════════════════════
 *  MAGIC FINDER WORKSHEET — LoanRepository
 * ══════════════════════════════════════════════════════════════
 *
 *  Aufbauregel:  {@code <Prefix>By<Property>[<Operator>](<Params>)}
 *  ──────────────────────────────────────────────────────
 *  Prefixe:      findBy -> List/Optional  |  countBy -> long  |  existsBy → boolean
 *  Null:         IsNull  |  IsNotNull     (kein Parameter!)
 *  Vergleich:    GreaterThan  |  LessThanEqual  |  Between
 *  String:       ContainingIgnoreCase  |  StartingWith
 *  Datum:        Between  |  Before  |  After
 *  FK-Nav:       findByMemberEmail -> navigiert Loan.member.email
 *  Kombi:        And  |  Or
 *  Sortierung:   OrderByXxxAsc  |  OrderByXxxDesc
 *  Limit:        findTop3By...  |  findFirstBy...
 *
 *  Kommentiere die Lösung ein (entferne //) und führe die Tests aus.
 */
public interface LoanRepository extends JpaRepository<Loan, Long> {

    // ══════════════════════════════════════════════════════════
    //  LEICHT — Einfache Finder auf eigenen Feldern
    // ══════════════════════════════════════════════════════════

    // Aufgabe 2: Finde alle aktiven Ausleihen (returnDate ist null)
    // Verwende: findBy + Property + IsNull
    // TODO

    // Aufgabe 3: Finde alle abgeschlossenen Ausleihen (returnDate ist NICHT null)
    // Verwende: findBy + Property + IsNotNull
    // TODO

    // Aufgabe 7: Finde Ausleihen vor einem bestimmten Datum
    // TODO

    // ══════════════════════════════════════════════════════════
    //  MITTEL — FK-Navigation, Between, Vergleiche
    // ══════════════════════════════════════════════════════════

    // Aufgabe 8: Finde alle Ausleihen eines Mitglieds (über E-Mail)
    // FK-Navigation: Loan -> Member.email
    // Verwende: findBy + FK-Property (MemberEmail)
    // TODO

    // Aufgabe 9: Finde alle Ausleihen eines bestimmten Genres
    // FK-Navigation: Loan -> Book.genre
    // Verwende: findBy + FK-Property (BookGenre)
    // TODO

    // Aufgabe 10: Finde Ausleihen in einem Zeitraum
    // Verwende: Between (2 Parameter: from, to)
    // TODO

    // Aufgabe 11: Finde Ausleihen mit Gebühr über einem Betrag
    // TODO

    // Aufgabe 12: Finde Ausleihen von Büchern mit günstigem Tagespreis
    // FK-Navigation + LessThanEqual
    // TODO

    // Aufgabe 13: Finde Ausleihen nach einem bestimmten Datum
    // TODO

    // Aufgabe 14: Zähle die Ausleihen eines Mitglieds
    // TODO

    // ══════════════════════════════════════════════════════════
    //  SCHWER — And, OrderBy, Top, Kombinationen
    // ══════════════════════════════════════════════════════════

    // Aufgabe 15: Prüfe ob ein Buch (nach ISBN) gerade ausgeliehen ist
    // Verwende: existsBy + FK-Navigation (Book.isbn) + And + IsNull
    // TODO

    // Aufgabe 16: Finde aktive Ausleihen, sortiert nach Datum aufsteigend
    // Verwende: findBy + IsNull + OrderBy...Asc
    // TODO

    // Aufgabe 17: Finde die 3 teuersten Ausleihen
    // Verwende: findTop3By + OrderBy...Desc
    // TODO

    // Aufgabe 18: Finde aktive Ausleihen eines bestimmten Mitglieds
    // TODO

    // Aufgabe 21: Finde Ausleihen eines Genres mit Gebühr über einem Betrag
    // FK-Navigation + And + GreaterThan
    // TODO
}
