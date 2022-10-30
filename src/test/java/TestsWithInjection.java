import booksFactory.BooksFactory;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import controller.InjectionModule;
import controller.Library;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestsWithInjection {
    @Inject
    private BooksFactory booksFactory;

    @BeforeEach
    public void beforeEachTest() {
        final Injector injector = Guice.createInjector(new InjectionModule("books.json"));
        injector.injectMembers(this);
    }

    @DisplayName("Библиотека бросает исключение при создании, если ее вместимость меньше чем количество книг, возвращаемое фабрикой.")
    @Test
    public void creatingLibraryException() {
        assertThrows(IllegalStateException.class, () -> new Library(1,booksFactory));
    }
}
