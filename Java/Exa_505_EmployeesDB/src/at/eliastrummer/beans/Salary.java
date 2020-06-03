package at.eliastrummer.beans;

import java.time.LocalDate;

public class Salary {
    private int salary;
    private LocalDate from;
    private LocalDate to;

    public Salary(int salary, LocalDate from, LocalDate to) {
        this.salary = salary;
        this.from = from;
        this.to = to;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public LocalDate getFrom() {
        return from;
    }

    public void setFrom(LocalDate from) {
        this.from = from;
    }

    public LocalDate getTo() {
        return to;
    }

    public void setTo(LocalDate to) {
        this.to = to;
    }
}
