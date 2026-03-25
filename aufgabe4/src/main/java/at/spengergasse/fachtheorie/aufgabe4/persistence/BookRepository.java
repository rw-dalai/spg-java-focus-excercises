package at.spengergasse.fachtheorie.aufgabe4.persistence;

import at.spengergasse.fachtheorie.aufgabe4.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
