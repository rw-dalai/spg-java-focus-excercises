package at.spengergasse.fachtheorie.aufgabe3.persistence;

import at.spengergasse.fachtheorie.aufgabe3.models.Book;
import at.spengergasse.fachtheorie.aufgabe3.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

/**
 * ══════════════════════════════════════════════════════════════
 *  MAGIC FINDER WORKSHEET — BookRepository
 * ══════════════════════════════════════════════════════════════
 *
 *  Aufbauregel:  <Prefix>By<Property>[<Operator>](<Params>)
 *  ──────────────────────────────────────────────────────
 *  Prefixe:      findBy -> List/Optional  |  countBy -> long  |  existsBy -> boolean
 *  Null:         IsNull  |  IsNotNull     (kein Parameter!)
 *  Vergleich:    GreaterThan  |  LessThanEqual  |  Between
 *  String:       ContainingIgnoreCase  |  StartingWith
 *  Datum:        Between  |  Before  |  After
 *  FK-Nav:       findByMemberEmail → navigiert Loan.member.email
 *  Kombi:        And  |  Or
 *  Sortierung:   OrderByXxxAsc  |  OrderByXxxDesc
 *  Limit:        findTop3By...  |  findFirstBy...
 *
 *  Kommentiere die Lösung ein (entferne //) und führe die Tests aus.
 */
public interface BookRepository extends JpaRepository<Book, Long> {

    // ══════════════════════════════════════════════════════════
    //  LEICHT
    // ══════════════════════════════════════════════════════════

    // Aufgabe 1: Finde Bücher eines bestimmten Genres
    // Verwende: findBy + Property
    // TODO

    // Aufgabe 6: Finde Bücher deren Titel einen Suchbegriff enthält (case-insensitive)
    // TODO

    // ══════════════════════════════════════════════════════════
    //  SCHWER
    // ══════════════════════════════════════════════════════════

    // Aufgabe 19: Finde Bücher in einer Preisspanne
    // TODO

    // Aufgabe 20: Finde alle Bücher sortiert nach Preis aufsteigend
    // TODO
}
