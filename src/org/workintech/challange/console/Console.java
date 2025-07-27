package org.workintech.challange.console;

import org.workintech.challange.models.Library;
import org.workintech.challange.models.Member;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Console {
    private Member loggedInMember;

    public Console() {
        init();
    }

    private void init() {
        Scanner s = new Scanner(System.in);

        System.out.println("Hello, Welcome to \033[1;4mTHE LIBRARY\033[0m");
        System.out.println("Please specify your login type");
        System.out.println("1- Guest");
        System.out.println("2- Member");
        System.out.println("3- Librarian");
        String c = s.nextLine();
        clearScreen();
        loggedInMember = null;
        switch (c) {
            case "1":
                GuestUI();

                break;
            case "2":
                MemberUI();

                break;
            case "3":
                LibrarianUI();

                break;
            default:
                InvalidUI(this::init);
        }
    }

    private void InvalidUI(Runnable callback) {
        clearScreen();
        System.out.println("\u001B[31mPlease enter a valid number\u001B[0m");
        callback.run();
    }

    private void GuestUI() {
        clearScreen();
        System.out.println("Welcome Guest");
        System.out.println("Please sign up to see more options");
        Scanner s = new Scanner(System.in);

        System.out.println("1- List Books");
        System.out.println("2- Search Books");
        String answer = s.nextLine();
        clearScreen();
        switch (answer) {
            case "1":
                Library.getInstance().ListBooks();
                BackOrExit(this::GuestUI);
                break;
            case "2":
                System.out.println("Please enter your search key it can be ID, Author or Book name");
                Scanner x = new Scanner(System.in);
                String searchKey = x.nextLine();
                System.out.println(Library.getInstance().searchBook(searchKey));
                BackOrExit(this::GuestUI);
                break;
            default:
                init();

        }
    }

    private void direct_to_main_menu() {
        System.out.println("\n***********************************");
        System.out.println("Enter anything else to go main menu");
        System.out.println("***********************************");
    }

    private void BackOrExit(Runnable callback) {
        System.out.println("\n\nWhat would you like to do next?");
        System.out.println("1- Go Back");
        System.out.println("2- Main Menu");
        Scanner s = new Scanner(System.in);
        int answer = Integer.parseInt(s.nextLine());
        switch (answer) {
            case 1:
                callback.run();
                break;
            case 2:
                clearScreen();
                init();
                break;
        }
    }

    public static void clearScreen() {
        for (int i = 0; i < 50; i++) System.out.println();
    }

    private void MemberUI() {
        login();
        Scanner s = new Scanner(System.in);
        clearScreen();
        System.out.println(String.format("Welcome %s", loggedInMember.getReader().getName()));
        System.out.println("What would you like to do?\n\n");
        System.out.println("1- List Books");
        System.out.println("2- List Books by Category ");
        System.out.println("3- Search Books");
        System.out.println("4- Barrow book");
        System.out.println("5- Give book back");
        System.out.println("6- See your books");
        direct_to_main_menu();
        String answer = s.nextLine();
        clearScreen();
        switch (answer) {
            case "1":
                clearScreen();
                Library.getInstance().ListBooks();
                BackOrExit(this::MemberUI);
                break;
            case "2":
                clearScreen();
                Library.getInstance().getEnumValues();
                System.out.println("Please select a category");
                Library.getInstance().ListByCategory(Integer.parseInt(s.nextLine()));
                BackOrExit(this::MemberUI);
                break;
            case "3":
                System.out.println("Please enter your search key it can be ID, Author or Book name");
                String searchKey = s.nextLine();
                System.out.println(Library.getInstance().searchBook(searchKey));
                BackOrExit(this::MemberUI);
                break;
            case "4":
                System.out.println("Please enter requested book id");
                int requestID = Integer.parseInt(s.nextLine());
                Library.getInstance().request_book(requestID, loggedInMember.getReader());
                BackOrExit(this::MemberUI);
                break;
            case "5":
                System.out.println("Your current books :");
                loggedInMember.getReader().bookList();
                System.out.println("\nPlease enter the book id you want to give back");
                Library.getInstance().book_take_back(loggedInMember.getReader().return_book(Integer.parseInt(s.nextLine())), loggedInMember.getReader());
                BackOrExit(this::MemberUI);
                break;
            case "6":
                System.out.println("Your current books :");
                loggedInMember.getReader().bookList();
                BackOrExit(this::MemberUI);
                break;
            default:
                init();

        }
    }

    private void LibrarianUI() {
        Scanner input = new Scanner(System.in);
        String name, password;
        while (true) {
            System.out.println("Please enter your username (*_*)");
            name = input.nextLine();
            System.out.println("Please enter your password (-_-)");
            password = input.nextLine();
            if (name.equals("admin") && password.equals("admin")) {
                break;
            }

            System.out.println("\u001B[31mWrong username or password\u001B[0m");
        }
        Scanner s = new Scanner(System.in);
        clearScreen();
        System.out.println("Welcome Admin");
        System.out.println("What would you like to do?\n\n");
        System.out.println("1- List Books");
        System.out.println("2- List Books by Category");
        System.out.println("3- Search Books");
        System.out.println("4- List barrowed books");
        System.out.println("5- Add new book to library");
        System.out.println("6- Delete a book from library");
        direct_to_main_menu();
        String answer = s.nextLine();
        clearScreen();
        switch (answer) {
            case "1":
                clearScreen();
                Library.getInstance().ListBooks();
                BackOrExit(this::LibrarianUI);
                break;
            case "2":
                clearScreen();
                Library.getInstance().getEnumValues();
                System.out.println("Please select a category");
                Library.getInstance().ListByCategory(Integer.parseInt(s.nextLine()));
                BackOrExit(this::MemberUI);
                break;
            case "3":
                System.out.println("Please enter your search key it can be ID, Author or Book name");
                Scanner x = new Scanner(System.in);
                String searchKey = x.nextLine();
                System.out.println(Library.getInstance().searchBook(searchKey));
                BackOrExit(this::LibrarianUI);
                break;
            case "4":
                Library.getInstance().list_barrowed_books();
                BackOrExit(this::LibrarianUI);
                break;
            default:
                init();

        }
    }

    private void login() {
        Scanner input = new Scanner(System.in);
        String name, password;
        while (loggedInMember == null) {
            System.out.println("Please enter your username (*_*)");
            name = input.nextLine();
            System.out.println("Please enter your password (-_-)");
            password = input.nextLine();
            loggedInMember = Library.getInstance().auth_check(name, password);
            if (loggedInMember != null) {
                break;
            }

            System.out.println("\u001B[31mWrong username or password\u001B[0m");
        }
    }
}
