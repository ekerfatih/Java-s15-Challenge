import org.workintech.challange.Book;
import org.workintech.challange.Category;
import org.workintech.challange.library.Library;

import java.util.Calendar;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
//        Library.getInstance().ListBooks();
//        System.out.println(Library.getInstance().ListByCategory(Category.FICTION));
        System.out.println(Library.getInstance().searchBook(2));
        Library.getInstance().update_book(2L, Library.getInstance().getAuthorByName("J.R.R. Tolkien"), "The Hobbits", new Date(1937 - 1900, Calendar.SEPTEMBER, 21), Category.FANTASY);
//        Library.getInstance().remove_book(2);
        System.out.println("**************************************************");
        System.out.println(Library.getInstance().searchBook(2));
//        Library.getInstance().ListBooks();


    }
}