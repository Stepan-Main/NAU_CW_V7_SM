package Services;

import Models.Book;
import Models.Library;
import Models.User;

import java.util.Scanner;

import static Utils.DataCheck.groupCheck;
import static Utils.DataCheck.nameCheck;

public class Searching extends DataManipulation {

    //<editor-fold desc="Searching">
    public static void searchingByFirstName(Scanner scanner, Library library) {
        System.out.println(menuColor + " Ведіть частину имені користувача " + reset);
        String firstName = nameCheck(scanner);
        StringBuilder list = new StringBuilder();
        for (User user: library.getUsers()) {
            if (user.getFirstName().toLowerCase().contains(firstName.toLowerCase())) {
                if (list.length() > 0) list.append("\n");
                list.append(user);
            }
        }
        printList(list.toString(), "Список користувачів");
    }

    public static void searchingBySecondName(Scanner scanner, Library library) {
        System.out.println(menuColor + " Ведіть частину прізвища користувача " + reset);
        String secondName = nameCheck(scanner);
        StringBuilder list = new StringBuilder();
        for (User user: library.getUsers()) {
            if (user.getLastName().toLowerCase().contains(secondName.toLowerCase())) {
                if (list.length() > 0) list.append("\n");
                list.append(user);
            }
        }
        printList(list.toString(), "Список користувачів");
    }

    public static void searchingByThirdName(Scanner scanner, Library library) {
        System.out.println(menuColor + " Ведіть частину побатькові користувача " + reset);
        String thirdName = nameCheck(scanner);
        StringBuilder list = new StringBuilder();
        for (User user: library.getUsers()) {
            if (user.getSurname().toLowerCase().contains(thirdName.toLowerCase())) {
                if (list.length() > 0) list.append("\n");
                list.append(user);
            }
        }
        printList(list.toString(), "Список користувачів");
    }

    public static void searchingByGroup(Scanner scanner, Library library) {
        System.out.println(menuColor + " Ведіть номер групи " + reset);
        String group = groupCheck(scanner);
        StringBuilder list = new StringBuilder();
        for (User user: library.getUsers()) {
            if (user.getGroup().toLowerCase().contains(group.toLowerCase())) {
                if (list.length() > 0) list.append("\n");
                list.append(user);
            }
        }
        printList(list.toString(), "Список користувачів");
    }

    public static void searchingByTitle(Scanner scanner, Library library) {
        System.out.println(menuColor + " Ведіть частину назви книжки " + reset);
        String title = scanner.nextLine();
        StringBuilder list = new StringBuilder();
        for (Book book: library.getBooks()) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                if (list.length() > 0) list.append("\n");
                list.append(book);
            }
        }
        printList(list.toString(), "Список книг");
    }

    public static void searchingByAuthor(Scanner scanner, Library library) {
        System.out.println(menuColor + " Ведіть частину прізвищя автора " + reset);
        String author = scanner.nextLine();
        StringBuilder list = new StringBuilder();
        for (Book book: library.getBooks()) {
            if (book.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                if (list.length() > 0) list.append("\n");
                list.append(book);
            }
        }
        printList(list.toString(), "Список книг");
    }

    public static void searchingByPublisher(Scanner scanner, Library library) {
        System.out.println(menuColor + " Ведіть частину назви видавця " + reset);
        String publisher = scanner.nextLine();
        StringBuilder list = new StringBuilder();
        for (Book book: library.getBooks()) {
            if (book.getPublisher().toLowerCase().contains(publisher.toLowerCase())) {
                if (list.length() > 0) list.append("\n");
                list.append(book);
            }
        }
        printList(list.toString(), "Список книг");
    }
    //</editor-fold>
}
