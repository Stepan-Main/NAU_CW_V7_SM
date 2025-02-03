package Services;

import Models.Library;
import Models.User;
import Utils.FileManager;

import java.util.List;
import java.util.Scanner;

import static Utils.DataCheck.*;
import static Utils.FileManager.saveDataToFile;

public class UserDataManipulation extends DataManipulation {

    //<editor-fold desc="Users Data Manipulation">
    public static void addNewUser(Scanner scanner, Library library) {
        System.out.println("Ввести данні користувача: ");
        System.out.print(" - номер залікової книжки: ");
        String recordNumber = recordNumberCheck(scanner);
        System.out.print(" - імя: ");
        String firstName = nameCheck(scanner);
        System.out.print(" - прізвище: ");
        String lastName = nameCheck(scanner);
        System.out.print(" - побатькові: ");
        String surname = nameCheck(scanner);
        System.out.print(" - група: ");
        String group = groupCheck(scanner);
        System.out.print(" - електронна пошта: ");
        String email = emailCheck(scanner);
        User user = new User(recordNumber, firstName, lastName, surname, group, email);
        library.addUser(user);
        System.out.println("Користувач " + user + " доданий в картотеку.");
        saveDataToFile(library);
    }

    public static void editUserData(Scanner scanner, Library library) {
        System.out.println("Знайдемо користувача. Для цього введіть номер заликової книжки.");
        String recordNumber = recordNumberCheck(scanner);
        for (User user:library.getUsers()) {
            if (user.getRecordNumber().equals(recordNumber)) {
                System.out.println(user); // Виводимо збережені дані користувача
                System.out.println("Які данні ви бажайте відрагувати?\n" + menuColor +
                        " 1 - Імя; 2 - Прізвища; 3 - Побатькові; 4 - Номер групи; 5 - Email; 6 - Вийти " + reset);
                switch (menuNumberCheck(scanner, 6)) {
                    case 1: // Імя
                        System.out.println("Ведіть нове імя");
                        user.setFirstName(nameCheck(scanner));
                        break;
                    case 2: // Прізвища
                        System.out.println("Ведіть нове прізвище");
                        user.setLastName(nameCheck(scanner));
                        break;
                    case 3: // Побатькові
                        System.out.println("Ведіть нове побатькові");
                        user.setSurname(nameCheck(scanner));
                        break;
                    case 4: // Номер групи
                        System.out.println("Ведіть новий номер групи");
                        user.setGroup(groupCheck(scanner));
                        break;
                    case 5: // Email
                        System.out.println("Ведіть новий email");
                        user.setEmail(emailCheck(scanner));
                        break;
                    case 6: // Вийти
                        FileManager.saveDataToFile(library);
                        break;
                    default:
                        System.out.println("Помилка! Повторіть введення...");
                        break;
                }
                System.out.println("Данні користувача з номером залікової книжки " + recordNumber + " відредаговано.");
                System.out.println(user);
            }
        }
        saveDataToFile(library);
    }

    public static void deleteUser(Scanner scanner, Library library) {
        System.out.println("Знайдемо користувача. Для цього введіть номер заликової книжки.");
        String recordNumber = recordNumberCheck(scanner);
        boolean isDeleted = false; // Флаг для виходу з ціклу для забобігання ConcurrentModificationException
        for (User user : library.getUsers()) {
            if (user.getRecordNumber().equals(recordNumber)) {
                System.out.println("Перевірте данні користувача для видалення.");
                System.out.println(user);
                System.out.println(menuColor + " Yes - Видалити користувача; No - Не видаляти користувача " + reset);
                String choice = scanner.nextLine();
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
        saveDataToFile(library);
    }

    public static void viewDataUser(Scanner scanner, Library library) {
        System.out.println("Знайдемо користувача. Для цього введіть його дані.");
        System.out.println("Імя: ");
        String firstName = nameCheck(scanner);
        System.out.println("Призвище: ");
        String lastName = nameCheck(scanner);
        System.out.println("Побатькові: ");
        String surname = nameCheck(scanner);
        User userFound = null;
        for (User user : library.getUsers()) {
            if (checkPart(user.getFirstName(), firstName)
                    && checkPart(user.getLastName(), lastName)
                    && checkPart(user.getSurname(), surname)) {
                userFound = user; // Коли знайшов користувача
                break;
            }
        }
        if (userFound != null) {
            System.out.println("Користувач знайдений");
            System.out.println(userFound);
        } else {
            System.out.println("Користувач не знайдений");
        }
    }

    public static void listUsers(Scanner scanner, Library library) {
        System.out.println(menuColor + " [ Список користувачів ]  1 - імені; 2 - прізвищу; 3 - академічній групі" + reset);
        List<User> users = library.getUsers();

        // Для сортування по користувачів по імені/прізвищю/групі використаем стандарні метод sort
        // Компаратор передаемо у вигляді лямда виразу
        switch (menuNumberCheck(scanner, 3)) {
            case 1:
                System.out.println(niceColor + "[ - - - - - - - - - - Список користувачів відсортуваний за іменем - - - - - - - - - - - ]" + reset);
                users.sort((b1, b2) -> b1.getFirstName().compareToIgnoreCase(b2.getFirstName()));
                for (User user: users) {
                    System.out.println(user);
                }
                System.out.println(niceColor + "[ - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ]" + reset);
            break;
            case 2:
                System.out.println(niceColor + "[ - - - - - - - - - - Список користувачів відсортуваний за прізвищем  - - - - - - - - - - ]" + reset);
                users.sort((b1, b2) -> b1.getLastName().compareToIgnoreCase(b2.getLastName()));
                for (User user: users) {
                    System.out.println(user);
                }
                System.out.println(niceColor + "[ - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ]" + reset);
            break;
            case 3:
                System.out.println(niceColor + "[ - - - - - - - - - - Список користувачів відсортуваний за групою - - - - - - - - - - ]" + reset);
                users.sort((b1, b2) -> b1.getGroup().compareToIgnoreCase(b2.getGroup()));
                for (User user: users) {
                    System.out.println(user);
                }
                System.out.println(niceColor + "[ - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ]" + reset);
            break;
        }
    }
    //</editor-fold>

    public static boolean checkPart(String textA, String textB) {
        return textA.toLowerCase().contains(textB.toLowerCase());
    }
}
