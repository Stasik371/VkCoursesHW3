package booksFactory;
import models.Book;
import java.util.Collection;

public interface BooksFactory {
    public Collection<Book> books();

}
