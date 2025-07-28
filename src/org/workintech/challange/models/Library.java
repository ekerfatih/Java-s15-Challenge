package org.workintech.challange.models;

import java.util.*;

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
    HashSet<BarrowCredentials> barrowSet = new HashSet<>();
    Set<Member> members;

    private BarrowCredentials findByIdBarrowCredentials(Book book, Reader reader) {
        return barrowSet.stream()
                .filter(b -> b.getBook() == book && b.getReader() == reader)
                .findFirst()
                .orElse(null);

    }

    private Library() {

        members = Set.of(
                new Member("guser", "guser", new Reader("Gizem")),
                new Member("ruser", "ruser", new Reader("Rifat"))
        );

        Book book1 = new Book(1L, getAuthorByName("George Orwell"), "1984", new Date(1949 - 1900, Calendar.JUNE, 8), Category.HISTORY, 15.35);
        Book book5 = new Book(5L, getAuthorByName("George Orwell"), "Animal Farm", new Date(1945 - 1900, Calendar.AUGUST, 17), Category.FICTION, 17.95);
        Book book6 = new Book(6L, getAuthorByName("George Orwell"), "Homage to Catalonia", new Date(1938 - 1900, Calendar.APRIL, 25), Category.NON_FICTION, 12.55);

        Book book2 = new Book(2L, getAuthorByName("J.R.R. Tolkien"), "The Hobbit", new Date(1937 - 1900, Calendar.SEPTEMBER, 21), Category.FANTASY, 11.75);
        Book book7 = new Book(7L, getAuthorByName("J.R.R. Tolkien"), "The Lord of the Rings", new Date(1954 - 1900, Calendar.JULY, 29), Category.FANTASY, 10.05);
        Book book8 = new Book(8L, getAuthorByName("J.R.R. Tolkien"), "The Silmarillion", new Date(1977 - 1900, Calendar.SEPTEMBER, 15), Category.FANTASY, 17.85);

        Book book3 = new Book(3L, getAuthorByName("F. Scott Fitzgerald"), "The Great Gatsby", new Date(1925 - 1900, Calendar.APRIL, 10), Category.FICTION, 22.45);
        Book book9 = new Book(9L, getAuthorByName("F. Scott Fitzgerald"), "Tender Is the Night", new Date(1934 - 1900, Calendar.APRIL, 12), Category.FICTION, 5.55);
        Book book10 = new Book(10L, getAuthorByName("F. Scott Fitzgerald"), "This Side of Paradise", new Date(1920 - 1900, Calendar.MARCH, 26), Category.FICTION, 3.75);

        Book book4 = new Book(4L, getAuthorByName("Mary Shelley"), "Frankenstein", new Date(1818 - 1900, Calendar.JANUARY, 1), Category.FICTION, 17.25);
        Book book11 = new Book(11L, getAuthorByName("Mary Shelley"), "Mathilda", new Date(1819 - 1900, Calendar.JANUARY, 1), Category.FICTION, 12.22);
        Book book12 = new Book(12L, getAuthorByName("Mary Shelley"), "Valperga", new Date(1823 - 1900, Calendar.JANUARY, 1), Category.HISTORY, 20.20);
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

    public void ListByCategory(int category) {
        for (Book book : books.keySet()) {
            if (book.getCategory() == Category.values()[category - 1]) {
                System.out.println(book);
            }
        }
    }

    public Category GetCategoryById(int category) {
        return Category.values()[category - 1];
    }

    public void getEnumValues() {
        Category[] categories = Category.values();
        for (int i = 0; i < categories.length; i++) {
            System.out.println((i + 1) + " - " + categories[i]);
        }
    }

    public void add_new_book(long id, String author, String name, Date date, Category category, double price, int quantity) {
        books.put(new Book(id, getAuthorByName(author), name, date, category, price), quantity);
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

    public void list_barrowed_books() {
        if (barrowSet.isEmpty()) System.out.println("There is no book given away.");
        for (BarrowCredentials b : barrowSet) {
            System.out.println(b.getBook().get_title() + " book is gather by " + b.getReader().getName() + " at " + b.getGatheredTime());
        }
    }

    public <T> Book searchBook(T value) {
        if (value instanceof String str) {
            try {
                int id = Integer.parseInt(str);
                for (Book book : books.keySet()) {
                    if (book.getId() == id) {
                        return book;
                    }
                }
            } catch (NumberFormatException ignored) {
                // Not a number, continue to string match
            }

            for (Book book : books.keySet()) {
                if (book.get_title().equalsIgnoreCase(str) ||
                        book.get_author().getName().equalsIgnoreCase(str)) {
                    return book;
                }
            }

        } else if (value instanceof Integer id) {
            for (Book book : books.keySet()) {
                if (book.getId() == id) {
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

    public void update_book(long id, String author, String name, Date date, Category category, double price) {
        Book book = searchBookById(id);
        book.update(getAuthorByName(author), name, date, category, price);
    }

    public void request_book(int bookID, Reader reader) {
        Book book = searchBookById(bookID);
        if (this.books.get(book) > 0 && reader.isReaderHaveTheBook(book)) {
            reader.barrow_book(book);
            int booksLeft = this.books.get(book) - 1;
            this.books.put(book, booksLeft);
            barrowSet.add(new BarrowCredentials(reader, book));
            System.out.println("Book barrowed successfully");
            System.out.printf("Your deposit reciept : ");
            System.out.printf(String.valueOf("$" + book.getPrice()));
        }
    }

    public void book_take_back(Book book, Reader reader) {
        if (this.books.containsKey(book) && reader.isReaderHaveTheBook(book)) {
            int newBookCount = this.books.get(book) + 1;
            this.books.put(book, newBookCount);
            barrowSet.remove(findByIdBarrowCredentials(book, reader));
            System.out.println("Book given back successfully");
            System.out.printf("Your deposit : ");
            System.out.printf("$" + book.getPrice());
        } else {
            System.out.println("You cant give back a book not already in the system");
        }
    }

    public Member auth_check(String user, String password) {
        for (Member m : members) {
            if (m.getUserName().equals(user) && m.getPassword().equals(password)) {
                return m;
            }
        }
        return null;
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