package at.spengergasse.fachtheorie.aufgabe4.persistence;

import at.spengergasse.fachtheorie.aufgabe4.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
