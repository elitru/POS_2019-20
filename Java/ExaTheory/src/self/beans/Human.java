package self.beans;

import beans.Gender;

import java.time.LocalDate;
import java.util.Objects;

public class Human extends Entity {
    private Gender gender;

    public Human(String name, LocalDate birthdate, Gender gender) {
        super(name, birthdate);
        this.gender = gender;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "HUMAN => " + getName() + " -> " + gender.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Human human = (Human) o;
        return gender == human.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(gender);
    }
}
