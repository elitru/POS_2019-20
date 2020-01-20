package self.beans;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Alien extends Entity {
    private int amountChildren;

    public Alien(String name, LocalDate birthdate, int amountChildren) {
        super(name, birthdate);
        this.amountChildren = amountChildren;
    }

    public int getAmountChildren() {
        return amountChildren;
    }

    public void setAmountChildren(int amountChildren) {
        this.amountChildren = amountChildren;
    }

    @Override
    public String toString() {
        return "ALIEN => " + getName() + " -> " + amountChildren;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alien alien = (Alien) o;
        return amountChildren == alien.amountChildren;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amountChildren);
    }
}
