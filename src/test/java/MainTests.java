import booksFactory.BooksFactory;

import static org.hamcrest.Matchers.*;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import controller.InjectionModule;
import controller.Library;
import exceptions.EmptyCellException;
import exceptions.IncorrectArgumentException;
import models.Author;
import models.Book;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;


public class MainTests {


    @NotNull
    private static Library library;

    @NotNull
    private static ArrayList<Book> booksFromBookFabric;

    @NotNull
    private static final Type listBooksType = new TypeToken<ArrayList<Book>>() {
    }.getType();

    @NotNull
    private static final String PATH_TO_FILE = "src/test/resources/books.json";

    @NotNull
    private static final Book testBook = new Book(new Author("TestTest"), "TestTest");


    @BeforeEach
    public void beforeEachTests() throws FileNotFoundException, IncorrectArgumentException {
        final Injector injector = Guice.createInjector(new InjectionModule("books.json"));
        injector.injectMembers(this);
        final var mockBooksFactory = Mockito.mock(BooksFactory.class);
        booksFromBookFabric = new Gson().fromJson(new BufferedReader(new FileReader(PATH_TO_FILE)), listBooksType);
        Mockito.when(mockBooksFactory.books()).thenReturn(booksFromBookFabric);
        library = new Library((booksFromBookFabric).size() + 1, mockBooksFactory);
        library.getArrayListOfEmptyCells().clear();
        library.getArrayListOfEmptyCells().add(5);
    }

    @DisplayName("При взятии книги информация о ней и ячейке выводится.")
    @Test
    public void infoAboutTakenBook() throws IncorrectArgumentException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        String expRes = "Book was taken: " + library.getJSONBook(library.getCells()[0], 0) + "\r\n";
        library.getBookFromCell(0);
        assertThat(expRes, is(output.toString()));
    }

    @DisplayName("При попытке взять книгу из пустой ячейки библиотека бросает исключение")
    @Test
    public void getBookFromEmptyCell() {
        assertThrows(IncorrectArgumentException.class, () -> library.getBookFromCell(5));
    }

    @DisplayName("Если при добавлении книги свободных ячеек нет, библиотека бросает исключение.")
    @Test
    public void setBookToEmptyCell() throws EmptyCellException {
        library.addBookToCell(testBook);
        assertThrows(EmptyCellException.class,
                () -> library.addBookToCell(testBook));
    }

    @DisplayName("Вызов метода “напечатать в консоль содержимое” выводит информацию о содержимом ячеек библиотеки.")
    @Test
    public void infoAboutLibrary() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        library.printAllOfBooks();
        StringBuilder expRes = new StringBuilder();
        for (int i = 0; i < library.getAmountOfCells(); i++) {
            if (library.getCells()[i] == null) {
                expRes.append("Number of cell: ").append(i).append(" Cell is empty\r\n");
            } else {
                expRes.append(library.getJSONBook(library.getCells()[i], i)).append("\r\n");
            }
        }
        Assertions.assertEquals(expRes.toString().trim(), output.toString().trim());
    }

    @DisplayName("При добавлении книги она размещается в первой свободной ячейке.")
    @Test
    public void firstCellWhenAdd() throws IncorrectArgumentException, EmptyCellException {
        library.getBookFromCell(0);
        int expIndex = Collections.min(library.getArrayListOfEmptyCells()), actIndex = -1;
        library.addBookToCell(testBook);
        for (int i = 0; i < library.getCells().length; i++) {
            if (library.getCells()[i] == testBook) {
                actIndex = i;
                break;
            }
        }
        assertThat(expIndex, is(actIndex));
    }

    @DisplayName("При взятии книги возвращается именно та книга, что была в этой ячейке.")
    @Test
    public void sameBookFromCell() throws IncorrectArgumentException {
        Book expBook = library.getCells()[0];
        Book actBook = library.getBookFromCell(0);
        assertThat(expBook, is(actBook));
    }

    @DisplayName("При создании библиотеки все книги расставлены по ячейкам в порядке как они возвращаются фабрикой книг. Остальные ячейки пусты.")
    @Test
    public void booksInTheRightPlaces() {
        for (int i = 0; i < booksFromBookFabric.size(); i++) {
            assertEquals(booksFromBookFabric.get(i), library.getCells()[i]);
        }
        for (int i = booksFromBookFabric.size(); i < library.getCells().length; i++) {
            assertNull(library.getCells()[i]);
        }
    }


}
