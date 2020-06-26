package at.eliastrummer.database;

import at.eliastrummer.beans.Department;
import at.eliastrummer.beans.DepartmentManagerInfo;
import at.eliastrummer.beans.Employee;
import at.eliastrummer.beans.EmployeeDepartmentInfo;
import at.eliastrummer.beans.Gender;
import at.eliastrummer.beans.Salary;
import at.eliastrummer.beans.Title;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class DBAccess {

    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("MM-dd-yyyy");
    private static DBAccess theInstance = null;
    private Database db;

    public static DBAccess getInstance() {
        if (theInstance == null) {
            theInstance = new DBAccess();
        }
        return theInstance;
    }

    private DBAccess() {
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
    
    public List<Employee> getEmployees(String departmentName, boolean male, boolean female, LocalDate birthdateBefore, int rowFrom, int rowTo) throws SQLException{
        List<Employee> result = new ArrayList<>();
        Map<String, Department> departments = new HashMap<>();
        List<Title> titles = new ArrayList<>();
        List<Salary> salaries = new ArrayList<>();
        
        String query = SQLStatements.GET_ENTRIES.replace("{department}", departmentName == null ? "" : departmentName);
        query = query.replace("{birthdate}", DTF.format(birthdateBefore));
        query = query.replace("{male}", Boolean.toString(male).toLowerCase());
        query = query.replace("{female}", Boolean.toString(female).toLowerCase());
        query = query.replace("{limit}", (rowTo - rowFrom) + "");
        query = query.replace("{offset}", rowFrom + "");
        
        Statement stmt = db.getStatement();
        
        //System.out.println(query);
        
        ResultSet rs = stmt.executeQuery(query);
        
        while(rs.next()){
            Title title = new Title(rs.getString("title"), rs.getDate("title_from").toLocalDate(), rs.getDate("title_to").toLocalDate());
            
            if(!titles.contains(title)){
                titles.add(title);
            }
            
            title = titles.get(titles.indexOf(title));
            
            Salary salary = new Salary(rs.getInt("emp_no"), rs.getInt("salary"), rs.getDate("sal_from").toLocalDate(), rs.getDate("sal_to").toLocalDate());
            
            if(!salaries.contains(salary)){
                salaries.add(salary);
            }
            
            salary = salaries.get(salaries.indexOf(salary));
            
            Department department = new Department(rs.getString("dept_no"), rs.getString("dept_name"));
            
            if(!departments.containsKey(department.getId())){
                departments.put(department.getId(), department);
            }
            
            department = departments.get(department.getId());
            
            String gender = rs.getString("gender");
            
            EmployeeDepartmentInfo departmentInfo = new EmployeeDepartmentInfo(rs.getDate("dep_from").toLocalDate(), rs.getDate("dep_to").toLocalDate(), department);
            
            Employee employee = new Employee(
                    rs.getInt("emp_no"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    gender.equalsIgnoreCase("m") ? Gender.MALE : Gender.FEMALE,
                    rs.getDate("birth_date").toLocalDate(),
                    rs.getDate("hire_date").toLocalDate(),
                    titles,
                    salaries,
                    new ArrayList<>()
            );
            
            if(result.contains(employee)){
                result.get(result.indexOf(employee)).getDepartmentInfo().add(departmentInfo);
            }else{
                employee.getDepartmentInfo().add(departmentInfo);
                result.add(employee);
            }
        }
        
        rs.close();
        db.releaseStatement(stmt);
        
        return result;
    }
    
    public List<String> getDepartments() throws SQLException{
        List<String> result = new ArrayList<>();
        
        Statement stmt = db.getStatement();
                
        ResultSet rs = stmt.executeQuery(SQLStatements.GET_DEPARTMENTS);
        
        while(rs.next()){
            result.add(rs.getString("dept_name"));
        }
        
        rs.close();
        db.releaseStatement(stmt);
        
        return result;
    }
    
    public List<DepartmentManagerInfo> getDepartmentManagers(String department) throws SQLException{
        List<DepartmentManagerInfo> result = new ArrayList<>();
        
        Statement stmt = db.getStatement();
                
        ResultSet rs = stmt.executeQuery(SQLStatements.GET_MANAGERS_FOR_DEPARTMENT.replace("{department}", department));
        
        while(rs.next()){
            DepartmentManagerInfo dmi = new DepartmentManagerInfo(
                    rs.getDate("from_date").toLocalDate(),
                    rs.getDate("to_date").toLocalDate(),
                    new Employee(rs.getInt("emp_no"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("gender").equalsIgnoreCase("m") ? Gender.MALE : Gender.FEMALE, rs.getDate("birth_date").toLocalDate(), rs.getDate("hire_date").toLocalDate()),
                    new Department(rs.getString("dept_no"), rs.getString("dept_name")));
            
            if(!result.contains(dmi)){
                result.add(dmi);
            }
        }
        
        rs.close();
        db.releaseStatement(stmt);
        
        return result;
    }
    
    public void updateEmployee(Employee employee) throws SQLException{
        Statement stmt = db.getStatement();
        
        String query = String.format(SQLStatements.UPDATE_EMPLOYEE, employee.getFirstname(), employee.getLastname(), employee.getHiredate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")), employee.getId());
        stmt.execute(query);
        
        db.releaseStatement(stmt);
    }
}