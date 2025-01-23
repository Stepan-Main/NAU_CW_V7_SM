package Utils;

import java.util.Scanner;

public class DataCheck {
    //public static final String niceColor = "\u001B[3;30;42m";
    public static final String errorColor = "\u001B[1;31m";
    public static final String reset = "\u001B[0m";

    public static String error(String str) {
        return errorColor + "ПОМИЛКА! Невірні данні..." + str + "\nПовторіть ведення." + reset;
    }

    static String strData;
    public static String recordNumberCheck(Scanner scanner){
        while (!(strData = scanner.next()).matches("^\\d{6}$")) {
            System.out.println(error("\nНомер залівковки складается із 6 цифір."));
        }
        return strData;
    }

    public static String nameCheck(Scanner scanner){
        while (!(strData = scanner.next()).matches("^[А-ЯЁ][а-яё]*$")) {
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
}
