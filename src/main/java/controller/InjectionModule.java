package controller;

import booksFactory.BooksFactory;
import booksFactory.FileBookFactory;
import com.google.inject.AbstractModule;


public final class InjectionModule extends AbstractModule {
    private final String path;
    public InjectionModule(String path) {
        this.path = path;
    }

    @Override
    protected void configure() {
        bind(BooksFactory.class).toInstance(new FileBookFactory(path));
    }
}