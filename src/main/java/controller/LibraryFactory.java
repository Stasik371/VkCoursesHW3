package controller;

import booksFactory.BooksFactory;
import com.google.inject.Inject;
import exceptions.IncorrectArgumentException;

public class LibraryFactory {
    @Inject
    private BooksFactory booksFactory;

    public Library library(int capacity) throws IncorrectArgumentException {
        return new Library(capacity, booksFactory);
    }
}
