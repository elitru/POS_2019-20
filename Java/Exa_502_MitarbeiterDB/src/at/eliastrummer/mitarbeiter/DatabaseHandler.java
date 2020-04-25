package at.eliastrummer.mitarbeiter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler {
    public static int nextID = 0;
    public static List<Employee> employees = new ArrayList<>();
    
    private final String username;
    private final String password;
    
    private Connection connection;

    public DatabaseHandler(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public void connect() throws SQLException{
        if(isConnected()){
            connection.close();
        }
        
        String url = "jdbc:postgresql://localhost/";
        connection = DriverManager.getConnection(url, username, password);
    }
    
    public void connect(String database) throws SQLException{
        if(isConnected()){
            connection.close();
        }
        
        String url = "jdbc:postgresql://localhost/" + database;
        connection = DriverManager.getConnection(url, username, password);
    }
    
    public void disconnect() throws SQLException{
        if(isConnected()){
            connection.close();
            connection = null;
        }
    }
    
    public boolean createDB(){
        try {
            String sqlQuery = "CREATE DATABASE mitarbeiterdb";
            Statement stmt = connection.createStatement();
            stmt.execute(sqlQuery);
            stmt.close();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean createTable(){
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(SQLStatements.CREATE_TABLE);
            stmt.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<Employee> getEmplyoeesFromDepartment(int department) throws SQLException{
        List<Employee> list = new ArrayList<>();

        PreparedStatement ps = connection.prepareStatement(SQLStatements.EMPLYOEES_FOR_DEPARTMEN);
        ResultSet result = ps.executeQuery();
        while (result.next()) {
            list.add(new Employee(result));
        }
        
        result.close();
        ps.close();

        return list;
    }
    
    public double getAverageSalary(char gender){
        try{
            PreparedStatement ps = connection.prepareStatement(SQLStatements.AVERAGE_SALARY);
            ps.setString(1, gender + "");
            ResultSet result = ps.executeQuery();
            
            if(result.next()){
                return result.getBigDecimal(1).doubleValue();
            }
            
            result.close();
            ps.close();
            
            return 0;
        }catch(SQLException e){
            return -1;
        }
    }
    
    public Employee insertEmployee(Employee employee){
        try{
            int persNr = getNextID();
            PreparedStatement ps = connection.prepareStatement(SQLStatements.INSERT_EMPLOYEE);
            ps.setInt(1, persNr);
            ps.setString(2, employee.getLastname());
            ps.setString(3, employee.getFirstname());
            ps.setDate(4, java.sql.Date.valueOf(employee.getBirthdate()));
            ps.setBigDecimal(5, employee.getSalary());
            ps.setInt(6, employee.getDepartmentNumber());
            ps.setString(7, employee.getGender());
            ps.execute();
            employee.setPersNumber(persNr);
            ps.close();
            return employee;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }        
    }
    
    public boolean removeEmployee(Employee employee){
        try{
            PreparedStatement ps = connection.prepareStatement(SQLStatements.DELETE_EMPLOYEE);
            ps.setInt(1, employee.getPersNumber());
            ps.execute();
            ps.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }        
    }
    
    public boolean deleteDatabase(){
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(SQLStatements.DROP_DATABASE);
            stmt.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean dropTable(){
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(SQLStatements.DROP_TABLE);
            stmt.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public int getNextID(){
        try{
            Statement stmt = connection.createStatement();
            ResultSet result = stmt.executeQuery(SQLStatements.NEXT_ID);
            
            if(result.next()){
                return result.getInt(1) + 1;
            }
            result.close();
            stmt.close();
            
            return 1;
        }catch(SQLException e){
            return -1;
        }
    }
    
    public boolean clearTable(){
        try{
            Statement stmt = connection.createStatement();
            stmt.execute("DELETE FROM mitarbeiter;");
            stmt.close();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean isConnected(){
        try {
            return connection != null && !connection.isClosed();
        } catch (SQLException ex) {
            return false;
        }
    }
    
    public List<Employee> getAllEmployees(){
        try (Statement stmt = connection.createStatement()) {
            List<Employee> resultEmployees = new ArrayList<>();
            ResultSet result = stmt.executeQuery("SELECT * FROM mitarbeiter;");
            
            while(result.next()){
                Employee emp = new Employee(result);
                resultEmployees.add(emp);
            }
            
            employees.clear();
            employees.addAll(resultEmployees);
            
            result.close();
            stmt.close();
            
            return resultEmployees;
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }
}