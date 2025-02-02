package Utils;

import java.util.Scanner;

public class DataCheck {
    public static final String errorColor = "\u001B[1;30;41m";
    public static final String reset = "\u001B[0m";

    public static String error(String str) {
        return errorColor + " ПОМИЛКА! Невірні данні... " + (str.length() > 0 ? str + " " : "") + "Повторіть ведення. " + reset;
    }

    //<editor-fold desc="User data check">
    static String strData;

    public static Integer menuNumberCheck(Scanner scanner, int maxMenuNumber){
        int number = 0;
        while (true) {
            strData = scanner.nextLine();
            if (!strData.matches("^\\d{1}$")) {
                System.out.println(error(""));
                continue;
            }
            number = Integer.parseInt(strData);
            if (number <= 0 || number > maxMenuNumber) {
                System.out.println(error(""));
                continue;
            }
            break;
        }
        return number;
    }

    public static String recordNumberCheck(Scanner scanner){
        while (!(strData = scanner.nextLine()).matches("^\\d{6}$")) {
            System.out.println(error("Номер залікоки складается із 6 цифір."));
        }
        return strData;
    }

    public static String nameCheck(Scanner scanner){
        while (!(strData = scanner.nextLine()).matches("^[А-ЯЇЄІЇҐЁ][а-яїєіїґ'ё]*$")) {
            System.out.println(error("Ім'я/призвище/побаткові складється із букв кирилиці," +
                    " не може включати пробіли, цифри та символи."));
        }
        return strData;
    }

    public static String groupCheck(Scanner scanner){
        while (!(strData = scanner.nextLine()).matches("^\\d{3}$")) {
            System.out.println(error("Номер залікоки складается із 3 цифір."));
        }
        return strData;
    }

    public static String emailCheck(Scanner scanner){
        while (!(strData = scanner.nextLine()).matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            System.out.println(error("Адреса електроної пошти складается локальної частини, символа @ та доменної частини."));
        }
        return strData;
    }
    //</editor-fold>

    public static int bookIDCheck(Scanner scanner){
        while (!(strData = scanner.nextLine()).matches("^\\d{6}$")) {
            System.out.println(error("Номер заліковки складается із 6 цифір."));
        }
        return Integer.parseInt(strData);
    }


    public static String authorNameCheck(Scanner scanner){
        while (!(strData = scanner.nextLine()).matches("^[A-ZА-ЯЇЄІЇҐЁ][a-zа-яїєіїґ'ё]+(?:[- ][A-ZА-ЯЇЄІЇҐЁ][a-zа-яїєіїґ'ё]+)*(?:,? [A-ZА-ЯЇЄІЇҐЁ]\\.? [A-ZА-ЯЇЄІЇҐЁ]\\.?)?$")) {
            System.out.println(error("Імена авторів повинні будти веденні у форматі \"Анна-Мария В. К.\". " +
                    "Не допускаються цифри та інші знаки"));
        }
        return strData;
    }

    public static int pagesCheck(Scanner scanner){
        while (!(strData = scanner.nextLine()).matches("^[1-9]\\d{0,2}$")) {
            System.out.println(error("Кількість сторінок містить тільки 3 цифри." +
                    "Початок з нуля не допускается"));
        }
        return Integer.parseInt(strData);
    }

    public static boolean availableCheck(Scanner scanner){
        while (!(strData = scanner.nextLine()).matches("^(true|false)$")) {
            System.out.println(error("Строка вводу має містити тільки слова \"true\", або \"false\"."));
        }
        return Boolean.parseBoolean(strData);
    }
}
