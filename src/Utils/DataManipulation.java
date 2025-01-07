package Utils;

import CustomExceptions.LimitExceededException;
import MainClasses.Editions.Book;
import MainClasses.Library;
import MainClasses.Users.User;

import java.util.Scanner;

public class DataManipulation {
    //<editor-fold desc="Books Data Manipulation">
    public static void printBooksList(Library library) {
        for (Book book : library.getBooks()) {
            System.out.println(bookDataPrint(book));
        }
    }

    private static String bookDataPrint(Book book) {
        return book.getId() + " " +
                book.getTitle() + " " +
                book.getAuthor() + " " +
                book.getPublisher() + " " +
                book.getPages() + " " +
                book.isAvailable();
    }

    public static void addNewBook(Scanner scanner, Library library) {
        System.out.println("Ввсести данні книжки:");
        System.out.print(" - ID книжки: ");
        int id = scanner.nextInt();
        System.out.print(" - Назва книжки: ");
        String title = scanner.next();
        System.out.print(" - Автори книжки: ");
        String author = scanner.next();
        System.out.print(" - Видаництво книжки: ");
        String publisher = scanner.next();
        System.out.print(" - Кількість сторінок: ");
        int pages = scanner.nextInt();
        System.out.print(" - Наявність книжки: ");
        boolean available = scanner.nextBoolean();
        Book book = new Book(id, title, author, publisher, pages, true);
        library.addBook(book);
        System.out.println("Книжка " + bookDataPrint(book) + " додана в бібліотеку.");
    }

    public static void editBookData(Scanner scanner, Library library) {
        System.out.println("Знайдемо книжку. Для цього введіть назву книжки.");
        String title = scanner.next();
        for (Book book : library.getBooks()) {
            if (book.getTitle().equals(title)) {
                System.out.println(bookDataPrint(book)); // Виводимо збережені дані книжки
                System.out.println("Які данні ви бажайте відрагувати?\n" +
                        "1 - ID; 2 - Назва; 3 - Автори; 4 - Видаництво; 5 - Кількість сторінок; 6 - Наявність; 7 - Вийти");
                int userFieldChoice = scanner.nextInt();
                switch (userFieldChoice) {
                    case 1: // ID
                        System.out.println("Ведіть нове ID");
                        book.setId(scanner.nextInt());
                        break;
                    case 2: // Назва
                        System.out.println("Ведіть нову назву");
                        book.setTitle(scanner.next());
                        break;
                    case 3: // Автори
                        System.out.println("Ведіть нових авторів");
                        book.setAuthor(scanner.next());
                        break;
                    case 4: // Видаництво
                        System.out.println("Ведіть нове видаництво");
                        book.setPublisher(scanner.next());
                        break;
                    case 5: // Кількість сторінок
                        System.out.println("Ведіть кількість сторінок");
                        book.setPages(scanner.nextInt());
                        break;
                    case 6: // Наявність
                        System.out.println("Пачте наявність (true/false)");
                        book.setAvailable(scanner.nextBoolean());
                        break;
                    case 7: // Вийти
                        DataManipulation.SaveDataToFile(library);
                        break;
                    default:
                        System.out.println("Помилка! Повторіть введення...");
                        break;
                }
                System.out.println("Книжка " + bookDataPrint(book) + " відредагована.");
                System.out.println(bookDataPrint(book));
            }
        }
    }

    public static void deleteBook(Scanner scanner, Library library) {
        System.out.println("Знайдемо книжку. Для цього введіть назву книжки.");
        String title = scanner.next();
        boolean isDeleted = false; // Флаг для виходу з ціклу для забобігання ConcurrentModificationException
        for (Book book : library.getBooks()) {
            if (book.getTitle().equals(title)) {
                System.out.println("Перевірте данні книжки для видалення.");
                System.out.println(bookDataPrint(book));
                System.out.println("Yes - Видалити книжку; No - Не видаляти книжку");
                String choice = scanner.next();
                switch (choice) {
                    case "Yes":
                        System.out.println("Книжка видалена.");
                        library.deleteBook(book);
                        isDeleted = true;
                        break;
                    case "No":
                        System.out.println("Книжка не видалена");
                        break;
                    default:
                        System.out.println("Помилка! Повторіть введення...");
                        break;
                }
                if (isDeleted) break; // Вихід з циклу після видаленя користувача
            }
        }
    }
    //</editor-fold>

    //<editor-fold desc="Users Data Manipulation">
    public static void printUsersList(Library library) {
        for (User user : library.getUsers()) {
            System.out.println(userDataPrint(user));
        }
    }

    private static String userDataPrint(User user) {
        return user.getRecordNumber() + " " +
                user.getFirstName() + " " +
                user.getLastName() + " " +
                user.getSurname() + " " +
                user.getGroup() + " " +
                user.getEmail();
    }

    public static void addNewUser(Scanner scanner, Library library) {
        System.out.println("Ввести данні користувача: ");
        System.out.print(" - номер залікової книжки: ");
        String recordNumber = scanner.next();
        System.out.print(" - імя: ");
        String firstName = scanner.next();
        System.out.print(" - прізвище: ");
        String lastName = scanner.next();
        System.out.print(" - побатькові: ");
        String surname = scanner.next();
        System.out.print(" - група: ");
        String group = scanner.next();
        System.out.print(" - електронна пошта: ");
        String email = scanner.next();
        User user = new User(recordNumber, firstName, lastName, surname, group, email);
        library.addUser(user);
        System.out.println("Користувач " + userDataPrint(user) + " доданий в картотеку.");
    }

    public static void editUserData(Scanner scanner, Library library) {
        System.out.println("Знайдемо користувача. Для цього введіть номер заликової книжки.");
        String recordNumber = scanner.next();
        for (User user:library.getUsers()) {
            if (user.getRecordNumber().equals(recordNumber)) {
                System.out.println(userDataPrint(user)); // Виводимо збережені дані користувача
                System.out.println("Які данні ви бажайте відрагувати?\n" +
                        "1 - Імя; 2 - Прізвища; 3 - Побатькові; 4 - Номер групи; 5 - Email; 6 - Вийти");
                int userFieldChoice = scanner.nextInt();
                switch (userFieldChoice) {
                    case 1: // Імя
                        System.out.println("Ведіть нове імя");
                        user.setFirstName(scanner.next());
                        break;
                    case 2: // Прізвища
                        System.out.println("Ведіть нове прізвище");
                        user.setLastName(scanner.next());
                        break;
                    case 3: // Побатькові
                        System.out.println("Ведіть нове побатькові");
                        user.setSurname(scanner.next());
                        break;
                    case 4: // Номер групи
                        System.out.println("Ведіть новий номер групи");
                        user.setGroup(scanner.next());
                        break;
                    case 5: // Email
                        System.out.println("Ведіть новий email");
                        user.setEmail(scanner.next());
                        break;
                    case 6: // Вийти
                        DataManipulation.SaveDataToFile(library);
                        break;
                    default:
                        System.out.println("Помилка! Повторіть введення...");
                        break;
                }
                System.out.println("Данні користувача з номером залікової книжки " + recordNumber + " відредаговано.");
                System.out.println(userDataPrint(user));
            }
        }
    }

    public static void deleteUser(Scanner scanner, Library library) {
        System.out.println("Знайдемо користувача. Для цього введіть номер заликової книжки.");
        String recordNumber = scanner.next();
        boolean isDeleted = false; // Флаг для виходу з ціклу для забобігання ConcurrentModificationException
        for (User user : library.getUsers()) {
            if (user.getRecordNumber().equals(recordNumber)) {
                System.out.println("Перевірте данні користувача для видалення.");
                System.out.println(userDataPrint(user));
                System.out.println("Yes - Видалити користувача; No - Не видаляти користувача");
                String choice = scanner.next();
                switch (choice) {
                    case "Yes":
                        System.out.println("Користувача видалено");
                        library.deleteUser(user);
                        isDeleted = true;
                        break;
                    case "No":
                        System.out.println("Користувача не видалений");
                        break;
                    default:
                        System.out.println("Помилка! Повторіть введення...");
                        break;
                }
                if (isDeleted) break; // Вихід з циклу після видаленя користувача
            }
        }
    }
    //</editor-fold>

    // Метод що викликае метод запису данних у файл та виводи сповіщення
    public static void SaveDataToFile(Library library) {
        FileManager.saveFile("lib_data.json", library);
        System.out.println("Данні записані. Виходимо з програми...");
    }

    public static void issueBook(Scanner scanner, Library library) {
        System.out.println("Знайдемо книжку. Для цього введіть назву книжки.");
        String title = scanner.next();
        Book bookForIssue = null; // todo обробити щоб не null
        User userForIssue = null; // todo обробити щоб не null
        boolean isIssued = false; // Флаг для виходу з ціклу для забобігання ConcurrentModificationException
        for (Book book : library.getBooks()) {
            if (book.getTitle().equals(title)) {
                bookForIssue = book;
                System.out.println("Перевірте данні книжки для видачи.");
                System.out.println(bookDataPrint(book));
            }
        }

        System.out.println("Знайдемо користувача. Для цього введіть номер залікової книжки.");
        String record = scanner.next();
        for (User user : library.getUsers()) {
            if (user.getRecordNumber().equals(record)) {
                userForIssue = user;
                System.out.println("Перевірте данні книжки для видачи.");
                System.out.println(userDataPrint(user));
            }
        }

        System.out.println("Yes - Видати книжку; No - Не видавати книжку");
        String choice = scanner.next();
        switch (choice) {
            case "Yes":
                System.out.println("Книжка видана.");
                library.addIssuedBook(userForIssue, bookForIssue);
                isIssued = true;
                break;
            case "No":
                System.out.println("Книжка не видана");
                break;
            default:
                System.out.println("Помилка! Повторіть введення...");
                break;
        }
    }

    public static void receiptBook(Scanner scanner, Library library) {
        // todo
    }
}
