package at.spengergasse.fachtheorie.aufgabe2.persistence;

import at.spengergasse.fachtheorie.aufgabe2.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
