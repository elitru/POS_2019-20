package at.eliastrummer.database;

import at.eliastrummer.beans.Student;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class DBAcces {

    private static DBAcces theInstance = null;
    private Database db;

    public static DBAcces getInstance() {
        if (theInstance == null) {
            theInstance = new DBAcces();
        }
        return theInstance;
    }

    private DBAcces() {
        try {
            db = new Database();
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("Database problem - possible cause: DB-Driver not found");
        } catch (SQLException ex) {
            throw new RuntimeException("Database problem - possible cause: " + ex.toString());
        }
    }
    
    public boolean isConnected(){
        return db.isConnected();
    }
    
    public boolean connect(){
        try {
            db.connect();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public boolean disconnect(){
        try {
            db.disconnect();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public List<Student> getAllDatabaseEntries(){
        List<Student> students = new ArrayList<>();
        try {
            Statement stmt = db.getStatement();
            ResultSet rs = stmt.executeQuery(SQLStatements.GET_ALL_STUDENTS);
            
            while(rs.next()){
                students.add(Student.parse(rs));
            }
            
            db.releaseStatement(stmt);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return students;
    }
    
    public List<String> getAllClasses(){
        List<String> classes = new ArrayList<>();
        
        try {
            Statement stmt = db.getStatement();
            ResultSet rs = stmt.executeQuery(SQLStatements.GET_ALL_GRADES);
            
            while(rs.next()){
                classes.add(rs.getString("classname"));
            }
            
            db.releaseStatement(stmt);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return classes;
    }
    
    public Map<Integer, String> getAllClassesNamesWithID(){
        Map<Integer, String> result = new HashMap<>();
        
        try {
            Statement stmt = db.getStatement();
            ResultSet rs = stmt.executeQuery(SQLStatements.GET_ALL_GRADES);
            
            while(rs.next()){
                result.put(rs.getInt("classid"), rs.getString("classname"));
            }
            
            db.releaseStatement(stmt);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return result;
    }
    
    public boolean importStudents(List<Student> students){
        try {
            PreparedStatement ps = db.getPreparedStatement(StatementType.INSERT_STUDENT);
            List<String> classes = students
                    .stream()
                    .map(Student::getClassname)
                    .distinct()
                    .collect(Collectors.toList());
            
            if(!insertClasses(classes)){
                new RuntimeException("Error while inserting classes");
                return false;
            }
            
            Map<Integer, String> classesWithIDs = getAllClassesNamesWithID();
            
            for(Student s : students){
                ps.clearParameters();
                s.insert(ps, classesWithIDs).execute();
            }
            
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } catch(RuntimeException ex){
            ex.printStackTrace();
            return false;
        }
    }
    
    public boolean insertClasses(List<String> classes){
        try {
            PreparedStatement ps = db.getPreparedStatement(StatementType.INSERT_CLASS);
            
            for(String c : classes){
                Statement stmt = db.getStatement();
                ResultSet rs = stmt.executeQuery(String.format(SQLStatements.GET_CLASS, c));
                
                if(!rs.next()){
                    ps.clearParameters();
                    ps.setString(1, c);
                    ps.executeUpdate();
                }
                
                db.releaseStatement(stmt);
            }
            
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public boolean insertStudent(Student student){
        try {
            PreparedStatement ps = db.getPreparedStatement(StatementType.INSERT_STUDENT);
            
            if(!insertClasses(Arrays.asList(new String[]{student.getClassname()}))){
                new RuntimeException("Error while inserting classes");
                return false;
            }
            
            Map<Integer, String> classesWithIDs = getAllClassesNamesWithID();
            
            List<Student> studentsForClass = new ArrayList<>();
            Statement stmt = db.getStatement();
            
            ResultSet rs = stmt.executeQuery(String.format(SQLStatements.GET_ALL_STUDENTS_FOR_CLASS, student.getClassname()));
            
            while(rs.next()){
                studentsForClass.add(Student.parse(rs));
            }
            
            studentsForClass.add(student);
            studentsForClass.sort(Comparator.comparing(Student::getSurname).thenComparing(Student::getFirstname));
            
            int studentIdx = studentsForClass.indexOf(student);
            student.setCatno(studentIdx + 1);
            ps.clearParameters();
            student.insert(ps, classesWithIDs).execute();
            
            //update catnos
            
            for(int i = studentIdx + 1; i < studentsForClass.size(); i++){
                stmt.executeUpdate(String.format(SQLStatements.UPDATE_CATNO, (i + 1), studentsForClass.get(i).getStudentID()));
            }
            
            db.releaseStatement(stmt);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } catch(RuntimeException ex){
            ex.printStackTrace();
            return false;
        }
    }
    
    public boolean deleteStudents(){
        try {
            Statement stmt = db.getStatement();
            
            stmt.execute(SQLStatements.DELETE_STUDENTS);
            
            db.releaseStatement(stmt);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean deleteClasses(){
        try {
            Statement stmt = db.getStatement();
            
            stmt.execute(SQLStatements.DELETE_CLASSES);
            
            db.releaseStatement(stmt);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public String export(){
        try{
            String result = null;
            Statement stmt = db.getStatement();
            ResultSet rs = stmt.executeQuery(SQLStatements.GET_ALL_EXPORT);
            
            while(rs.next()){
                if(result == null){
                    result = Student.parse(rs).toString();
                    continue;
                }
                
                result += "\n" + Student.parse(rs).toString();
            }
            
            db.releaseStatement(stmt);
            return "Klasse;Familienname;Vorname;Geschlecht;Geburtsdatum\n" + result;
        }catch(SQLException e){
            e.printStackTrace();
            return "";
        }        
    }
}