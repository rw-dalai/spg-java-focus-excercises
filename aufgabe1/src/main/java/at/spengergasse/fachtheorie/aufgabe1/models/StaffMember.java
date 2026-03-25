package at.spengergasse.fachtheorie.aufgabe1.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.ToString;

@Entity
@DiscriminatorValue("STAFF")
@Getter
@ToString(callSuper = true)
public class StaffMember extends Member {

    private String department;

    protected StaffMember() {}

    public StaffMember(String email, String name, String department) {
        super(email, name);
        this.department = department;
    }
}
