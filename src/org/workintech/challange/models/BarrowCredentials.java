package org.workintech.challange.models;

import java.util.Date;
import java.util.Objects;

public class BarrowCredentials {

    private Date gatheredTime;
    private Reader reader;

    public Book getBook() {
        return book;
    }

    public Reader getReader() {
        return reader;
    }

    public Date getGatheredTime() {
        return gatheredTime;
    }

    private Book book;

    public BarrowCredentials(Reader reader, Book book) {
        this.gatheredTime = new Date();
        this.reader = reader;
        this.book = book;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof BarrowCredentials that)) return false;
        return Objects.equals(reader, that.reader) && Objects.equals(book, that.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reader, book);
    }
}
