package at.spengergasse.fachtheorie.aufgabe4.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "member")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "member_type")
@Getter
@ToString(callSuper = true)
public abstract class Member extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "member")
    @ToString.Exclude
    private final List<Loan> loans = new ArrayList<>();

    protected Member() {}

    protected Member(String email, String name) {
        this.email = email;
        this.name = name;
    }
}
