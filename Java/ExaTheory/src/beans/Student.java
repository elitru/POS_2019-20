package beans;

import java.io.Serializable;
import java.time.LocalDate;

public class Student implements Serializable {
    private String firstname;
    private String lastname;
    private LocalDate birthdate;
    private Gender gender;

    private static final long serialVersionUID = 123456L;

    public Student(String firstname, String lastname, LocalDate birthdate, Gender gender) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.gender = gender;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
