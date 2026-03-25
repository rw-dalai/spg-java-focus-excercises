package at.spengergasse.fachtheorie.aufgabe3.persistence;

import at.spengergasse.fachtheorie.aufgabe3.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * ══════════════════════════════════════════════════════════════
 *  MAGIC FINDER WORKSHEET — MemberRepository
 * ══════════════════════════════════════════════════════════════
 */
public interface MemberRepository extends JpaRepository<Member, Long> {

    // ══════════════════════════════════════════════════════════
    //  LEICHT
    // ══════════════════════════════════════════════════════════

    // Aufgabe 4: Finde ein Mitglied nach E-Mail
    // TODO

    // Aufgabe 5: Prüfe ob eine E-Mail bereits vergeben ist
    // TODO
}
