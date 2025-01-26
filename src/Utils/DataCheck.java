package Utils;

import java.util.Scanner;

public class DataCheck {
    public static final String errorColor = "\u001B[1;31m";
    public static final String reset = "\u001B[0m";

    public static String error(String str) {
        return errorColor + "ПОМИЛКА! Невірні данні..." + str + "\nПовторіть ведення." + reset;
    }

    //<editor-fold desc="User data check">
    static String strData;
    public static String recordNumberCheck(Scanner scanner){
        while (!(strData = scanner.next()).matches("^\\d{6}$")) {
            System.out.println(error("\nНомер залівковки складается із 6 цифір."));
        }
        return strData;
    }

    public static String nameCheck(Scanner scanner){
        while (!(strData = scanner.next()).matches("^[А-ЯЇЄІЇҐЁ][а-яїєіїґ'ё]*$")) {
            System.out.println(error("\nІм'я/призвище/побаткові складється із букв кирилиці і починається з великої літири," +
                    "\nне може включати пробіли, цифри та символи."));
        }
        return strData;
    }

    public static String groupCheck(Scanner scanner){
        while (!(strData = scanner.next()).matches("^\\d{3}$")) {
            System.out.println(error("\nНомер залівковки складается із 3 цифір."));
        }
        return strData;
    }

    public static String emailCheck(Scanner scanner){
        while (!(strData = scanner.next()).matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            System.out.println(error("\nАдреса електроної пошти складается локальної частини, символа @ та доменної частини які записані латиницею."));
        }
        return strData;
    }
    //</editor-fold>

    public static int bookIDCheck(Scanner scanner){
        while (!(strData = scanner.next()).matches("^\\d{6}$")) {
            System.out.println(error("\nНомер залівковки складается із 6 цифір."));
        }
        return Integer.parseInt(strData);
    }


    public static String authorNameCheck(Scanner scanner){ // todo Не працює. Можливо через української літери
        while (!(strData = scanner.nextLine()).matches("^[A-ZА-ЯЇЄІЇҐЁ][a-zа-яїєіїґ'ё]+(?:[- ][A-ZА-ЯЇЄІЇҐЁ][a-zа-яїєіїґ'ё]+)*(?:,? [A-ZА-ЯЇЄІЇҐЁ]\\.? [A-ZА-ЯЇЄІЇҐЁ]\\.?)?$")) {
            System.out.println(error("\nІмена авторів повинні будти веденні у форматі \"Анна-Мария В. К.\". " +
                    "допускается як киритилиця так и латилиця" +
                    "Не допускаються цифри та інші знаки"));
        }
        return strData;
    }

    public static int pagesCheck(Scanner scanner){
        while (!(strData = scanner.next()).matches("^[1-9]\\d{0,2}$")) {
            System.out.println(error("\nКількість сторінок містить тільки 3 цифри." +
                    "Початок з нуля не допускается"));
        }
        return Integer.parseInt(strData);
    }

    public static boolean availableCheck(Scanner scanner){
        while (!(strData = scanner.next()).matches("^(true|false)$")) {
            System.out.println(error("\nСтрока вводу має містити тільки слова \"true\", або \"false\"." +
                    "Інші значення не допускается."));
        }
        return Boolean.parseBoolean(strData);
    }



}
