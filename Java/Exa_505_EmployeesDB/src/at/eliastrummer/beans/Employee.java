package at.eliastrummer.beans;

import java.time.LocalDate;

public class Employee {
    private String firstname;
    private String lastname;
    private Gender gender;
    private LocalDate birthdate;
    private LocalDate hiredate;
    private Title title;
    private Salary salary;
    private EmployeeDepartmentInfo departmentInfo;
    private DepartmentManager departmentManager;

    public Employee(String firstname, String lastname, Gender gender, LocalDate birthdate, LocalDate hiredate, Title title, Salary salary, EmployeeDepartmentInfo departmentInfo, DepartmentManager departmentManager) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.birthdate = birthdate;
        this.hiredate = hiredate;
        this.title = title;
        this.salary = salary;
        this.departmentInfo = departmentInfo;
        this.departmentManager = departmentManager;
    }
        
    public Employee(String firstname, String lastname, Gender gender, LocalDate birthdate, LocalDate hiredate) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.birthdate = birthdate;
        this.hiredate = hiredate;
    }

    public Employee() {
        
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

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public Salary getSalary() {
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }

    public EmployeeDepartmentInfo getDepartmentInfo() {
        return departmentInfo;
    }

    public void setDepartmentInfo(EmployeeDepartmentInfo departmentInfo) {
        this.departmentInfo = departmentInfo;
    }

    public DepartmentManager getDepartmentManager() {
        return departmentManager;
    }

    public void setDepartmentManager(DepartmentManager departmentManager) {
        this.departmentManager = departmentManager;
    }
}
