package com.example.libraryapp;

import java.util.ArrayList;

public class Database {
    private Category category;
    private ArrayList<Book> allBooks;
    private ArrayList<Book> currentBooks;
    private ArrayList<Book> wishlist;
    private ArrayList<Book> favorites;
    private ArrayList<Book> haveRead;

    private static Database database;

    public enum Category {
        ALL,
        FAVORITES,
        WISHLIST,
        CURRENTLY_READING,
        HAVE_READ,
        ABOUT,
    }

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
        if (null == database)
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
        for (Book b : allBooks)
            if (b.getId() == bookId)
                return b;

        return null;
    }

    public boolean hasRead(Book b) {
        for (Book book : haveRead)
            if (book.getId() == b.getId())
                return true;

        return false;
    }

    public ArrayList<Book> getBooks() {
        if (category == Category.ALL)
            return allBooks;
        if (category == Category.WISHLIST)
            return wishlist;
        if (category == Category.HAVE_READ)
            return haveRead;
        if (category == Category.FAVORITES)
            return favorites;
        if (category == Category.CURRENTLY_READING)
            return currentBooks;

        return null;
    }

    public boolean addToHaveRead(Book b) {
        return b != null && haveRead.add(b);
    }

    public boolean inWishlist(Book b) {
        for (Book book : wishlist)
            if (book.getId() == b.getId())
                return true;

        return false;
    }

    public boolean addToWishlist(Book b) {
        return b != null && wishlist.add(b);
    }

    public boolean isFavorite(Book b) {
        for (Book book : favorites)
            if (book.getId() == b.getId())
                return true;

        return false;
    }

    public boolean addToFavorites(Book b) {
        return b != null && favorites.add(b);
    }

    public boolean isReading(Book b) {
        for (Book book : currentBooks)
            if (book.getId() == b.getId())
                return true;

        return false;
    }

    public boolean addToCurrentlyReading(Book b) {
        return b != null && currentBooks.add(b);
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getCategoryName() {
        if (category == Category.ALL)
            return "All books";
        if (category == Category.WISHLIST)
            return "Wishlist";
        if (category == Category.HAVE_READ)
            return "Have read books";
        if (category == Category.FAVORITES)
            return "Favorite books";
        if (category == Category.CURRENTLY_READING)
            return "Currently reading books";

        return "About";
    }
}
