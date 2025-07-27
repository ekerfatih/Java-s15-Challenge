package org.workintech.challange.models;

import java.util.HashMap;
import java.util.Map;

//TODO 5 kitap limiti
public class Reader extends Person {
    private Map<Book, Boolean> books;

    // true olanlar barrowed
    public Reader(String name, Map<Book, Boolean> books) {
        this(name);
        this.books = new HashMap<>(books);
    }

    private Book find_book(int id) {
        for (Book b : books.keySet()) {
            if (b.getId() == id) {
                return b;
            }
        }
        return null;
    }

    public Reader(String name) {
        super(name);
        this.books = new HashMap<>();
    }

    public void purchase_book(Book book, Status status) {
        books.put(book, false);
    }

    public void bookList() {
        for (Book b : books.keySet()) {
            System.out.println(b);
        }
    }

    public void barrow_book(Book book) {
        books.put(book, true);
    }

    public boolean isReaderHaveTheBook(Book book) {
        if (books.containsKey(book)) System.out.println("This book already on user can't have another");
        return !books.containsKey(book);
    }

    public Book return_book(int id) {
        for (Book b : books.keySet()) {
            if (b.getId() == id) {
                books.remove(find_book(id));
                return b;
            }
        }
        return null;
    }

    public void show_book(String name) {
        System.out.println(books);
    }
}
