package at.eliastrummer.beans;

import java.time.LocalDate;
import java.util.Objects;

public class Salary {

    private int employeeId;
    private int salary;
    private LocalDate from;
    private LocalDate to;

    public Salary(int employeeId, int salary, LocalDate from, LocalDate to) {
        this.employeeId = employeeId;
        this.salary = salary;
        this.from = from;
        this.to = to;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.employeeId;
        hash = 37 * hash + this.salary;
        hash = 37 * hash + Objects.hashCode(this.from);
        hash = 37 * hash + Objects.hashCode(this.to);
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
        final Salary other = (Salary) obj;
        if (this.employeeId != other.employeeId) {
            return false;
        }
        if (this.salary != other.salary) {
            return false;
        }
        if (!Objects.equals(this.from, other.from)) {
            return false;
        }
        if (!Objects.equals(this.to, other.to)) {
            return false;
        }
        return true;
    }

}
