package org.workintech.challange.models;

import java.util.HashMap;
import java.util.Map;

//TODO 5 kitap limiti
public class Reader extends Person {
    private Map<Book, Boolean> books;

    public Reader(String name, Map<Book, Boolean> books) {
        this(name);
        this.books = new HashMap<>(books);
    }

    public Reader(String name) {
        super(name);
    }

    public void purchase_book(Book book, Status status) {
        books.put(book, true);
    }

    public void barrow_book(Book book) {

    }

    public void return_book() {
        //TODO return a book with parameter
    }

    public void show_book(String name) {
        System.out.println(books);
    }
}
