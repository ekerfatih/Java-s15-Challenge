package org.workintech.challange.person;

import org.workintech.challange.Book;

public interface ILendbook {

    void borrow_book(Book book);

    void return_book(Book book);

    void purchase_book(Book book);

}
