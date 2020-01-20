package at.eliastrummer.exa_206_pethome.beans;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Dog extends Pet implements Serializable {

    private Size size;
    private static final long serialVersionUID = 01L;

    public Dog(String name, LocalDate dateOfBirth, Gender gender, Size size) {
        super(name, dateOfBirth, gender);
        this.size = size;
    }

    public Dog(String[] param){
        super(param[1], LocalDate.parse(param[3], DateTimeFormatter.ofPattern("MM/dd/yyyy")), Gender.valueOf(param[2].toUpperCase()));
        this.size = Size.valueOf(param[4].equalsIgnoreCase("L") ? "LARGE" : param[4].equalsIgnoreCase("M") ? "MEDIUM" : "SMALL");
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }
}
