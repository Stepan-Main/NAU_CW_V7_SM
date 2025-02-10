import CustomExceptions.NoLibFileException;
import Models.Library;
import UserInterface.Menu;
import Utils.FileManager;

import java.util.Scanner;

public class Main {

    public static final String niceColor = "\u001B[3;30;42m";
    public static final String errorColor = "\u001B[1;30;41m";
    public static final String reset = "\u001B[0m";

    public static void main(String[] args) {
        // Створюємо бібліотеку
        Library library;

        try {
            library = FileManager.loadFromFile("lib_data.json", Library.class);
            System.out.println(niceColor + " Дані бібліотеки успішно завантажено! " + reset);
        } catch (Exception e) {
            System.out.println(e);
            new NoLibFileException(errorColor + " Файл даних бібліотеки не знайдено! " + reset);
            library = new Library();
            System.out.println("Створено нову бібліотеку.");
        }

        // Створюємо сканер для читання данних з консолі
        Scanner scanner = new Scanner(System.in);

        // Нескінченний цикл для обробки
        while (true) {
            if (Menu.mainMenu(scanner, library)) return;
        }
    }

}