package org.workintech.challange.models;

import java.util.Date;
import java.util.Objects;

public class Book {
    private long id;
    private Category category;
    private Author author;
    private String name;

    public double getPrice() {
        return price;
    }

    private double price;
    private Reader owner;
    private Status status;
    private String edition;
    private Date date_of_purchase;
    private Date release_date;

    // <editor-fold desc="Getters">
    public Date getRelease_date() {
        return release_date;
    }

    public Category getCategory() {
        return category;
    }

    public long getId() {
        return id;
    }

    public Author get_author() {
        return author;
    }

    public String get_title() {
        return name;
    }

    // </editor-fold>
    // <editor-fold desc="Constructors">
    public Book(long id, Author author, String name, Date date, Category category, double price) {
        this.id = id;
        this.author = author;
        this.category = category;
        this.name = name;
        this.release_date = date;
        this.price = price;
    }

    public Book(long id, Author author, String name, Date date, Category category, String edition, double price) {
        this.id = id;
        this.author = author;
        this.category = category;
        this.name = name;
        this.price = price;
        this.edition = edition;
        this.release_date = date;
    }

    // </editor-fold>
    // <editor-fold desc="Methods">
    public void update(Author author, String name, Date date, Category category, double price) {
        this.author = author;
        this.category = category;
        this.name = name;
        this.release_date = date;
        this.price = price;
    }

    public void change_owner() {

    }

    public void display() {

    }

    public void update_status() {

    }

    // </editor-fold>
    //<editor-fold desc="Hash, Equals, ToString">
    @Override
    public String toString() {
        return "\nBook{" +
                "id=" + id +
                ", category=" + category +
                ", author=" + author +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", owner=" + owner +
                ", status=" + status +
                ", edition='" + edition + '\'' +
                ", date_of_purchase=" + date_of_purchase +
                "}";
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Book book)) return false;
        return id == book.id;
    }
//</editor-fold>
}
