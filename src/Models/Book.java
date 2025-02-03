package Models;

import java.io.Serializable;

public class Book implements Serializable {
    //<editor-fold desc="Class fields">
    private int id;
    private String title;
    private String author;
    private String publisher;
    private int pages;
    private boolean available;
    //</editor-fold>

    //<editor-fold desc="Class constructor">
    public Book(int id, String title, String author, String publisher, int pages, boolean available) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.pages = pages;
        this.available = available;
    }
    //</editor-fold>

    //<editor-fold desc="Setters & Getters">
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
    //</editor-fold>

    @Override
    public String toString() {
        return id + " " +
                title + " " +
                author + " " +
                publisher + " " +
                pages + " " +
                available;
    }
}
