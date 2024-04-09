package ru.rybakov.spring.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


public class Person {
    private int user_id;
    @NotEmpty(message = "FIO should not be empty")
    @Size(min = 2, max = 30, message = "FIO should be between 2 and 30 characters")
    private String user_fio;
    private String user_birthday;

    public Person(int user_id, String user_fio, String user_birthday) {
        this.user_id = user_id;
        this.user_fio = user_fio;
        this.user_birthday = user_birthday;
    }

    public Person() {
    }

    public int getId() {
        return user_id;
    }

    public void setId(int id) {
        this.user_id = id;
    }

    public String getFIO() {
        return user_fio;
    }

    public void setFIO(String FIO) {
        this.user_fio = FIO;
    }

    public String getBirthday() {
        return user_birthday;
    }

    public void setBirthday(String birthday) {
        this.user_birthday = birthday;
    }
}
