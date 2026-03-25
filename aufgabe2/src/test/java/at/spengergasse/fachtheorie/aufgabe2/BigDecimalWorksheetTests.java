package at.spengergasse.fachtheorie.aufgabe2;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class BigDecimalWorksheetTests {

    private final BigDecimalWorksheet worksheet = new BigDecimalWorksheet();

    @Test
    void exercise01_createFromString() {
        assertThat(worksheet.exercise01_createFromString())
                .isEqualByComparingTo(new BigDecimal("19.50"));
    }

    @Test
    void exercise02_createFromLong() {
        assertThat(worksheet.exercise02_createFromLong(42))
                .isEqualByComparingTo(new BigDecimal("42"));
    }

    @Test
    void exercise03_add() {
        assertThat(worksheet.exercise03_add(new BigDecimal("10.50"), new BigDecimal("3.25")))
                .isEqualByComparingTo(new BigDecimal("13.75"));
    }

    @Test
    void exercise04_subtract() {
        assertThat(worksheet.exercise04_subtract(new BigDecimal("10.50"), new BigDecimal("3.25")))
                .isEqualByComparingTo(new BigDecimal("7.25"));
    }

    @Test
    void exercise05_multiply() {
        assertThat(worksheet.exercise05_multiply(new BigDecimal("2.50"), new BigDecimal("10")))
                .isEqualByComparingTo(new BigDecimal("25.00"));
    }

    @Test
    void exercise06_divideWithRounding() {
        assertThat(worksheet.exercise06_divideWithRounding(new BigDecimal("10.00"), new BigDecimal("3")))
                .isEqualByComparingTo(new BigDecimal("3.33"));
    }

    @Test
    void exercise07_isGreaterThan() {
        assertThat(worksheet.exercise07_isGreaterThan(new BigDecimal("10"), new BigDecimal("5"))).isTrue();
        assertThat(worksheet.exercise07_isGreaterThan(new BigDecimal("5"), new BigDecimal("10"))).isFalse();
    }

    @Test
    void exercise08_subtractNonNegative() {
        assertThat(worksheet.exercise08_subtractNonNegative(new BigDecimal("3"), new BigDecimal("5")))
                .isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(worksheet.exercise08_subtractNonNegative(new BigDecimal("5"), new BigDecimal("3")))
                .isEqualByComparingTo(new BigDecimal("2"));
    }

    @Test
    void exercise09_applyDiscount() {
        assertThat(worksheet.exercise09_applyDiscount(new BigDecimal("25.00"), 20))
                .isEqualByComparingTo(new BigDecimal("20.00"));
    }

    @Test
    void exercise10_isEqualByValue() {
        assertThat(worksheet.exercise10_isEqualByValue(new BigDecimal("1.0"), new BigDecimal("1.00"))).isTrue();
    }
}
