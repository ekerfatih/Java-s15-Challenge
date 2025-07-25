package org.workintech.challange.console;

import org.workintech.challange.models.Library;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Console {
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

        }
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
        Scanner input = new Scanner(System.in);
        String name, password;

        while (true) {
            System.out.println("Please enter your username (*_*)");
            name = input.nextLine();
            System.out.println("Please enter your password (-_-)");
            password = input.nextLine();

            if (name.equalsIgnoreCase("user") && password.equalsIgnoreCase("user")) {
                break;
            }

            System.out.println("\u001B[31mWrong username or password\u001B[0m");
        }

        clearScreen();
        System.out.println("Welcome Dear");
        System.out.println("What would you like to do?\n\n");
        System.out.println("1- List Books");
        System.out.println("2- Search Books");
        System.out.println("3- Barrow book");
        System.out.println("4- Give book back");
        System.out.println("5- See your credentials");
    }

    private void LibrarianUI() {

    }
}
