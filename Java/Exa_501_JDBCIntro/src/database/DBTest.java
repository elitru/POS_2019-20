package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBTest {

    private Connection connection;

    public DBTest() throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
    }

    public void connect(String dbName) throws SQLException {
        String url = "jdbc:postgresql://localhost/" + dbName;
        String username = "postgres";
        String password = "admin";

        connection = DriverManager.getConnection(url, username, password);
    }

    public void disconnect() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    public void createDatabase(String dbName) throws SQLException {
        String sqlQuery = "CREATE DATABASE " + dbName.toLowerCase();
        Statement stmt = connection.createStatement();
        stmt.execute(sqlQuery);
        stmt.close();
    }

    public void createTable(String table) throws SQLException {
        String sqlQuery = "CREATE TABLE students (\n"
                + "    student_id serial PRIMARY KEY,\n"
                + "    cat_nr INTEGER NOT NULL,\n"
                + "    firstname VARCHAR(100) NOT NULL,\n"
                + "    lastname VARCHAR(100) NOT NULL,\n"
                + "    birthdate DATE NOT NULL\n"
                + ");";
        
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(sqlQuery);
        stmt.close();
    }

    public static void main(String[] args) {
        DBTest test = null;

        try {
            test = new DBTest();
            test.connect("studentdb");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            test.createTable("students");
        } catch (SQLException ex) {
            Logger.getLogger(DBTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
