package test;

import MainClasses.Editions.Book;
import MainClasses.Library;
import MainClasses.Users.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LibraryTest {

    Library library;

    @BeforeEach
    void setUp() {
        library = new Library();
    }

    @Test
    void addUser() {
        Assertions.assertTrue(library.addUser(new User(
                "22222",
                "John",
                "Doe",
                "New",
                "206",
                "john@doe.com")));
    }

    @Test
    void addBook() {
        Assertions.assertTrue(library.addBook(new Book(
                234,
                "Book",
                "Athor",
                "Publisher",
                200,
                true
        )));
    }

    @Test
    void addIssuedBook() {
        //Assertions.assertTrue(library.addBook(new Book()))
    }

    @Test
    void returnBook() {
    }

    @AfterEach
    void tearDown() {
    }
}