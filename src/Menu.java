import CustomExceptions.LimitExceededException;
import MainClasses.Library;
import Utils.DataManipulation;

import java.util.Scanner;

public class Menu {
    public static final String menuColor = "\u001B[3;30;44m";
    public static final String errorColor = "\u001B[3;30;47m";
    public static final String reset = "\u001B[0m";

    public static boolean mainMenu(Scanner scanner, Library library) {
        System.out.println(menuColor + " 1 - Видача/прийом книжок; 2 - Керування данними; 3 - Звідність; 4 - Вийти " + reset);
        int mainMenuChoice = scanner.nextInt();
        switch (mainMenuChoice) {
            case 1: // Видача/прийом книжок;
                System.out.println(menuColor + " 1 - Видача книжок; 2 - Прийом книжок; 3 - Вийти " + reset);
                if (issueReceiptBooks(scanner, library)) return true;
                break;
            case 2: // 2 - Керування данними;
                System.out.println(menuColor + " 1 - Книжки; 2 - Користувачі; 3 - Вийти " + reset);
                if (dataManagement(scanner, library)) return true;
                break;
            case 3: // Звідність
                System.out.println(menuColor + " 1 - Список книжок; 2 - Список користувачів; 3 - Книжки у користувачів; 4 - Вийти " + reset);
                if (reporting(scanner, library)) return true;
                break;
            case 4: // Вийти
                DataManipulation.SaveDataToFile(library);
                return true;
            default:
                System.out.println(errorColor + "Помилка! Повторіть введення..." + reset);
                break;
        }
        return false;
    }

    public static boolean dataManagement(Scanner scanner, Library library) {
        String dataManagmentMenu = " 1 - Додати; 2 - Редагувати; 3 - Видалити; 4 - Переглянути; 5 - Список; 6 - Вийти ";
        int dataManagementMenuChoice = scanner.nextInt();
        switch (dataManagementMenuChoice) {
            case 1: // Книжки
                System.out.println(menuColor + " [ Документи ] " + dataManagmentMenu + reset);
                if (bookManagement(scanner, library)) return true;
                break;
            case 2: // Користувачі
                System.out.println(menuColor + " [ Користувачі ] " + dataManagmentMenu + reset);
                if (userManagement(scanner, library)) return true;
                break;
            case 3: // Вийти
                DataManipulation.SaveDataToFile(library);
                return true;
            default:
                System.out.println(errorColor + " Помилка! Повторіть введення... " + reset);
                break;
        }
        return false;
    }

    public static boolean userManagement(Scanner scanner, Library library) {
        int userManagementMenuChoice = scanner.nextInt();
        switch (userManagementMenuChoice) {
            case 1: // Додати користувача
                DataManipulation.addNewUser(scanner, library);
                break;
            case 2: // Редагувати данні користувача
                DataManipulation.editUserData(scanner, library);
                break;
            case 3: // Видалити користувача
                DataManipulation.deleteUser(scanner, library);
                break;

            case 4: // Переглянути дані користувача
                DataManipulation.viewDataUser(scanner, library); // todo add method
                break;
            case 5: // Список користувачів
                DataManipulation.listUsers(scanner, library); // todo add method
                break;

            case 6: // Вийти
                DataManipulation.SaveDataToFile(library);
                return true;
            default:
                System.out.println(errorColor + " Помилка! Повторіть введення... " + reset);
                break;
        }
        return false;
    }

    public static boolean bookManagement(Scanner scanner, Library library) {
        int bookManagementMenuChoice = scanner.nextInt();
        switch (bookManagementMenuChoice) {
            case 1: // Додати книжку
                DataManipulation.addNewBook(scanner, library);
                break;
            case 2: // Редагувати данні книжки
                DataManipulation.editBookData(scanner, library);
                break;
            case 3: // Видалити книжку
                DataManipulation.deleteBook(scanner, library);
                break;

            case 4: // Переглянути дані книжки
                DataManipulation.viewDataBook(scanner, library); // todo add method
                break;
            case 5: // Список книжок
                DataManipulation.listBooks(scanner, library); // todo add method
                break;

            case 6: // Вийти
                DataManipulation.SaveDataToFile(library);
                return true;
            default:
                System.out.println(errorColor + " Помилка! Повторіть введення... " + reset);
                break;
        }
        return false;
    }

    public static boolean issueReceiptBooks(Scanner scanner, Library library) {
        int issueReceptionBooksChoice = scanner.nextInt();
        switch (issueReceptionBooksChoice) {
            case 1: // Видача книжок
                DataManipulation.issueBook(scanner, library);
                break;
            case 2: // Прийом книжок
                DataManipulation.receiptBook(scanner, library);
                break;
            case 3: // Вийти
                DataManipulation.SaveDataToFile(library);
                return true;
            default:
                System.out.println(errorColor + " Помилка! Повторіть введення... " + reset);
                break;
        }
        return false;
    }

    // Звідність
    public static boolean reporting(Scanner scanner, Library library) {
        int reportingChoice = scanner.nextInt();
        switch (reportingChoice) {
            case 1: // Надрукувати список книжок
                DataManipulation.printBooksList(library);
                break;
            case 2: // Надрукування список користувачів
                DataManipulation.printUsersList(library);
                break;
            case 3: // Надрукувати список користувачів із списками виданних книжок
                DataManipulation.printIssuedBooksList(library);
                break;
            case 4: // Вийти
                DataManipulation.SaveDataToFile(library);
                return true;
            default:
                System.out.println(errorColor + " Помилка! Повторіть введення... " + reset);
                break;
        }
        return false;
    }
}
