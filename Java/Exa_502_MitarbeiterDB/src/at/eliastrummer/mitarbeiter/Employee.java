package at.eliastrummer.mitarbeiter;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Employee {
    private int persNumber;
    private String lastname;
    private String firstname;
    private LocalDate birthdate;
    private BigDecimal salary;
    private int departmentNumber;
    private String gender;
    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public Employee() {
        
    }

    public Employee(int pers_nr, String name, String vorname,
            LocalDate geb_datum, BigDecimal gehalt, int abt_nr,
            String geschlecht) {
        this.persNumber = pers_nr;
        this.lastname = name;
        this.firstname = vorname;
        this.birthdate = geb_datum;
        this.salary = gehalt;
        this.departmentNumber = abt_nr;
        this.gender = geschlecht;
    }
    
     public Employee(String line) {
        String[] split = line.split(";");
        persNumber = Integer.parseInt(split[0]);
        lastname = split[1];
        firstname = split[2];
        birthdate = LocalDate.parse((String) split[3], DTF);
        salary = BigDecimal.valueOf(Double.parseDouble(split[4]));
        departmentNumber = Integer.parseInt(split[5]);
        gender = split[6].charAt(0)+"";
    }

    public Employee(ResultSet set) throws SQLException {
        this.persNumber = set.getInt("pers_nr");
        this.lastname = set.getString("name");
        this.firstname = set.getString("vorname");
        this.birthdate = set.getDate("geb_datum").toLocalDate();
        this.salary = set.getBigDecimal("gehalt");
        this.departmentNumber = set.getInt("abt_nr");
        this.gender = set.getString("geschlecht");
    }

    public int getPersNumber() {
        return persNumber;
    }

    public void setPersNumber(int PersNumber) {
        this.persNumber = PersNumber;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public int getDepartmentNumber() {
        return departmentNumber;
    }

    public void setDepartmentNumber(int departmentNumber) {
        this.departmentNumber = departmentNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.persNumber;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Employee other = (Employee) obj;
        if (!Objects.equals(this.lastname, other.lastname)) {
            return false;
        }
        if (!Objects.equals(this.firstname, other.firstname)) {
            return false;
        }
        if (!Objects.equals(this.birthdate, other.birthdate)) {
            return false;
        }
        return true;
    }

    

    @Override
    public String toString() {
        return String.format("%d | %-15s %-15s | %.2f € | %s | Department: %d | %s", persNumber, firstname, lastname, salary.doubleValue(), DTF.format(birthdate), departmentNumber, gender);
    }
}
