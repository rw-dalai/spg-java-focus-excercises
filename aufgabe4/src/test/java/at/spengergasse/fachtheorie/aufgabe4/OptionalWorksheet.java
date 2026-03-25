package at.spengergasse.fachtheorie.aufgabe4;

import at.spengergasse.fachtheorie.aufgabe4.models.*;
import at.spengergasse.fachtheorie.aufgabe4.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

/**
 * ══════════════════════════════════════════════════════════════
 *  OPTIONAL WORKSHEET — Optional & Guard Pattern Übungen
 * ══════════════════════════════════════════════════════════════
 *
 *  Optional<T>:  Container der einen Wert enthalten KANN oder leer ist
 *  ──────────────────────────────────────────────────────────
 *  Erstellen:    Optional.of(value)  |  Optional.ofNullable(value)  |  Optional.empty()
 *  ──────────────────────────────────────────────────────────
 *  Auspacken:   .orElse(default)           -> T (Fallback-Wert)
 *               .orElseThrow(() -> ex)     -> T (Exception wenn leer)
 *               .orElseThrow()             -> T (NoSuchElementException)
 *  ──────────────────────────────────────────────────────────
 *  Prüfen:          .isPresent()  |  .isEmpty()
 *  Transformieren:  .map(x -> y)  |  .filter(x -> bool)
 *  ──────────────────────────────────────────────────────────
 *  Repository:   repo.findById(id) -> Optional<T>
 */
public class OptionalWorksheet {

    // Übung 1: Finde ein Buch per ID oder wirf eine Exception
    // Verwende: repo.findById(..).orElseThrow(..)
    public Book exercise01_findBookOrThrow(Long id, BookRepository repo) {
        throw new UnsupportedOperationException("TODO");
    }

    // Übung 2: Finde ein Buch per ID oder gib ein Fallback-Buch zurück
    // Verwende: repo.findById(id).orElse(fallback)
    public Book exercise02_findBookOrDefault(Long id, BookRepository repo, Book fallback) {
        throw new UnsupportedOperationException("TODO");
    }

    // Übung 3: Erstelle eine Ausleihe (Guard Pattern)
    // 1. Buch laden (orElseThrow mit "Invalid book.")
    // 2. Mitglied laden (orElseThrow mit "Invalid member.")
    // 3. Neue Loan erstellen und zurückgeben
    public Loan exercise03_borrowBook(Long bookId, Long memberId, BookRepository bookRepo, MemberRepository memberRepo) {
        // var book = bookRepo.findById(..);
        // var member = memberRepo.findById(..);
        // return new Loan(..);
        throw new UnsupportedOperationException("TODO");
    }

    // Übung 4: Gib den Buchtitel als Optional zurück (ohne das Buch selbst)
    // Verwende: repo.findById(id).map(..)
    public Optional<String> exercise04_getBookTitle(Long id, BookRepository repo) {
        throw new UnsupportedOperationException("TODO");
    }

    // Übung 5: Finde ein Buch nur wenn sein Preis über minPrice liegt
    // Verwende: repo.findById(id).filter(..)
    public Optional<Book> exercise05_findExpensiveBook(Long id, BookRepository repo, BigDecimal minPrice) {
        throw new UnsupportedOperationException("TODO");
    }
}
