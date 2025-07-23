package org.workintech.challange.library;

import java.util.*;
import java.util.function.Predicate;

import org.workintech.challange.Book;
import org.workintech.challange.Category;
import org.workintech.challange.person.Author;
import org.workintech.challange.person.Reader;

public class Library {
    private static Library instance;

    public static Library getInstance() {
        if (instance == null) {
            instance = new Library();
        }
        return instance;
    }

    private static Set<Author> authorSet = new HashSet<>();
    Map<Book, Integer> books;
    Set<Reader> readers = new HashSet<>();

    private Library() {


        Reader ahmet = new Reader("Ahmet");
        Reader mehmet = new Reader("Mehmet");
        Reader buse = new Reader("Buse");
        readers.add(ahmet);
        readers.add(mehmet);
        readers.add(buse);

        Book book1 = new Book(1L, getAuthorByName("George Orwell"), "1984", new Date(1949 - 1900, Calendar.JUNE, 8), Category.HISTORY);
        Book book5 = new Book(5L, getAuthorByName("George Orwell"), "Animal Farm", new Date(1945 - 1900, Calendar.AUGUST, 17), Category.FICTION);
        Book book6 = new Book(6L, getAuthorByName("George Orwell"), "Homage to Catalonia", new Date(1938 - 1900, Calendar.APRIL, 25), Category.NON_FICTION);

        Book book2 = new Book(2L, getAuthorByName("J.R.R. Tolkien"), "The Hobbit", new Date(1937 - 1900, Calendar.SEPTEMBER, 21), Category.FANTASY);
        Book book7 = new Book(7L, getAuthorByName("J.R.R. Tolkien"), "The Lord of the Rings", new Date(1954 - 1900, Calendar.JULY, 29), Category.FANTASY);
        Book book8 = new Book(8L, getAuthorByName("J.R.R. Tolkien"), "The Silmarillion", new Date(1977 - 1900, Calendar.SEPTEMBER, 15), Category.FANTASY);

        Book book3 = new Book(3L, getAuthorByName("F. Scott Fitzgerald"), "The Great Gatsby", new Date(1925 - 1900, Calendar.APRIL, 10), Category.FICTION);
        Book book9 = new Book(9L, getAuthorByName("F. Scott Fitzgerald"), "Tender Is the Night", new Date(1934 - 1900, Calendar.APRIL, 12), Category.FICTION);
        Book book10 = new Book(10L, getAuthorByName("F. Scott Fitzgerald"), "This Side of Paradise", new Date(1920 - 1900, Calendar.MARCH, 26), Category.FICTION);

        Book book4 = new Book(4L, getAuthorByName("Mary Shelley"), "Frankenstein", new Date(1818 - 1900, Calendar.JANUARY, 1), Category.FICTION);
        Book book11 = new Book(11L, getAuthorByName("Mary Shelley"), "Mathilda", new Date(1819 - 1900, Calendar.JANUARY, 1), Category.FICTION);
        Book book12 = new Book(12L, getAuthorByName("Mary Shelley"), "Valperga", new Date(1823 - 1900, Calendar.JANUARY, 1), Category.HISTORY);
        books = new HashMap<>(Map.ofEntries(
                Map.entry(book1, 4),
                Map.entry(book2, 3),
                Map.entry(book3, 2),
                Map.entry(book4, 5),
                Map.entry(book5, 6),
                Map.entry(book6, 2),
                Map.entry(book7, 4),
                Map.entry(book8, 3),
                Map.entry(book9, 1),
                Map.entry(book10, 2),
                Map.entry(book11, 1),
                Map.entry(book12, 3)
        ));

    }

    public Author getAuthorByName(String name) {
        for (Author author : authorSet) {
            if (author.getName().equals(name)) {
                return author;
            }
        }
        Author newAuthor = new Author(name);
        authorSet.add(newAuthor);
        return newAuthor;
    }

    public void ListBooks() {
        System.out.println(books);
    }

    public Set<Book> ListByCategory(Category category) {
        Set<Book> newList = new HashSet<>();
        for (Book book : books.keySet()) {
            if (book.getCategory() == category) {
                newList.add(book);
            }
        }
        return newList;
    }

    public void add_new_book(String authorName, Book book, Integer quantity) {
        books.put(getAuthorByName(authorName).new_book(book), quantity);
    }

    //    public Book searchBook(Predicate<Book> condition) {
//        for (Book book : books.keySet()) {
//            if (condition.test(book)) {
//                return book;
//            }
//        }
//        return null;
//    }
    public Book searchBookById(long id) {
        for (Book book : books.keySet()) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    public <T> Book searchBook(T value) {
        if (value instanceof Integer id) {
            for (Book book : books.keySet()) {
                if (book.getId() == id) {
                    return book;
                }
            }
        } else if (value instanceof String name) {
            for (Book book : books.keySet()) {
                if (book.get_title().equalsIgnoreCase(name) || book.get_author().getName().equalsIgnoreCase(name)) {
                    return book;
                }
            }
        }
        return null;
    }

    public void remove_book(int id) {
        Book searchedBook = null;
        for (Book b : books.keySet()) {
            if (b.getId() == id) {
                searchedBook = b;
                break;
            }
        }
        if (searchedBook != null) {
            books.remove(searchedBook);
        }
    }

    public void update_book(long id, Author author, String name, Date date, Category category) {
        Book book = searchBookById(id);
        book.update(author, name, date, category);
    }
}




/*
+TODO Sisteme yeni kitap eklenebilir
+TODO Sistemde id,isim,veya yazar bilgisine göre bir kitap seçilebilir.
+TODO Sistemde var olanı güncelleme
+TODO Sistemdeki kitabı silme
+TODO Sistemde var olan kategoriye göre listeleme
Bir kullanıcı sistemde eğer kitap kütüphanede varsa ve başkası tarafından alınmadıysa bir kitabı ödünç alabilir.
 Bu durum yaşanırsa hangi kitabın hangi kullanıcıda olduğunun bilgisi tutulmalıdır.
-TODO Yani kütüphane kime ödünç verildiği bilgisini tutacak.
-TODO Kullanıcı geri teslim edebilir
-TODO Sistemden bir kitap alındığında kitabı alan kullanıcıya bir fatura kesilmelidir. Kitabı geri iade ettiğinde
  kullanıcıya ücreti geri iade edilmelidir.
 */