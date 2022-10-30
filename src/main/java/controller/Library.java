package controller;

import booksFactory.BooksFactory;
import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;
import models.Book;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

@Getter
@Setter
public final class Library {

    private final Book[] cells;
    private final int amountOfCells;

    public Library(int amountOfBooks, @NotNull BooksFactory booksFactory)  throws IllegalArgumentException{
        var books = ((ArrayList<Book>) booksFactory.books());
        this.amountOfCells = amountOfBooks;
        if (amountOfBooks < books.size()) {
            throw new IllegalArgumentException("Not enough cells");
        }
        this.cells = new Book[amountOfCells];
        for (int i = 0; i < books.size(); i++) {
            this.cells[i] = books.get(i);
        }
    }

    public @NotNull
    Book getBookFromCell(int index) {
        if (index > amountOfCells) throw new IllegalArgumentException("Cell index greater than library capacity");
        if (checkBook(index)) throw new IllegalArgumentException("Cell is empty");
        Book bufBook = cells[index];
        cells[index] = null;
        System.out.print("Book was taken: ");
        System.out.println(getJSONBook(bufBook, index));
        return bufBook;
    }

    public void addBookToCell(@NotNull Book book) {
        int indexOfFirstEmptyCell = -1;
        for (int i = 0; i < amountOfCells; i++) {
            if (cells[i] == null) {
                indexOfFirstEmptyCell = i;
                break;
            }
        }
        if (indexOfFirstEmptyCell == -1) throw new IllegalArgumentException("No empty cells");
        cells[indexOfFirstEmptyCell] = book;
    }

    public boolean checkBook(int index) {
        return cells[index] == null;
    }

    public String getJSONBook(@NotNull Book book, int index) {
        String authorName = book.getAuthor().getName();
        String bookName = book.getName();
        Gson gson = new Gson();
        String jsonBook = "Number of cell: " + index + " " + gson.toJson(book);
        return jsonBook;

    }

    public void printAllOfBooks() {
        for (int i = 0; i < amountOfCells; i++) {
            if (cells[i] == null) {
                System.out.println("Number of cell: " + i + " Cell is empty");
            } else {
                System.out.println(getJSONBook(cells[i], i));
            }
        }
    }

}
