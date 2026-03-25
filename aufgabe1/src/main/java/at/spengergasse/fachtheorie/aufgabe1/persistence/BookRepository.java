package at.spengergasse.fachtheorie.aufgabe1.persistence;

import at.spengergasse.fachtheorie.aufgabe1.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
