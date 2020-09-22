package com.epam.library.model;


public class UserBook extends Entity
{
    private long id;

    private Book book;

    private User user;


    public UserBook(long id, Book book, User user) {
        this.id = id;
        this.book = book;
        this.user = user;
    }

    public UserBook(Book book, User user) {
        this.book = book;
        this.user = user;
    }

    public UserBook() {
    }

    public long getId() {
        return id;
    }

    public Book getBook() {
        return book;
    }

    public User getUser() {
        return user;
    }
}
