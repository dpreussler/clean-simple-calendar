package com.example.sample;

import java.util.Date;

public class Birthday {

    final Date date;
    final String name;

    public Birthday(String name, Date date) {
        this.name = name;
        this.date = new Date(date.getTime());
    }
}
