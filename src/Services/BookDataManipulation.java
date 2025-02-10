package Services;

import Models.Book;
import Models.Library;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static Utils.DataCheck.*;
import static Utils.FileManager.*;

public class BookDataManipulation extends DataManipulation {

    //<editor-fold desc="Books Data Manipulation">
    public static void printBooksList(Library library) {
        StringBuilder list = new StringBuilder();
        for (Book book : library.getBooks()) {
            if (list.length() > 0) list.append("\n");
            list.append(book);
        }
        printList(list.toString(), "Книжки");
    }

    public static void printIssuedBooksList(Library library) {
        StringBuilder list = new StringBuilder();
        for (Map.Entry<String, List<Book>> mapEntry: library.getIssuedBooks().entrySet()) {
            StringBuilder list1 = new StringBuilder();
            for (Book book : mapEntry.getValue()) {
                list1.append(list1.length() > 0 ? "; " : "").append(book.getTitle());
            }
            if (list.length() > 0) list.append("\n");
            list.append(mapEntry.getKey() + " : " + list1);
        }
        printList(list.toString(), "Видані книжки");
    }

    public static void addNewBook(Scanner scanner, Library library) {
        System.out.println("Ввести дані книжки:");
        System.out.print(" - ID книжки: ");
        int id = bookIDCheck(scanner);
        System.out.print(" - Назва книжки: ");
        // Перевіряти коректність введення назви книжки немає сенcу
        String title = scanner.nextLine();
        System.out.print(" - Автори книжки: ");
        String author = authorNameCheck(scanner);
        System.out.print(" - Видавництво книжки: ");
        // Перевіряти коректність введення назви книжки немає сенcу
        String publisher = scanner.nextLine();
        System.out.print(" - Кількість сторінок: ");
        int pages = pagesCheck(scanner);
        System.out.print(" - Наявність книжки: ");
        boolean available = availableCheck(scanner);
        Book book = new Book(id, title, author, publisher, pages, true);
        library.addBook(book);
        System.out.println("Книжка " + book + " додана в бібліотеку.");
        saveDataToFile(library);
    }

    public static void editBookData(Scanner scanner, Library library) {
        System.out.println("Знайдемо книжку. Для цього введіть назву книжки.");
        // Перевіряти коректність введення назви книжки немає сенcу
        String title = scanner.nextLine();
        for (Book book : library.getBooks()) {
            if (book.getTitle().equals(title)) {
                System.out.println(book); // Виводимо збережені дані книжки
                System.out.println("Які дані ви бажаєте відредагувати?\n" + menuColor +
                        " 1 - ID; 2 - Назва; 3 - Автори; 4 - Видавництво; 5 - Кількість сторінок; 6 - Наявність; 7 - Вийти " + reset);
                switch (menuNumberCheck(scanner, 7)) {
                    case 1: // ID
                        System.out.println("Введіть нове ID");
                        book.setId(bookIDCheck(scanner));
                        break;
                    case 2: // Назва
                        System.out.println("Введіть нову назву");
                        // Перевіряти коректність введення назви книжки немає сенcу
                        book.setTitle(scanner.next());
                        break;
                    case 3: // Автори
                        System.out.println("Введіть нових авторів");
                        book.setAuthor(authorNameCheck(scanner));
                        break;
                    case 4: // Видавництво
                        System.out.println("Введіть нове видавництво");
                        // Перевіряти коректність введення назви книжки немає сенcу
                        book.setPublisher(scanner.next());
                        break;
                    case 5: // Кількість сторінок
                        System.out.println("Введіть кількість сторінок");
                        book.setPages(pagesCheck(scanner));
                        break;
                    case 6: // Наявність
                        System.out.println("Введіть наявність (true/false)");
                        book.setAvailable(availableCheck(scanner));
                        break;
                    case 7: // Вийти
                        saveDataToFile(library);
                        break;
                    default:
                        System.out.println("Помилка! Повторіть введення...");
                        break;
                }
                System.out.println("Книжка " + book + " відредагована.");
                saveDataToFile(library);
            }
        }
    }

    public static void deleteBook(Scanner scanner, Library library) {
        System.out.println("Знайдемо книжку. Для цього введіть назву книжки.");
        // Перевіряти коректність введення назви книжки немає сенcу
        String title = scanner.nextLine();
        boolean isDeleted = false; // Флаг для виходу з ціклу для запобігання ConcurrentModificationException
        boolean isFound = false;
        for (Book book : library.getBooks()) {
            if (book.getTitle().equals(title)) {
                isFound = true;
                System.out.println("Перевірте дані книжки для видалення.");
                System.out.println(book);
                System.out.println(menuColor+" Yes - Видалити книжку; No - Не видаляти книжку "+ reset);
                String choice = scanner.nextLine();
                switch (choice) {
                    case "Yes":
                        library.deleteBook(book);
                        isDeleted = true;
                        System.out.println("Книжка видалена.");
                        saveDataToFile(library);
                        break;
                    case "No":
                        System.out.println("Книжка не видалена");
                        break;
                    default:
                        System.out.println("Помилка! Повторіть введення...");
                        break;
                }
                if (isDeleted) break; // Вихід з циклу після видалення користувача
            }
        }
        if (!isFound) System.out.println("Книжка не знайдена.");
    }

    public static void viewDataBook(Scanner scanner, Library library) {
        System.out.println("Знайдемо книжку. Для цього введіть назву книжки.");
        // Перевіряти коректність введення назви книжки немає сенcу
        String title = scanner.nextLine();
        Book bookFound = null;
        for (Book book : library.getBooks()) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                bookFound = book; // Коли знайшов книгу
                break;
            }
        }
        if (bookFound != null) {
            System.out.println("Книжка знайдена");
            System.out.println(bookFound);
        } else {
            System.out.println("Книжка не знайдена");
        }
    }

    public static void listBooks(Scanner scanner, Library library) {
        System.out.println(menuColor + " [ Список книг ]  1 - назви; 2 - авторів" + reset);
        List<Book> books = library.getBooks();

        // Для сортування за автором або за назвою використаємо стандартний метод sort
        // Компаратор передаємо у вигляді лямбда виразу
        StringBuilder list = new StringBuilder();
        switch (menuNumberCheck(scanner, 2)) {
            case 1:
                books.sort((b1, b2) -> b1.getTitle().compareToIgnoreCase(b2.getTitle()));
                for (Book book: books) {
                    if (list.length() > 0) list.append("\n");
                    list.append(book);
                }
                printList(list.toString(),"Список книг відсортований за назвою");
            break;
            case 2:
                books.sort((b1, b2) -> b1.getAuthor().compareToIgnoreCase(b2.getAuthor()));
                for (Book book: books) {
                    if (list.length() > 0) list.append("\n");
                    list.append(book);
                }
                printList(list.toString(),"Список книг відсортований за автором");
            break;
        }
    }
    //</editor-fold>
}
