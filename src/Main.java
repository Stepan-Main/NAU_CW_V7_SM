import CustomExceptions.NoLibFileException;
import MainClasses.Library;
import Utils.DataManipulation;
import Utils.FileManager;

import java.util.Scanner;

import static Utils.DataManipulation.*;

public class Main {
    public static void main(String[] args) {
        // Створюємо бібліотеку
        Library library;

        try {
            library = FileManager.loadFromFile("lib_data.json", Library.class);
            System.out.println("Дані бібліотеки успішно завантажено!");
        } catch (Exception e) {
            System.out.println(e);
            new NoLibFileException("Файл даних бібліотеки не знайдено!");
            library = new Library();
            System.out.println("Створено нову бібліотеку.");
            // todo Написати створеня файлу lib_data.json на диску
        } finally {

        }

        /*printUsersList(library);
        printBooksList(library);
        printIssuedBooksList(library);*/

        // Створюємо сканер для читання данниз з консолі
        Scanner scanner = new Scanner(System.in);

        // Нескінченний цикл для обробки
        while (true) {
            if (Menu.mainMenu(scanner, library)) return;
        }
    }

}