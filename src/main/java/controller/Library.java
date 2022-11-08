package controller;

import booksFactory.BooksFactory;
import com.google.gson.Gson;
import exceptions.EmptyCellException;
import exceptions.IncorrectArgumentException;
import lombok.Getter;

import models.Book;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;

@Getter
public final class Library {

    private final Book[] cells;
    private final int amountOfCells;
    private int indexOfFirstEmptyCell = -1;
    private int indexOfGetBook = -1;
    @NotNull
    private final ArrayList<Integer> arrayListOfEmptyCells = new ArrayList<>();

    public Library(int amountOfBooks, @NotNull BooksFactory booksFactory) throws IllegalArgumentException, IncorrectArgumentException {
        var books = ((ArrayList<Book>) booksFactory.books());
        this.amountOfCells = amountOfBooks;
        if (amountOfBooks < books.size()) {
            throw new IncorrectArgumentException("Not enough cells");
        }
        this.cells = new Book[amountOfCells];
        for (int i = 0; i < books.size(); i++) {
            this.cells[i] = books.get(i);
        }
        if (amountOfCells > books.size()) {
            indexOfFirstEmptyCell = books.size();
        }
        for (int i = books.size(); i < amountOfCells; i++){
            arrayListOfEmptyCells.add(i);
        }
    }

    public @NotNull
    Book getBookFromCell(int index) throws IncorrectArgumentException {
        if (index > amountOfCells | index < 0)
            throw new IncorrectArgumentException("Cell index incorrect");
        if (checkBook(index)) throw new IncorrectArgumentException("Cell is empty");
        Book bufBook = cells[index];
        cells[index] = null;
        System.out.print("Book was taken: ");
        System.out.println(getJSONBook(bufBook, index));
        arrayListOfEmptyCells.add(index);
        return bufBook;
    }



    public void addBookToCell(@NotNull Book book) throws EmptyCellException {
        if (arrayListOfEmptyCells.size() == 0) throw new EmptyCellException("No empty cells");
        int minIndex=Collections.min(arrayListOfEmptyCells);
        cells[minIndex] = book;
        arrayListOfEmptyCells.remove(arrayListOfEmptyCells.indexOf(minIndex));

    }

    public void findFirstEmptyCell() {

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
