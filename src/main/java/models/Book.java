package models;

import lombok.Data;

public @Data class Book {
    private final Author author;
    private final String name;
}
