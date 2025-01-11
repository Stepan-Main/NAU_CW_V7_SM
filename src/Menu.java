import CustomExceptions.LimitExceededException;
import MainClasses.Library;
import Utils.DataManipulation;

import java.util.Scanner;

public class Menu {
    public static boolean mainMenu(Scanner scanner, Library library) {
        System.out.println("1 - Видача/прийом книжок; 2 - Керування данними; 3 - Звідність; 4 - Вийти");
        int mainMenuChoice = scanner.nextInt();
        switch (mainMenuChoice) {
            case 1: // Видача/прийом книжок;
                System.out.println("1 - Видача книжок; 2 - Прийом книжок; 3 - Вийти");
                if (issueReceiptBooks(scanner, library)) return true;
                break;
            case 2: // 2 - Керування данними;
                System.out.println("1 - Книжки; 2 - Користувачі; 3 - Вийти");
                if (dataManagement(scanner, library)) return true;
                break;
            case 3: // Звідність
                System.out.println("1 - Список книжок; 2 - Список користувачів; 3 - Книжки у користувачів; 4 - Вийти");
                if (reporting(scanner, library)) return true;
                return true;
            case 4: // Вийти
                DataManipulation.SaveDataToFile(library);
                return true;
            default:
                System.out.println("Помилка! Повторіть введення...");
                break;
        }
        return false;
    }

    public static boolean dataManagement(Scanner scanner, Library library) {
        int dataManagementMenuChoice = scanner.nextInt();
        switch (dataManagementMenuChoice) {
            case 1: // Книжки
                System.out.println("1 - Додати книжку; 2 - Редагувати данні книжки; 3 - Видалити книжку; 4 - Вийти");
                if (bookManagement(scanner, library)) return true;
                break;
            case 2: // Користувачі
                System.out.println("1 - Додати користувача; 2 - Редагувати данні користувача; 3 - Видалити користувача; 4 - Вийти");
                if (userManagement(scanner, library)) return true;
                break;
            case 3: // Вийти
                DataManipulation.SaveDataToFile(library);
                return true;
            default:
                System.out.println("Помилка! Повторіть введення...");
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
            case 4: // Вийти
                DataManipulation.SaveDataToFile(library);
                return true;
            default:
                System.out.println("Помилка! Повторіть введення...");
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
            case 4: // Вийти
                DataManipulation.SaveDataToFile(library);
                return true;
            default:
                System.out.println("Помилка! Повторіть введення...");
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
                System.out.println("Помилка! Повторіть введення...");
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
                System.out.println("Помилка! Повторіть введення...");
                break;
        }
        return false;
    }
}
