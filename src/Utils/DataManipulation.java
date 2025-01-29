package Utils;

import CustomExceptions.LimitExceededException;
import MainClasses.Editions.Book;
import MainClasses.Library;
import MainClasses.Users.User;

import javax.xml.crypto.Data;
import java.util.*;

public class DataManipulation {

    public static final String menuColor = "\u001B[3;30;44m";
    public static final String niceColor = "\u001B[3;30;42m";
    public static final String errorColor = "\u001B[3;30;47m";
    public static final String reset = "\u001B[0m";

    //<editor-fold desc="Users Data Manipulation">
    public static void printUsersList(Library library) {
        System.out.println(niceColor + "---------------------- Користувачі ----------------------" + reset);
        for (User user : library.getUsers()) {
            System.out.println(userDataPrint(user));
        }
        System.out.println(niceColor + "---------------------------------------------------------" + reset);
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
        String recordNumber = DataCheck.recordNumberCheck(scanner);
        System.out.print(" - імя: ");
        String firstName = DataCheck.nameCheck(scanner);
        System.out.print(" - прізвище: ");
        String lastName = DataCheck.nameCheck(scanner);
        System.out.print(" - побатькові: ");
        String surname = DataCheck.nameCheck(scanner);
        System.out.print(" - група: ");
        String group = DataCheck.groupCheck(scanner);
        System.out.print(" - електронна пошта: ");
        String email = DataCheck.emailCheck(scanner);
        User user = new User(recordNumber, firstName, lastName, surname, group, email);
        library.addUser(user);
        System.out.println("Користувач " + userDataPrint(user) + " доданий в картотеку.");
    }

    public static void editUserData(Scanner scanner, Library library) {
        System.out.println("Знайдемо користувача. Для цього введіть номер заликової книжки.");
        String recordNumber = DataCheck.recordNumberCheck(scanner);
        for (User user:library.getUsers()) {
            if (user.getRecordNumber().equals(recordNumber)) {
                System.out.println(userDataPrint(user)); // Виводимо збережені дані користувача
                System.out.println("Які данні ви бажайте відрагувати?\n" + menuColor +
                        " 1 - Імя; 2 - Прізвища; 3 - Побатькові; 4 - Номер групи; 5 - Email; 6 - Вийти " + reset);
                int userFieldChoice = scanner.nextInt();
                switch (userFieldChoice) {
                    case 1: // Імя
                        System.out.println("Ведіть нове імя");
                        user.setFirstName(DataCheck.nameCheck(scanner));
                        break;
                    case 2: // Прізвища
                        System.out.println("Ведіть нове прізвище");
                        user.setLastName(DataCheck.nameCheck(scanner));
                        break;
                    case 3: // Побатькові
                        System.out.println("Ведіть нове побатькові");
                        user.setSurname(DataCheck.nameCheck(scanner));
                        break;
                    case 4: // Номер групи
                        System.out.println("Ведіть новий номер групи");
                        user.setGroup(DataCheck.groupCheck(scanner));
                        break;
                    case 5: // Email
                        System.out.println("Ведіть новий email");
                        user.setEmail(DataCheck.emailCheck(scanner));
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
        String recordNumber = DataCheck.recordNumberCheck(scanner);
        boolean isDeleted = false; // Флаг для виходу з ціклу для забобігання ConcurrentModificationException
        for (User user : library.getUsers()) {
            if (user.getRecordNumber().equals(recordNumber)) {
                System.out.println("Перевірте данні користувача для видалення.");
                System.out.println(userDataPrint(user));
                System.out.println(menuColor + " Yes - Видалити користувача; No - Не видаляти користувача " + reset);
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

    public static void viewDataUser(Scanner scanner, Library library) {
        System.out.println("Знайдемо користувача. Для цього введіть його дані.");
        scanner.nextLine();
        System.out.println("Імя: ");
        String firstName = DataCheck.nameCheck(scanner);
        System.out.println("Призвище: ");
        String lastName = DataCheck.nameCheck(scanner);
        System.out.println("Побатькові: ");
        String surname = DataCheck.nameCheck(scanner);
        User userFound = null;
        for (User user : library.getUsers()) {
            if (user.getFirstName().equals(firstName) && user.getLastName().equals(lastName) && user.getSurname().equals(surname)) { // todo Зробити пошук по частині імені
                userFound = user; // Коли знайшов користувача
                break;
            }
        }
        if (userFound != null) {
            System.out.println("Користувач знайдений");
            System.out.println(userDataPrint(userFound));
        } else {
            System.out.println("Користувач не знайдений");
        }
    }

    public static void listUsers(Scanner scanner, Library library) {
        System.out.println(menuColor + "[ Список користувачів ] 0 - імені; 1 - прізвищу; 2 - академічній групі" + reset);
        List<User> users = library.getUsers();
        scanner.nextLine();
        int listType = -1;
        while (true) {
            listType = scanner.nextInt();
            if (listType == 1 || listType == 2 || listType == 3) break;
            else System.out.println(errorColor + "Невірний" + reset);
        }

        // Для сортування по користувачів по імені/прізвищю/групі використаем стандарні метод sort
        // Компаратор передаемо у вигляді лямда виразу
        switch (listType) {
            case 1:
                System.out.println(niceColor + "[ - - - - - - - - - - Список користувачів відсортуваний за іменем - - - - - - - - - - - ]" + reset);
                users.sort((b1, b2) -> b1.getFirstName().compareToIgnoreCase(b2.getFirstName()));
                for (User user: users) {
                    System.out.println(userDataPrint(user));
                }
                System.out.println(niceColor + "[ - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ]" + reset);
            break;
            case 2:
                System.out.println(niceColor + "[ - - - - - - - - - - Список користувачів відсортуваний за прізвищем  - - - - - - - - - - ]" + reset);
                users.sort((b1, b2) -> b1.getLastName().compareToIgnoreCase(b2.getLastName()));
                for (User user: users) {
                    System.out.println(userDataPrint(user));
                }
                System.out.println(niceColor + "[ - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ]" + reset);
            break;
            case 3:
                System.out.println(niceColor + "[ - - - - - - - - - - Список користувачів відсортуваний за групою - - - - - - - - - - ]" + reset);
                users.sort((b1, b2) -> b1.getGroup().compareToIgnoreCase(b2.getGroup()));
                for (User user: users) {
                    System.out.println(userDataPrint(user));
                }
                System.out.println(niceColor + "[ - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ]" + reset);
            break;
        }
    }
    //</editor-fold>

    //<editor-fold desc="Books Data Manipulation">
    public static void printBooksList(Library library) {
        System.out.println(niceColor + "---------------------- Книжки ----------------------" + reset);
        for (Book book : library.getBooks()) {
            System.out.println(bookDataPrint(book));
        }
        System.out.println(niceColor + "----------------------------------------------------" + reset);
    }



    public static void printIssuedBooksList(Library library) {
        System.out.println(niceColor + "---------------------- Виданні книжки ----------------------" + reset);
        for (Map.Entry<String, List<Book>> mapEntry: library.getIssuedBooks().entrySet()) {
            StringBuilder list = new StringBuilder();
            for (Book book : mapEntry.getValue()) {
                list.append(list.length() > 0 ? "; " : "").append(book.getTitle());
            }
            System.out.println(mapEntry.getKey() + " : " + list);
        }
        System.out.println(niceColor + "------------------------------------------------------------" + reset);
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
        int id = DataCheck.bookIDCheck(scanner);
        System.out.print(" - Назва книжки: ");
        // Перевіряти коретність введення назви книжки немає сенcу
        String title = scanner.next();
        System.out.print(" - Автори книжки: ");
        String author = DataCheck.authorNameCheck(scanner);
        System.out.print(" - Видаництво книжки: ");
        // Перевіряти коретність введення назви книжки немає сенcу
        String publisher = scanner.next();
        System.out.print(" - Кількість сторінок: ");
        int pages = DataCheck.pagesCheck(scanner);
        System.out.print(" - Наявність книжки: ");
        boolean available = DataCheck.availableCheck(scanner);
        Book book = new Book(id, title, author, publisher, pages, true);
        library.addBook(book);
        System.out.println("Книжка " + bookDataPrint(book) + " додана в бібліотеку.");
    }

    public static void editBookData(Scanner scanner, Library library) {
        System.out.println("Знайдемо книжку. Для цього введіть назву книжки.");
        // Перевіряти коретність введення назви книжки немає сенcу
        scanner.nextLine();
        String title = scanner.nextLine();
        for (Book book : library.getBooks()) {
            if (book.getTitle().equals(title)) { // todo Зробити пошук по частині назви
                System.out.println(bookDataPrint(book)); // Виводимо збережені дані книжки
                System.out.println("Які данні ви бажайте відрагувати?\n" + menuColor +
                        " 1 - ID; 2 - Назва; 3 - Автори; 4 - Видаництво; 5 - Кількість сторінок; 6 - Наявність; 7 - Вийти " + reset);
                int userFieldChoice = scanner.nextInt();
                switch (userFieldChoice) {
                    case 1: // ID
                        System.out.println("Ведіть нове ID");
                        book.setId(DataCheck.bookIDCheck(scanner));
                        break;
                    case 2: // Назва
                        System.out.println("Ведіть нову назву");
                        // Перевіряти коретність введення назви книжки немає сенcу
                        book.setTitle(scanner.next());
                        break;
                    case 3: // Автори
                        System.out.println("Ведіть нових авторів");
                        book.setAuthor(DataCheck.authorNameCheck(scanner));
                        break;
                    case 4: // Видаництво
                        System.out.println("Ведіть нове видаництво");
                        // Перевіряти коретність введення назви книжки немає сенcу
                        book.setPublisher(scanner.next());
                        break;
                    case 5: // Кількість сторінок
                        System.out.println("Ведіть кількість сторінок");
                        book.setPages(DataCheck.pagesCheck(scanner));
                        break;
                    case 6: // Наявність
                        System.out.println("Ведіть наявність (true/false)");
                        book.setAvailable(DataCheck.availableCheck(scanner));
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
        // Перевіряти коретність введення назви книжки немає сенcу
        scanner.nextLine();
        String title = scanner.nextLine();
        boolean isDeleted = false; // Флаг для виходу з ціклу для забобігання ConcurrentModificationException
        for (Book book : library.getBooks()) {
            if (book.getTitle().equals(title)) { // todo Зробити пошук по частині назви
                System.out.println("Перевірте данні книжки для видалення.");
                System.out.println(bookDataPrint(book));
                System.out.println(menuColor+" Yes - Видалити книжку; No - Не видаляти книжку "+reset);
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

    public static void viewDataBook(Scanner scanner, Library library) {
        System.out.println("Знайдемо книжку. Для цього введіть назву книжки.");
        // Перевіряти коретність введення назви книжки немає сенcу
        scanner.nextLine();
        String title = scanner.nextLine();
        Book bookFound = null;
        for (Book book : library.getBooks()) {
            if (book.getTitle().equals(title)) { // todo Зробити пошук по частині назви
                bookFound = book; // Коли знайшов книгу
                break;
            }
        }
        if (bookFound != null) {
            System.out.println("Книжка знайдена");
            System.out.println(bookDataPrint(bookFound));
        } else {
            System.out.println("Книжка не знайдена");
        }
    }

    public static void listBooks(Scanner scanner, Library library) {
        System.out.println(menuColor + "[ Список книг ] 1 - назві; 2 - авторів" + reset);
        List<Book> books = library.getBooks();
        scanner.nextLine();
        int listType = -1;
        while (true) {
            listType = scanner.nextInt();
            if (listType == 0 || listType == 1) break;
            else System.out.println(errorColor + "Невірний вибір. Повторіть..." + reset);
        }

        // Для сортування по автору оба по назві використаем стандарні метод sort
        // Компаратор передаемо у вигляді лямда виразу
        switch (listType) {
            case 1:
                System.out.println(niceColor + "[ - - - - - - - - - - Список книг відсортуваний за назвою - - - - - - - - - - - ]" + reset);
                books.sort((b1, b2) -> b1.getTitle().compareToIgnoreCase(b2.getTitle()));
                for (Book book: books) {
                    System.out.println(bookDataPrint(book));
                }
                System.out.println(niceColor + "[ - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ]" + reset);
            break;
            case 2:
                System.out.println(niceColor + "[ - - - - - - - - - - Список книг відсортуваний за автором  - - - - - - - - - - ]" + reset);
                books.sort((b1, b2) -> b1.getAuthor().compareToIgnoreCase(b2.getAuthor()));
                for (Book book: books) {
                    System.out.println(bookDataPrint(book));
                }
                System.out.println(niceColor + "[ - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ]" + reset);
            break;
        }
    }
    //</editor-fold>

    // Метод що викликае метод запису данних у файл та виводи сповіщення
    public static void SaveDataToFile(Library library) {
        FileManager.saveFile("lib_data.json", library);
        System.out.println(niceColor + " Данні записані." + reset);
    }

    //<editor-fold desc="Issue/Return">
    public static void issueBook(Scanner scanner, Library library) {
        Book bookForIssue = null; // todo обробити щоб не null
        //User userForIssue = null; // todo обробити щоб не null

        System.out.println("Знайдемо користувача. Для цього введіть номер залікової книжки.");
        String record = DataCheck.recordNumberCheck(scanner);
        for (User user : library.getUsers()) {
            if (user.getRecordNumber().equals(record)) {
                System.out.println("Перевірте данні користувача для видачи.");
                System.out.println(userDataPrint(user));
                break;
            }
        }

        System.out.println("Знайдемо книжку. Для цього введіть назву книжки.");
        // Перевіряти коретність введення назви книжки немає сенцу
        scanner.nextLine();
        String title = scanner.nextLine();
        for (Book book : library.getBooks()) {
            if (book.getTitle().equals(title)) {// todo Шукати по частині назви
                if (!book.isAvailable()) {
                    System.out.println("Книжки зараз немає у бібліотеці.");
                    break;
                }
                bookForIssue = book;
                System.out.println("Перевірте данні книжки для видачи.");
                System.out.println(bookDataPrint(book));
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
                        SaveDataToFile(library);
                    } catch (LimitExceededException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "No":
                    System.out.println("Книжка не видана");
                    break;
                default:
                    System.out.println(errorColor+" Помилка! Повторіть введення... "+reset);
                    break;
            }
        }
    }

    public static void receiptBook(Scanner scanner, Library library) {
        Book bookForReturn = null; // todo обробити щоб не null
        User userForReturn = null; // todo обробити щоб не null

        System.out.println("Знайдемо користувача. Для цього введіть номер залікової книжки.");
        String record = DataCheck.recordNumberCheck(scanner);
        for (User user : library.getUsers()) {
            if (user.getRecordNumber().equals(record)) {
                userForReturn = user;
                System.out.println("Перевірте данні ористувача.");
                System.out.println(userDataPrint(user));
                break;
            }
        }

        System.out.println("Знайдемо книжку. Для цього введіть назву книжки.");
        // Перевіряти коретність введення назви книжки немає сенцу
        scanner.nextLine();
        String title = scanner.nextLine();
        for (Book book : library.getBooks()) {
            if (book.getTitle().equals(title)) {
                bookForReturn = book;
                System.out.println("Перевірте данні книжки для прийому.");
                System.out.println(bookDataPrint(book));
                break;
            }
        }

        System.out.println(menuColor+" Yes - Прийняти книжку; No - Не прийняти книжку "+reset);
        String choice = scanner.next();
        switch (choice) {
            case "Yes":
                library.returnBook(userForReturn, bookForReturn);
                System.out.println("Книжка здана в бібліотеку.");
                break;
            case "No":
                System.out.println("Книжка не прийнята");
                break;
            default:
                System.out.println(errorColor+" Помилка! Повторіть введення... "+reset);
                break;
        }
    }

    public static void issueBookToUser(Scanner scanner, Library library) {
        User userInfo = null; // todo обробити щоб не null

        System.out.println("Знайдемо користувача. Для цього введіть номер залікової книжки.");
        String record = DataCheck.recordNumberCheck(scanner);
        for (User user : library.getUsers()) {
            if (user.getRecordNumber().equals(record)) {
                userInfo = user;
                System.out.println("Перевірте данні користувача.");
                System.out.println(userDataPrint(user));
                break;
            }
        }

        System.out.println(niceColor + "[ - - - - - - - - - - Книжки у користувача користувача - - - - - - - - - - - ]" + reset);
        List<Book> bookList = library.getIssuedBooks().get(record);
        if (bookList.size() > 0) {
            for (Book book: bookList) {
                System.out.println(bookDataPrint(book));
            }
        } else {
            System.out.println("Цей користувач не маю заброгодності книжкам.");
        }
        System.out.println(niceColor + "[ - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ]" + reset);
    }

    public static void availableBook(Scanner scanner, Library library) {
        Book bookInfo = null; // todo обробити щоб не null

        System.out.println("Знайдемо книжку. Для цього введіть назву книжки.");
        // Перевіряти коретність введення назви книжки немає сенцу
        scanner.nextLine();
        String title = scanner.nextLine();
        for (Book book : library.getBooks()) {
            if (book.getTitle().equals(title)) {
                bookInfo = book;
                System.out.println("Перевірте данні книжки.");
                System.out.println(bookDataPrint(book));
                break;
            }
        }

        System.out.println(niceColor + "[ - - - - - - - - - - Інформация про книжку - - - - - - - - - - - ]" + reset);
        if (bookInfo.isAvailable()) {
            System.out.println("Данна книжка знаходится бібліотеці.");
        } else {
            for (Map.Entry<String, List<Book>> mapEntry: library.getIssuedBooks().entrySet()) {
                for (Book book : mapEntry.getValue()) {
                    if (book.getId() == bookInfo.getId()) {
                        System.out.println("Данна книжка видана наступному користовачу:");
                        for (User user: library.getUsers()) {
                            if (user.getRecordNumber().equals(mapEntry.getKey())) {
                                System.out.println(userDataPrint(user));
                            }
                        }
                    }
                }
            }
        }
        System.out.println(niceColor + "[ - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ]" + reset);
    }
    //</editor-fold>
}
