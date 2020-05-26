package at.eliastrummer.gui;

import at.eliastrummer.beans.Book;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;

public class BookModel extends AbstractListModel {

    private List<Book> books = new ArrayList<>();
    
    @Override
    public int getSize() {
        return books.size();
    }

    @Override
    public Object getElementAt(int index) {
        return books.get(index);
    }
    
    public void addAll(List<Book> books){
        this.books.addAll(books);
        this.fireContentsChanged(this, 0, books.size());
    }
    
    public void clear(){
        this.books.clear();
        this.fireContentsChanged(this, 0, books.size());
    }

    public List<Book> getBooks() {
        return books;
    }
}
