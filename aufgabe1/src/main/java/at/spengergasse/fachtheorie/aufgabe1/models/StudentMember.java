package at.spengergasse.fachtheorie.aufgabe1.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.ToString;

@Entity
@DiscriminatorValue("STUDENT")
@Getter
@ToString(callSuper = true)
public class StudentMember extends Member {

    private String studentId;

    private int discountPercent;

    protected StudentMember() {}

    public StudentMember(String email, String name, String studentId, int discountPercent) {
        super(email, name);
        this.studentId = studentId;
        this.discountPercent = discountPercent;
    }
}
