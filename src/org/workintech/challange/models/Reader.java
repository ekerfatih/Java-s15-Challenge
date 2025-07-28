package org.workintech.challange.models;

import java.util.HashMap;
import java.util.Map;

public class Reader extends Person {
    private Map<Book, Boolean> books;


    public Reader(String name) {
        super(name);
        this.books = new HashMap<>();
    }

    public void purchase_book(Book book, Status status) {
        books.put(book, false);
    }

    public void bookList() {
        books.keySet().forEach(System.out::println);
    }

    public void barrow_book(Book book) {
        books.put(book, true);
    }

    public boolean isReaderHaveTheBook(Book book) {
        if (books.containsKey(book)) System.out.println("This book already on user can't have another");
        return !books.containsKey(book);
    }

    public Book return_book(int id) {
        Book book = books.keySet().stream().filter(b -> b.getId() == id).findFirst().orElse(null);
        books.remove(book);
        return book;
    }

    public void show_book(String name) {
        System.out.println(books);
    }
}
