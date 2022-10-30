package controller;

import booksFactory.BooksFactory;
import com.google.inject.Inject;

public class LibraryFactory {
    @Inject
    private BooksFactory booksFactory;

    public Library library(int capacity)  {
        return new Library(capacity, booksFactory);
    }
}
