package at.eliastrummer.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {

    private String dbURL;
    private String dbDriver;
    private String dbUsername;
    private String dbPassword;
    private Connection connection;
    private DBCachedConnection cachedConnection;

    public Database() throws ClassNotFoundException, SQLException {
        loadProperties();
        Class.forName(dbDriver);
        //connect();
    }

    public void connect() throws SQLException {
        if (connection != null) {
            connection.close();
        }
        connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        cachedConnection = new DBCachedConnection(connection);
    }

    public void disconnect() throws SQLException {
        if (connection != null) {
            cachedConnection.close();
            connection.close();
            cachedConnection = null;
        }
    }

    public void loadProperties() {
        dbURL = DBProperties.getPropertyValue("url");
        dbDriver = DBProperties.getPropertyValue("driver");
        dbUsername = DBProperties.getPropertyValue("username");
        dbPassword = DBProperties.getPropertyValue("password");
    }

    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() throws SQLException {
        if (connection == null || cachedConnection == null) {
            throw new RuntimeException("database connection error");
        }
        return cachedConnection.getStatement();
    }

    public void releaseStatement(Statement statement) {
        if (connection == null || cachedConnection == null) {
            throw new RuntimeException("database connection error");
        }
        cachedConnection.releaseStatement(statement);
    }
    
    public static void main(String[] args) {
        try {
            new Database().connect();
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    public boolean isConnected() {
        try {
            return connection != null && !connection.isClosed();
        } catch (SQLException ex) {
            return false;
        }
    }
    
    public PreparedStatement getPreparedStatement(StatementType type) {
        return cachedConnection.getPreparedStatement(type);
    }
}
