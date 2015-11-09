package com.example.sample;

import android.support.annotation.NonNull;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Celebraties {

    public static List<Birthday> getFebruaryBirthdays() {
        return Arrays.asList(
                new Birthday("Alice Cooper", getAliceCoopersBirthday()),
                new Birthday("Johnny Cash", getJohnnyCashsBirthday()),
                new Birthday("James Dean", getAJamesDeansBirthday()));
    }

    public static Date getAliceCoopersBirthday() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1948, 1, 4);  // 4.2.
        return calendar.getTime();
    }

    public static Date getAJamesDeansBirthday() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1931, 1, 8);  // 8.2
        return calendar.getTime();
    }

    public static Date getJohnnyCashsBirthday() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1932, 1, 26);  // 26.2.
        return calendar.getTime();
    }
}
