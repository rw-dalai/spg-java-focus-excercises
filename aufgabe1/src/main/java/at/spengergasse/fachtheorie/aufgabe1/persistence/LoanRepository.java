package at.spengergasse.fachtheorie.aufgabe1.persistence;

import at.spengergasse.fachtheorie.aufgabe1.models.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {
}
