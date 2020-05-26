package at.eliastrummer.util;

import at.eliastrummer.beans.Author;
import java.util.List;

public class MergeReminder {
    private List<Author> authors;
    private List<String> genres;

    public MergeReminder(List<Author> authors, List<String> genres) {
        this.authors = authors;
        this.genres = genres;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }
}
