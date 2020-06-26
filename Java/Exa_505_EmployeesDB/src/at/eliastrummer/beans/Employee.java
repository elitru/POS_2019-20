package at.eliastrummer.beans;

import at.eliastrummer.bl.TableDisplayObject;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Employee extends TableDisplayObject {

    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public static final String[] COLUMN_TILES = new String[]{
        "Lastname",
        "Firstname",
        "Gender",
        "Birthdate",
        "Hiredate"
    };

    private int id;
    private String firstname;
    private String lastname;
    private Gender gender;
    private LocalDate birthdate;
    private LocalDate hiredate;
    private List<Title> titles;
    private List<Salary> salaries;
    private List<EmployeeDepartmentInfo> departmentInfo;

    public Employee(int id, String firstname, String lastname, Gender gender, LocalDate birthdate, LocalDate hiredate, List<Title> titles, List<Salary> salaries, List<EmployeeDepartmentInfo> departmentInfo) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.birthdate = birthdate;
        this.hiredate = hiredate;
        this.titles = titles;
        this.salaries = salaries;
        this.departmentInfo = departmentInfo;
    }

    public Employee(int id, String firstname, String lastname, Gender gender, LocalDate birthdate, LocalDate hiredate) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.birthdate = birthdate;
        this.hiredate = hiredate;
    }

    public Employee() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public LocalDate getHiredate() {
        return hiredate;
    }

    public void setHiredate(LocalDate hiredare) {
        this.hiredate = hiredare;
    }

    public List<Title> getTitles() {
        return titles;
    }

    public void setTitles(List<Title> titles) {
        this.titles = titles;
    }

    public List<Salary> getSalaries() {
        return salaries;
    }

    public void setSalaries(List<Salary> salaries) {
        this.salaries = salaries;
    }

    public List<EmployeeDepartmentInfo> getDepartmentInfo() {
        return departmentInfo;
    }

    public void setDepartmentInfo(List<EmployeeDepartmentInfo> departmentInfo) {
        this.departmentInfo = departmentInfo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
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
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String getValueForColumn(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return lastname;
            case 1:
                return firstname;
            case 2:
                return gender.toString().toLowerCase();
            case 3:
                return DTF.format(birthdate);
            case 4:
                return DTF.format(hiredate);
            default:
                return "not_found";
        }
    }

    @Override
    public boolean setValueForColumn(int columnIndex, Object value) {
        try {
            switch (columnIndex) {
                case 0:
                    lastname = value.toString();
                    break;
                case 1:
                    firstname = value.toString();
                    break;
                case 4:
                    hiredate = LocalDate.parse(value.toString(), DTF);
                    break;
                default:
                    break;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
