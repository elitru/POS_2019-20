package at.eliastrummer.exa_206_pethome.beans;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Dog extends Pet {

    private Size size;

    public Dog(String name, LocalDate dateOfBirth, Gender gender, Size size) {
        super(name, dateOfBirth, gender);
        this.size = size;
    }

    public Dog(String[] param){
        super(param[1], LocalDate.parse(param[2], DateTimeFormatter.ofPattern("dd/MM/yyyy")), Gender.valueOf(param[3].toUpperCase()));
        this.size = Size.valueOf(param[4].toUpperCase());
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }
}
