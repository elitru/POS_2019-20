package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBTest {

    private Connection connection;
    
    public DBTest() throws ClassNotFoundException{
        Class.forName("org.postgresql.Driver");
    }
    
    public void connect(String dbName) throws SQLException{
        String url = "jdbc:postgresql://localhost/" + dbName;
        String username = "postgres";
        String password = "admin";
        
        connection = DriverManager.getConnection(url, username, password);
    }
    
    public void disconnect() throws SQLException{
        if(connection != null){
            connection.close();
        }
    }
    
    public static void main(String[] args){
        try {
            DBTest test = new DBTest();
            test.connect("postgres");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
