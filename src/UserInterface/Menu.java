package UserInterface;

import Models.Library;
import Services.BookDataManipulation;
import Services.IssueManipulation;
import Services.Searching;
import Utils.DataCheck;
import Services.DataManipulation;
import Utils.FileManager;

import java.util.Scanner;

import static Services.UserDataManipulation.*;

public class Menu {

    public static final String menuColor = "\u001B[3;30;44m";
    public static final String errorColor = "\u001B[1;30;41m";
    public static final String reset = "\u001B[0m";

    public static boolean mainMenu(Scanner scanner, Library library) {
        System.out.println(menuColor + " 1 - Видача/прийом книжок; 2 - Керування даними; 3 - Звітність; 4 - Пошук; 5 - Вийти " + reset);
        switch (DataCheck.menuNumberCheck(scanner, 5)) {
            case 1: // Видача/прийом книжок;
                System.out.println(menuColor + " 1 - Видача книжок; 2 - Прийом книжок; 3 - Видані користувачу; 4 - Наявність; 5 - Вийти " + reset);
                if (issueReceiptBooks(scanner, library)) return true;
                break;
            case 2: // 2 - Керування даними;
                System.out.println(menuColor + " 1 - Книжки; 2 - Користувачі; 3 - Вийти " + reset);
                if (dataManagement(scanner, library)) return true;
                break;
            case 3: // Звітність
                System.out.println(menuColor + " 1 - Список книжок; 2 - Список користувачів; 3 - Книжки у користувачів; 4 - Вийти " + reset);
                if (reporting(scanner, library)) return true;
                break;
            case 4:
                System.out.println(menuColor + " 1 - Пошук книжок; 2 - Пошук користувачів; 3 - Вийти " + reset);
                if (searching(scanner, library)) return true;
                break;
            case 5: // Вийти
                FileManager.saveDataToFile(library);
                return true;
            default:
                System.out.println(errorColor + "Помилка! Повторіть введення..." + reset);
                break;
        }
        return false;
    }

    private static boolean searching(Scanner scanner, Library library) {
        switch (DataCheck.menuNumberCheck(scanner, 4)) {
            case 1: // Книжки
                System.out.println(menuColor + " [ Документи пошук по ]  1 - Назві; 2 - Автору; 3 - Видавцю; 4 - Вийти " + reset);
                if (bookSearching(scanner, library)) return true;
                break;
            case 2: // Користувачі
                System.out.println(menuColor + " [ Користувачі пошук по ]  1 - Імені; 2 - Прізвищу; 3 - Побатькові; 4 - Група; 5 - Вийти " + reset);
                if (userSearching(scanner, library)) return true;
                break;
            case 3: // Вийти
                FileManager.saveDataToFile(library);
                return true;
            default:
                System.out.println(errorColor + " Помилка! Повторіть введення... " + reset);
                break;
        }
        return false;
    }

    private static boolean userSearching(Scanner scanner, Library library) {
        switch (DataCheck.menuNumberCheck(scanner, 5)) {
            case 1: // Пошук по Імені
                Searching.searchingByFirstName(scanner, library);
                break;
            case 2: // Пошук по Прізвищу
                Searching.searchingBySecondName(scanner, library);
                break;
            case 3: // Пошук по Побатькові
                Searching.searchingByThirdName(scanner, library);
                break;
            case 4: // Пошук по Групі
                Searching.searchingByGroup(scanner, library);
                break;
            case 5: // Вийти
                FileManager.saveDataToFile(library);
                return true;
            default:
                System.out.println(errorColor + " Помилка! Повторіть введення... " + reset);
                break;
        }
        return false;
    }

    private static boolean bookSearching(Scanner scanner, Library library) {
        switch (DataCheck.menuNumberCheck(scanner, 4)) {
            case 1: // Пошук по Назві
                Searching.searchingByTitle(scanner, library);
                break;
            case 2: // Пошук по Автору
                Searching.searchingByAuthor(scanner, library);
                break;
            case 3: // Пошук по Видавцю
                Searching.searchingByPublisher(scanner, library);
                break;
            case 4: // Вийти
                FileManager.saveDataToFile(library);
                return true;
            default:
                System.out.println(errorColor + " Помилка! Повторіть введення... " + reset);
                break;
        }
        return false;
    }

    public static boolean dataManagement(Scanner scanner, Library library) {
        String dataManagmentMenu = " 1 - Додати; 2 - Редагувати; 3 - Видалити; 4 - Переглянути; 5 - Список; 6 - Вийти ";
        switch (DataCheck.menuNumberCheck(scanner, 6)) {
            case 1: // Книжки
                System.out.println(menuColor + " [ Документи ] " + dataManagmentMenu + reset);
                if (bookManagement(scanner, library)) return true;
                break;
            case 2: // Користувачі
                System.out.println(menuColor + " [ Користувачі ] " + dataManagmentMenu + reset);
                if (userManagement(scanner, library)) return true;
                break;
            case 3: // Вийти
                FileManager.saveDataToFile(library);
                return true;
            default:
                System.out.println(errorColor + " Помилка! Повторіть введення... " + reset);
                break;
        }
        return false;
    }

    public static boolean userManagement(Scanner scanner, Library library) {
        switch (DataCheck.menuNumberCheck(scanner, 6)) {
            case 1: // Додати користувача
                addNewUser(scanner, library);
                break;
            case 2: // Редагувати дані користувача
                editUserData(scanner, library);
                break;
            case 3: // Видалити користувача
                deleteUser(scanner, library);
                break;
            case 4: // Переглянути дані користувача
                viewDataUser(scanner, library);
                break;
            case 5: // Список користувачів
                listUsers(scanner, library);
                break;
            case 6: // Вийти
                FileManager.saveDataToFile(library);
                return true;
            default:
                System.out.println(errorColor + " Помилка! Повторіть введення... " + reset);
                break;
        }
        return false;
    }

    public static boolean bookManagement(Scanner scanner, Library library) {
        switch (DataCheck.menuNumberCheck(scanner, 6)) {
            case 1: // Додати книжку
                BookDataManipulation.addNewBook(scanner, library);
                break;
            case 2: // Редагувати дані книжки
                BookDataManipulation.editBookData(scanner, library);
                break;
            case 3: // Видалити книжку
                BookDataManipulation.deleteBook(scanner, library);
                break;

            case 4: // Переглянути дані книжки
                BookDataManipulation.viewDataBook(scanner, library);
                break;
            case 5: // Список книжок
                BookDataManipulation.listBooks(scanner, library);
                break;

            case 6: // Вийти
                FileManager.saveDataToFile(library);
                return true;
            default:
                System.out.println(errorColor + " Помилка! Повторіть введення... " + reset);
                break;
        }
        return false;
    }

    public static boolean issueReceiptBooks(Scanner scanner, Library library) {
        switch (DataCheck.menuNumberCheck(scanner, 5)) {
            case 1: // Видача книжок
                IssueManipulation.issueBook(scanner, library);
                break;
            case 2: // Прийом книжок
                IssueManipulation.receiptBook(scanner, library);
                break;
            case 3: // Видача книжок
                IssueManipulation.issueBookToUser(scanner, library);
                break;
            case 4: // Прийом книжок
                IssueManipulation.availableBook(scanner, library);
                break;
            case 5: // Вийти
                FileManager.saveDataToFile(library);
                return true;
            default:
                System.out.println(errorColor + " Помилка! Повторіть введення... " + reset);
                break;
        }
        return false;
    }

    public static boolean reporting(Scanner scanner, Library library) {
        switch (DataCheck.menuNumberCheck(scanner, 4)) {
            case 1: // Надрукувати список книжок
                BookDataManipulation.printBooksList(library);
                break;
            case 2: // Надрукувати список користувачів
                DataManipulation.printUsersList(library);
                break;
            case 3: // Надрукувати список користувачів із списками виданних книжок
                BookDataManipulation.printIssuedBooksList(library);
                break;
            case 4: // Вийти
                FileManager.saveDataToFile(library);
                return true;
            default:
                System.out.println(errorColor + " Помилка! Повторіть введення... " + reset);
                break;
        }
        return false;
    }
}
