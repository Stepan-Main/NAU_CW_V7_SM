package Models;

import java.io.Serializable;

public class User implements Serializable {
    //<editor-fold desc="Class fields">

    // Ідентефікатор коритувача у Бібліотеці
    // номер заліковиї книжки
    private String recordNumber;
    // Імя користувача
    private String firstName;
    // Прізвище коистувача
    private String lastName;
    // Побатькові користувача
    private String surname;
    // Номер групи користувача
    private String group;
    // електронна пошта для сповіщень
    private String email;
    //</editor-fold>

    //<editor-fold desc="Class constructor">
    // Конструктор класу User
    public User(String recordNumber, String firstName, String lastName, String surname, String group, String email) {
        this.recordNumber = recordNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.surname = surname;
        this.group = group;
        this.email = email;
    }
    //</editor-fold>

    //<editor-fold desc="Setters & Getters">
    public String getRecordNumber() {
        return recordNumber;
    }

    public void setRecordNumber(String recordNumber) {
        this.recordNumber = recordNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    //</editor-fold>


    @Override
    public String toString() {
        return recordNumber + " " +
                firstName + " " +
                lastName + " " +
                surname + " " +
                group + " " +
                email;
    }
}
