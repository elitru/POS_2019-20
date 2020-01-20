package self.beans;

import java.time.LocalDate;
import java.util.Objects;

public class Entity {
    private String name;
    private LocalDate birthdate;

    public Entity(String name, LocalDate birthdate) {
        this.name = name;
        this.birthdate = birthdate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return Objects.equals(name, entity.name) &&
                Objects.equals(birthdate, entity.birthdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthdate);
    }
}
