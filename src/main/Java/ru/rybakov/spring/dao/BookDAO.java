package ru.rybakov.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.rybakov.spring.model.Book;
import ru.rybakov.spring.model.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getBooks(){
        return jdbcTemplate.query("select * from book",
                new BeanPropertyRowMapper<>(Book.class));
    }
    public void save(Book book) {
        jdbcTemplate.update("Insert into Book(book_title, book_author, book_year) values (?, ?, ?)",
                book.getBook_title(),
                book.getBook_author(),
                book.getBook_year());
    }

    public Book getBook(int id) {
        return jdbcTemplate.query("Select * from Book where book_id =?", new Object[]{id},
                        new BeanPropertyRowMapper<>(Book.class))
                .stream()
                .findAny()
                .orElse(null);
    }

    public List<Book> getBooksOnUserId(int id){
        return new ArrayList<>(jdbcTemplate.query("Select * from Book where user_id = ?",
                new Object[]{id}, new BeanPropertyRowMapper<>(Book.class)));
    }

    public void editBook(int id, Book book) {
        jdbcTemplate.update("update Book set book_title =  ?,book_author = ?, book_year = ? where book_id = ?",
                book.getBook_title(),
                book.getBook_author(),
                book.getBook_year(),
                id);
    }
    public void giveBook(int id, Person person) {
        jdbcTemplate.update("update Book set user_id =  ? where book_id = ?",
                person.getId(),
                id);
    }
    public void delete(int id) {
        jdbcTemplate.update("DELETE from book where book_id = ?", id);
    }

    public void returnBook(int id) {

        jdbcTemplate.update("update Book set user_id =  ? where book_id = ?",
                null,
                id);
    }
}
