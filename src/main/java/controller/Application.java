package controller;

import com.google.inject.Guice;
import com.google.inject.Injector;
import models.Author;
import models.Book;
import org.jetbrains.annotations.NotNull;

public final class Application {

    public static void main(@NotNull String[] args) throws Exception {
        if(args.length!=2) throw new Exception("Not enough arguments in commandLine");
        String pathToFile = args[0];
        int capacity = Integer.parseInt(args[1]);
        final Injector injector = Guice.createInjector(new InjectionModule(pathToFile));
        Library lib = injector.getInstance(LibraryFactory.class).library(capacity);
        lib.printAllOfBooks();



    }

}
