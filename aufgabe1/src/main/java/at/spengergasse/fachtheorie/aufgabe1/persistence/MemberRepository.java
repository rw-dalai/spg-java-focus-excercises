package at.spengergasse.fachtheorie.aufgabe1.persistence;

import at.spengergasse.fachtheorie.aufgabe1.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
