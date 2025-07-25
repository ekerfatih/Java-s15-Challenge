package org.workintech.challange.models;

public interface ILendbook {

    void borrow_book(Book book);

    void return_book(Book book);

    void purchase_book(Book book);

}
