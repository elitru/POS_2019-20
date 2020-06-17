package at.eliastrummer.beans;

import java.time.LocalDate;


public class Filter {
    private String department;
    private boolean male;
    private boolean female;
    private LocalDate birthdateBefore;
    private int from;
    private int to;

    public Filter(String department, boolean male, boolean female, LocalDate birthdateBefore, int from, int to) {
        this.department = department;
        this.male = male;
        this.female = female;
        this.birthdateBefore = birthdateBefore;
        this.from = from;
        this.to = to;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public boolean isMale() {
        return male;
    }

    public void setMale(boolean male) {
        this.male = male;
    }

    public boolean isFemale() {
        return female;
    }

    public void setFemale(boolean female) {
        this.female = female;
    }

    public LocalDate getBirthdateBefore() {
        return birthdateBefore;
    }

    public void setBirthdateBefore(LocalDate birthdateBefore) {
        this.birthdateBefore = birthdateBefore;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }
}
