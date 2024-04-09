package ru.rybakov.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.rybakov.spring.model.Book;
import ru.rybakov.spring.model.Person;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> getPeople() {
        return new ArrayList<>(jdbcTemplate.query("Select * from Person",
                new PersonMapper()));
    }

    public Person getPerson(int id) {
        return jdbcTemplate.query("Select * from Person where user_id =?",
                new Object[]{id}, new PersonMapper())
                .stream()
                .findAny()
                .orElse(null);
    }

    public void editPerson(int id, Person person) {
        jdbcTemplate.update("update Person set user_FIO =  ?,user_birthday = ? where user_id = ?",
                person.getFIO(),
                person.getBirthday(),
                id);
    }

    public void save(Person person) {
        jdbcTemplate.update("Insert into Person(user_fio, user_birthday) values (?, ?)", person.getFIO(),
                person.getBirthday());
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE from person where user_id = ?", id);
    }

}
