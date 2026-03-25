package at.spengergasse.fachtheorie.aufgabe2;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * ══════════════════════════════════════════════════════════════
 *  BIGDECIMAL WORKSHEET — BigDecimal Übungen
 * ══════════════════════════════════════════════════════════════
 *
 *  Erstellen:    new BigDecimal("19.50")  |  BigDecimal.valueOf(10L)
 *                NIEMALS: new BigDecimal(19.50) → Rundungsfehler!
 *  ──────────────────────────────────────────────────────────
 *  Rechnen:      a.add(b)  |  a.subtract(b)  |  a.multiply(b)
 *                a.divide(b, scale, RoundingMode.HALF_UP)
 *  ──────────────────────────────────────────────────────────
 *  Vergleich:    a.compareTo(b)  ->  -1, 0, +1
 *                NIEMALS: a.equals(b) → prüft auch Scale!
 *                new BigDecimal("1.0").equals(new BigDecimal("1.00")) -> FALSE
 *  ──────────────────────────────────────────────────────────
 *  Konstanten:   BigDecimal.ZERO  |  BigDecimal.ONE  |  BigDecimal.TEN
 *  Runden:       RoundingMode.HALF_UP (kaufmännisch)
 */
public class BigDecimalWorksheet {

    // ══════════════════════════════════════════════════════════
    //  GUIDED (Übung 1-7)
    // ══════════════════════════════════════════════════════════

    // Übung 1: Erstelle einen BigDecimal mit dem Wert "19.50"
    // Verwende: new BigDecimal("...")
    public BigDecimal exercise01_createFromString() {
        throw new UnsupportedOperationException("TODO");
    }

    // Übung 2: Erstelle einen BigDecimal aus einem long-Wert
    // Verwende: BigDecimal.valueOf(value)
    public BigDecimal exercise02_createFromLong(long value) {
        throw new UnsupportedOperationException("TODO");
    }

    // Übung 3: Addiere zwei BigDecimals
    // Verwende: a.add(b)
    public BigDecimal exercise03_add(BigDecimal a, BigDecimal b) {
        throw new UnsupportedOperationException("TODO");
    }

    // Übung 4: Subtrahiere b von a
    // Verwende: a.subtract(b)
    public BigDecimal exercise04_subtract(BigDecimal a, BigDecimal b) {
        throw new UnsupportedOperationException("TODO");
    }

    // Übung 5: Multipliziere zwei BigDecimals
    // Verwende: a.multiply(b)
    public BigDecimal exercise05_multiply(BigDecimal a, BigDecimal b) {
        throw new UnsupportedOperationException("TODO");
    }

    // Übung 6: Dividiere a durch b mit 2 Nachkommastellen und HALF_UP Rundung
    // Verwende: a.divide(b, 2, RoundingMode.HALF_UP)
    public BigDecimal exercise06_divideWithRounding(BigDecimal a, BigDecimal b) {
        throw new UnsupportedOperationException("TODO");
    }

    // Übung 7: Prüfe ob a größer als b ist
    // Verwende: a.compareTo(b) > 0  —  NICHT a.equals(b)!
    public boolean exercise07_isGreaterThan(BigDecimal a, BigDecimal b) {
        throw new UnsupportedOperationException("TODO");
    }

    // ══════════════════════════════════════════════════════════
    //  SELF-GUIDED (Übung 8-10)
    // ══════════════════════════════════════════════════════════

    // Übung 8: Subtrahiere b von a, aber gib mindestens ZERO zurück (keine negativen Werte)
    public BigDecimal exercise08_subtractNonNegative(BigDecimal a, BigDecimal b) {
        throw new UnsupportedOperationException("TODO");
    }

    // Übung 9: Berechne einen Rabatt: amount * (100 - discountPercent) / 100
    // Ergebnis: 2 Nachkommastellen, HALF_UP
    public BigDecimal exercise09_applyDiscount(BigDecimal amount, int discountPercent) {
        // return amount.multiply(BigDecimal.valueOf(100 - discountPercent)).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
        throw new UnsupportedOperationException("TODO");
    }

    // Übung 10: Prüfe ob zwei BigDecimals den gleichen WERT haben
    // ACHTUNG: .equals() prüft auch Scale! Verwende .compareTo() == 0
    public boolean exercise10_isEqualByValue(BigDecimal a, BigDecimal b) {
        throw new UnsupportedOperationException("TODO");
    }
}
