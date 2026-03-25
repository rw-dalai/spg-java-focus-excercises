package at.spengergasse.fachtheorie.aufgabe1;

import java.math.BigDecimal;
import java.time.LocalDate;

public record LoanDto(
    Long id,
    String bookTitle,
    String memberEmail,
    LocalDate loanDate,
    LocalDate returnDate,
    BigDecimal fee
) {
}
