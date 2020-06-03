package at.eliastrummer.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
}