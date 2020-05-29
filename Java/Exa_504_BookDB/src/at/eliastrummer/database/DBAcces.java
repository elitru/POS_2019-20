package at.eliastrummer.database;

import at.eliastrummer.beans.Book;
import at.eliastrummer.util.MergeReminder;
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
    
    public List<Book> getBooks(String title, String author, String genre, String publisher){
        try {
            List<Book> result = new ArrayList<>();
            
            Statement stmt = db.getStatement();
            
            //apply filters
            String query = SQLStatements.GET_BOOKS.replace("{title}", title);
            query = query.replace("{genre}", genre);
            query = query.replace("{publisher}", publisher);
            query = query.replace("{author_lastname}", author);
            query = query.replace("{author_firstname}", author);
                                    
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next()){
                result.add(new Book(rs));
            }
            
            db.releaseStatement(stmt);
            
            return merge(result);
        } catch (SQLException ex) {
            Logger.getLogger(DBAcces.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<Book>();
        }
    }
    
    private List<Book> merge(List<Book> books){
        Map<Book, MergeReminder> bookData = new HashMap<>();
        
        for(Book b : books){
            if(bookData.containsKey(b)){
                bookData.get(b).getAuthors().addAll(b.getAuthors());
                bookData.get(b).getGenres().addAll(b.getGenres());
            }else{
                bookData.put(b, new MergeReminder(b.getAuthors(), b.getGenres()));
            }
        }
                
        return bookData
                .entrySet()
                .stream()
                .map(e -> {
                    Book book = e.getKey();
                    
                    book.setAuthors(e.getValue().getAuthors().stream().distinct().collect(Collectors.toList()));
                    book.setGenres(e.getValue().getGenres().stream().distinct().collect(Collectors.toList()));
                    
                    return book;
                })
                .sorted(Comparator.comparing((b) -> b.getTitle()))
                .collect(Collectors.toList());
    }
    
    public List<String> getAllGenres(){
        try {
            List<String> result = new ArrayList<>();
            
            Statement stmt = db.getStatement();
            ResultSet rs = stmt.executeQuery("SELECT * from genres ORDER BY genre;");
            
            while(rs.next()){
                result.add(rs.getString("genre"));
            }
            
            db.releaseStatement(stmt);
            
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(DBAcces.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<String>();
        }
    }
    
    public List<String> getAllPublishers(){
        try {
            List<String> result = new ArrayList<>();
            
            Statement stmt = db.getStatement();
            ResultSet rs = stmt.executeQuery("SELECT * from publishers ORDER BY name;");
            
            while(rs.next()){
                result.add(rs.getString("name"));
            }
            
            db.releaseStatement(stmt);
            
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(DBAcces.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<String>();
        }
    }
    
    public List<String> getGenres(String publisher){
        try {
            List<String> result = new ArrayList<>();
            
            Statement stmt = db.getStatement();
            String query = SQLStatements.GET_GENRES.replace("{publisher}", publisher);
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next()){
                result.add(rs.getString("genre"));
            }
            
            db.releaseStatement(stmt);
                        
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(DBAcces.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<String>();
        }
    }
    
    public List<String> getPublisher(String publisher){
        try {
            List<String> result = new ArrayList<>();
            
            Statement stmt = db.getStatement();
            ResultSet rs = stmt.executeQuery(SQLStatements.GET_PUBLISHERS.replace("{genre}", publisher));
            
            while(rs.next()){
                result.add(rs.getString("name"));
            }
            
            db.releaseStatement(stmt);
            
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(DBAcces.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<String>();
        }
    }
}