package ru.rybakov.spring.model;

import javax.validation.constraints.NotEmpty;


public class Book {
    int book_id;
    Integer user_id;

    @NotEmpty(message = "Title should not be empty")
    String book_title;
    @NotEmpty(message = "Author should not be empty")
    String book_author;
    String book_year;


    public Book() {
    }

    public Book(int book_id, Integer user_id, String book_title, String book_author, String book_year) {
        this.book_id = book_id;
        this.user_id = user_id;
        this.book_title = book_title;
        this.book_author = book_author;
        this.book_year = book_year;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getBook_title() {
        return book_title;
    }

    public void setBook_title(String book_title) {
        this.book_title = book_title;
    }

    public String getBook_author() {
        return book_author;
    }

    public void setBook_author(String book_author) {
        this.book_author = book_author;
    }

    public String getBook_year() {
        return book_year;
    }

    public void setBook_year(String book_year) {
        this.book_year = book_year;
    }
}
