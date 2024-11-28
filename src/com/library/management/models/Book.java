package com.library.management.models;

/**
 * Book resource.
 */
public class Book extends Resource {
    private final int pageCount;
    private final String isbn;

    public Book(String title, int pageCount, String isbn) {
        super(title);
        this.pageCount = pageCount;
        this.isbn = isbn;
    }

    @Override
    public String getDetails() {
        return "Book [Title=" + getTitle() + ", Pages=" + pageCount + ", ISBN=" + isbn + "]";
    }
}
