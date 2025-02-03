package Services;

import Models.Library;
import Models.User;

public abstract class DataManipulation {

    public static final String menuColor = "\u001B[3;30;44m";
    public static final String niceColor = "\u001B[3;30;42m";
    public static final String errorColor = "\u001B[1;30;41m";
    public static final String reset = "\u001B[0m";

    //<editor-fold desc="Print Data">
    public static void printUsersList(Library library) {
        System.out.println(niceColor + "---------------------- Користувачі ----------------------" + reset);
        for (User user : library.getUsers()) {
            System.out.println(user);
        }
        System.out.println(niceColor + "---------------------------------------------------------" + reset);
    }

    public static void printList(String list, String listTitle) {
        if (list.length() > 0) {
            System.out.println(niceColor + "[ - - - - - - - - - - " + listTitle + " - - - - - - - - - - ]" + reset);
            System.out.println(list);
            System.out.println(niceColor + "[ - - - - - - - - - - - " + "- ".repeat((listTitle.length() + 1) / 2) + "- - - - - - - - - ]" + reset);
        } else {
            System.out.println("Не знайдено");
        }
    }
    //</editor-fold>
}
