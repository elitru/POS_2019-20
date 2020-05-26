package at.eliastrummer.beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Book {
    private int id;
    private String title;
    private int totalPages;
    private double rating;
    private String isbn;
    private LocalDate publishedOn;
    private String publisher;
    private List<String> genres;
    private List<Author> authors;

    public Book(int id, String title, int totalPages, double rating, String isbn, LocalDate publishedOn, String publisher, List<String> genres, List<Author> authors) {
        this.id = id;
        this.title = title;
        this.totalPages = totalPages;
        this.rating = rating;
        this.isbn = isbn;
        this.publishedOn = publishedOn;
        this.publisher = publisher;
        this.genres = genres;
        this.authors = authors;
    }
    
    public Book(ResultSet rs) throws SQLException{
        this(
            rs.getInt("book_id"),
            rs.getString("title"),
            rs.getInt("total_pages"),
            rs.getDouble("rating"),
            rs.getString("isbn"),
            rs.getDate("published_date").toLocalDate(),
            rs.getString("publisher"),
            initGenre(rs.getString("genre")),
            initAuthor(new Author(rs.getString("authorFirstname"), rs.getString("authorMiddlename"), rs.getString("authorLastname")))
        );
    }
    
    private static List<String> initGenre(String genre){
        List<String> genres = new ArrayList<>();
        genres.add(genre);
        
        return genres;
    }
    
    private static List<Author> initAuthor(Author a){
        List<Author> authors = new ArrayList<>();
        authors.add(a);
        
        return authors;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public LocalDate getPublishedOn() {
        return publishedOn;
    }

    public void setPublishedOn(LocalDate publishedOn) {
        this.publishedOn = publishedOn;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Book other = (Book) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return title;
    }
}
