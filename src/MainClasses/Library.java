package MainClasses;

import CustomExceptions.LimitExceededException;
import MainClasses.Editions.Book;
import MainClasses.Users.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library {
    //<editor-fold desc="Class fields">
    private List<User> users = new ArrayList<>();;
    private List<Book> books = new ArrayList<>();
//    private Map<User, List<Book>> issuedBooks = new HashMap<>();
    private Map<String, List<Book>> issuedBooks = new HashMap<>();
    //</editor-fold>

//    public Library(List<User> users, List<Book> books, Map<User, List<Book>> issuedBooks) {
//        this.users = users;
//        this.books = books;
//        this.issuedBooks = issuedBooks;
//    }

    //<editor-fold desc="Library business-logic">
    // Метод видає книжку користувачу
    public void addIssuedBook(/*User user*/String userRecordNumber, Book book) throws LimitExceededException {
        // Внутрішній массив книжок отриманих користувачем
        List<Book> userBooks = issuedBooks.get(userRecordNumber);

        // Перевіряємо чи існує список книжок користувача щоб запобігти NullPointerException
        if (userBooks == null) {
            // Якщо список не існує додаємо порожній список
            userBooks = new ArrayList<>();
            // Додаємо список в карточку користувача
            issuedBooks.put(userRecordNumber, userBooks);
        }

        // Перевірка кількості виданих книжок для користувача
        // Викинемо виняток LimitExceededException якщо користувач уже узяв більше ніж 5 книжок.
        if (userBooks.size() >= 5) {
            throw new LimitExceededException("Кристувач не може отрмати більш ниж 5 книжок.");
        }

        // Додавання книжки до списку користувача
        if (userBooks.add(book)) {
            // Якщо видача пройшла успішно
            // Встановлюємо помітку що книжка видана і більше недоступна
            book.setAvailable(false);
        }
    }

    // Метод повертає книжку у бібліотеку
    public void returnBook(User user, Book book) {
        // Внутнішний массив книжок отриманих користувачем
        //List<Book> userBooks = issuedBooks.get(user);

        // Якщо список книжок користувача не пустий
        // Вдаляємо книжку із списку
        if (issuedBooks != null) {
            // Якщо видалення пройшло успішно помічаємо книжку як доступну
            // && issuedBooks.remove(book)
            List<Book> booksOnHand = issuedBooks.get(user.getRecordNumber());
            for (int i = 0; i < booksOnHand.size(); i++) {
                if (book.getId() == booksOnHand.get(i).getId()) {
                    booksOnHand.remove(i);
                    break;
                }
            }
            book.setAvailable(true);
        }
    }
    //</editor-fold>

    //<editor-fold desc="User business logic">
    // Додаємо користвача у картотеку бібліотеки
    public boolean addUser(User user) {
        return users.add(user);
    }

    public List<User> getUsers() {
        return users;
    }

    // Додаємо користвача у картотеку бібліотеки
    public boolean deleteUser(User user) {
        return users.remove(user);
    }
    //</editor-fold>

    //<editor-fold desc="Book business logic">
    // Додаємо книгу у список книжок бібліотеки
    public boolean addBook(Book book) {
        return books.add(book);
    }

    public List<Book> getBooks() {
        return books;
    }

    public Map<String, List<Book>> getIssuedBooks() {
        return issuedBooks;
    }

    public boolean deleteBook(Book book) {
        return books.remove(book);
    }
    //</editor-fold>
}
