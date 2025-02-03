package Services;

import CustomExceptions.LimitExceededException;
import Models.Book;
import Models.Library;
import Models.User;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static Utils.DataCheck.recordNumberCheck;
import static Utils.FileManager.*;

public class IssueManipulation extends DataManipulation {
    
    //<editor-fold desc="Issue/Return">
    public static void issueBook(Scanner scanner, Library library) {
        Book bookForIssue = null;

        System.out.println("Знайдемо користувача. Для цього введіть номер залікової книжки.");
        String record = recordNumberCheck(scanner);
        for (User user : library.getUsers()) {
            if (user.getRecordNumber().equals(record)) {
                System.out.println("Перевірте данні користувача для видачи.");
                System.out.println(user);
                break;
            }
        }

        System.out.println("Знайдемо книжку. Для цього введіть назву книжки.");
        // Перевіряти коретність введення назви книжки немає сенцу
        String title = scanner.nextLine();
        for (Book book : library.getBooks()) {
            if (book.getTitle().equals(title)) {
                if (!book.isAvailable()) {
                    System.out.println("Книжки зараз немає у бібліотеці.");
                    break;
                }
                bookForIssue = book;
                System.out.println("Перевірте данні книжки для видачи.");
                System.out.println(book);
                break;
            }
        }

        // Коли книга є у бібліотеці
        if (bookForIssue.isAvailable()) {
            System.out.println(menuColor + " Yes - Видати книжку; No - Не видавати книжку " + reset);
            String choice = scanner.nextLine();
            switch (choice) {
                case "Yes":
                    try {
                        library.addIssuedBook(record, bookForIssue);
                        System.out.println("Книжка видана.");
                        saveDataToFile(library);
                    } catch (LimitExceededException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "No":
                    System.out.println("Книжка не видана");
                    break;
                default:
                    System.out.println(errorColor+" Помилка! Повторіть введення... "+ reset);
                    break;
            }
        }
    }

    public static void receiptBook(Scanner scanner, Library library) {
        Book bookForReturn = null;
        User userForReturn = null;

        System.out.println("Знайдемо користувача. Для цього введіть номер залікової книжки.");
        String record = recordNumberCheck(scanner);
        for (User user : library.getUsers()) {
            if (user.getRecordNumber().equals(record)) {
                userForReturn = user;
                System.out.println("Перевірте данні ористувача.");
                System.out.println(user);
                break;
            }
        }

        System.out.println("Знайдемо книжку. Для цього введіть назву книжки.");
        // Перевіряти коретність введення назви книжки немає сенцу
        String title = scanner.nextLine();
        for (Book book : library.getBooks()) {
            if (book.getTitle().equals(title)) {
                bookForReturn = book;
                System.out.println("Перевірте данні книжки для прийому.");
                System.out.println(book);
                break;
            }
        }

        System.out.println(menuColor+" Yes - Прийняти книжку; No - Не прийняти книжку "+ reset);
        String choice = scanner.nextLine();
        switch (choice) {
            case "Yes":
                library.returnBook(userForReturn, bookForReturn);
                System.out.println("Книжка здана в бібліотеку.");
                saveDataToFile(library);
                break;
            case "No":
                System.out.println("Книжка не прийнята");
                break;
            default:
                System.out.println(errorColor+" Помилка! Повторіть введення... "+ reset);
                break;
        }
    }

    public static void issueBookToUser(Scanner scanner, Library library) {
        System.out.println("Знайдемо користувача. Для цього введіть номер залікової книжки.");
        String record = recordNumberCheck(scanner);
        for (User user : library.getUsers()) {
            if (user.getRecordNumber().equals(record)) {
                System.out.println("Перевірте данні користувача.");
                System.out.println(user);
                break;
            }
        }

        List<Book> bookList = library.getIssuedBooks().get(record);
        if (bookList.size() > 0) {
            StringBuilder list = new StringBuilder();
            for (Book book: bookList) {
                if (list.length() > 0) list.append("\n");
                list.append(book);
            }
            printList(list.toString(),"Книжки у користувача користувача");
        } else {
            System.out.println("Цей користувач не маю заброгодності книжкам.");
        }
    }

    public static void availableBook(Scanner scanner, Library library) {
        Book bookInfo = null;

        System.out.println("Знайдемо книжку. Для цього введіть назву книжки.");
        // Перевіряти коретність введення назви книжки немає сенцу

        String title = scanner.nextLine();
        for (Book book : library.getBooks()) {
            if (book.getTitle().equals(title)) {
                bookInfo = book;
                System.out.println("Перевірте данні книжки.");
                System.out.println(book);
                break;
            }
        }

        if (bookInfo != null) {
            if (bookInfo.isAvailable()) {
                System.out.println("Данна книжка знаходится бібліотеці.");
            } else {
                StringBuilder list = new StringBuilder();
                for (Map.Entry<String, List<Book>> mapEntry: library.getIssuedBooks().entrySet()) {
                    for (Book book : mapEntry.getValue()) {
                        if (book.getId() == bookInfo.getId()) {
                            list.append("Данна книжка видана наступному користовачу:");
                            for (User user: library.getUsers()) {
                                if (user.getRecordNumber().equals(mapEntry.getKey())) {
                                    if (list.length() > 0) list.append("\n");
                                    list.append(user);
                                }
                            }
                        }
                    }
                }
                printList(list.toString(),"Інформация про книжку");
            }
        }
    }
    //</editor-fold>
}
