package at.eliastrummer.beans;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Map.Entry;

public class Student {

    private int studentID;
    private String classname;
    private int catno;
    private String firstname;
    private String surname;
    private String gender;
    private LocalDate dateOfBirth;
    
    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public Student(int studentID, String classname, int catno, String firstname, String surname, String gender, LocalDate dateOfBirth) {
        this.studentID = studentID;
        this.classname = classname;
        this.catno = catno;
        this.firstname = firstname;
        this.surname = surname;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public int getCatno() {
        return catno;
    }

    public void setCatno(int catno) {
        this.catno = catno;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public static Student parse(ResultSet rs) throws SQLException {
        int studentID = rs.getInt("studentid");
        String classname = rs.getString("classname");
        int catno = rs.getInt("catno");
        String firstname = rs.getString("firstname");
        String surname = rs.getString("surname");
        String gender = rs.getString("gender");
        LocalDate dateOfBirth = rs.getDate("dateofbirth").toLocalDate();
        
        return new Student(studentID, classname, catno, firstname, surname, gender, dateOfBirth);
    }
    
    public static Student parse(String param, int catno){
        String[] s = param.split(";");
        int studentID = -1;
        String classname = s[0];
        String firstname = s[2];
        String surname = s[1];
        String gender = s[3];
        LocalDate dateOfBirth = LocalDate.parse(s[4], DTF);
        
        return new Student(studentID, classname, catno, firstname, surname, gender, dateOfBirth);
    }
    
    public PreparedStatement insert(PreparedStatement ps, Map<Integer, String> classes) throws SQLException{
        ps.setInt(1, catno);
        ps.setString(2, firstname);
        ps.setString(3, surname);
        ps.setString(4, gender);
        ps.setDate(5, java.sql.Date.valueOf(dateOfBirth));
        ps.setInt(6, getClassIDByName(classname, classes));
        return  ps;
    }
    
    private int getClassIDByName(String classname, Map<Integer, String> classes){
        for(Entry<Integer, String> e : classes.entrySet()){
            if(e.getValue().equals(classname)){
                return e.getKey();
            }
        }
        
        return -1;
    }

    @Override
    public String toString() {
        return String.format("%s;%s;%s;%s;%s", classname.toUpperCase(), surname.toUpperCase(), firstname, gender.charAt(0) + "", DTF.format(dateOfBirth));
    }
}
