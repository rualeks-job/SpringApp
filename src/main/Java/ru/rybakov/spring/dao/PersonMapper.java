package ru.rybakov.spring.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.rybakov.spring.model.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person person = new Person();

        person.setId(rs.getInt("user_id"));
        person.setFIO(rs.getString("user_fio"));
        person.setBirthday(rs.getString("user_birthday"));


        return person;
    }
}
