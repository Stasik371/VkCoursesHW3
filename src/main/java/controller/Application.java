package controller;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.jetbrains.annotations.NotNull;

public final class Application {

    public static void main(@NotNull String[] args) {
        String pathToFile = args[0];
        int capacity = Integer.parseInt(args[1]);
        final Injector injector = Guice.createInjector(new InjectionModule(pathToFile));
        Library lib = injector.getInstance(LibraryFactory.class).library(capacity);
        lib.printAllOfBooks();
    }

}
