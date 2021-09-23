package com.example.libraryapp;

import java.util.ArrayList;

public class Database {
    private ArrayList<Book> allBooks;
    private ArrayList<Book> currentBooks;
    private ArrayList<Book> wishlist;
    private ArrayList<Book> favorites;
    private ArrayList<Book> haveRead;

    private static Database database;

    private Database() {
        allBooks = new ArrayList<>();
        wishlist = new ArrayList<>();
        currentBooks = new ArrayList<>();
        haveRead = new ArrayList<>();
        favorites = new ArrayList<>();

        addData();
    }

    private void addData() {
        String longDes = "this is the long dec blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah";

        allBooks.add(new Book(1, "first book", "Jack London", "https://bayrockbayrock.files.wordpress.com/2015/06/1q84-cover.jpg", "this is the first", longDes, 520));
        allBooks.add(new Book(2, "first", "me", "https://bayrockbayrock.files.wordpress.com/2015/06/1q84-cover.jpg", "this is the first", longDes, 520));
        allBooks.add(new Book(3, "first", "me", "https://bayrockbayrock.files.wordpress.com/2015/06/1q84-cover.jpg", "this is the first", longDes, 520));
        allBooks.add(new Book(4, "first", "me", "https://bayrockbayrock.files.wordpress.com/2015/06/1q84-cover.jpg", "this is the first", longDes, 520));
        allBooks.add(new Book(5, "first", "me", "https://bayrockbayrock.files.wordpress.com/2015/06/1q84-cover.jpg", "this is the first", longDes, 520));
        allBooks.add(new Book(6, "first", "me", "https://bayrockbayrock.files.wordpress.com/2015/06/1q84-cover.jpg", "this is the first", longDes, 520));
    }

    public static Database getDatabase() {
        if(null == database)
            database = new Database();

        return database;
    }

    public ArrayList<Book> getAllBooks() {
        return allBooks;
    }

    public ArrayList<Book> getCurrentBooks() {
        return currentBooks;
    }

    public ArrayList<Book> getWishlist() {
        return wishlist;
    }

    public ArrayList<Book> getFavorites() {
        return favorites;
    }

    public ArrayList<Book> getHaveRead() {
        return haveRead;
    }

    public Book findBook(int bookId) {
        for(Book b : allBooks)
            if(b.getId() == bookId)
                return b;

        return null;
    }
}
