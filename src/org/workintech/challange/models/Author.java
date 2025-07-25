package org.workintech.challange.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Author extends Person {

    private Set<Book> books = new HashSet<>();

    public Author(String name) {
        super(name);
    }

    public Book new_book(long id, String name, Date date, Category category) {
        //TODO if not contain validation
        Book book = new Book(id, this, name, date, category);
        books.add(book);
        return book;
    }

    public Book new_book(Book book) {
        //TODO if not contain validation
        books.add(book);
        return book;
    }

    public void show_book(String name) {
        System.out.println(books);
    }
}
