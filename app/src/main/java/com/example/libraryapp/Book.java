package com.example.libraryapp;

public class Book {
    private int id;
    private String title;
    private String author;
    private String imageURL;
    private String shortDec;
    private String longDec;
    private int pages;

    public Book(int id, String title, String author, String imageURL, String shortDec, String longDec, int pages) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.imageURL = imageURL;
        this.shortDec = shortDec;
        this.longDec = longDec;
        this.pages = pages;
    }

    public String getShortDec() {
        return shortDec;
    }

    public String getAuthor() {
        return author;
    }

    public int getId() {
        return id;
    }

    public String getLongDec() {
        return longDec;
    }

    public int getPages() {
        return pages;
    }

    public String getTitle() {
        return title;
    }

    public String getImageURL() {
        return imageURL;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", shortDec='" + shortDec + '\'' +
                ", longDec='" + longDec + '\'' +
                ", pages=" + pages +
                '}';
    }
}
