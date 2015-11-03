package de.jodamob.android.calendar;

import java.util.Calendar;
import java.util.Date;

public class Day {

    private final Calendar calendar = Calendar.getInstance();

    public Day(Date date) {
        calendar.setTime(date);
    }

    public int getDay() {
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public Date getDate() {
        return calendar.getTime();
    }

    public boolean isToday() {
        return CalendarUtil.isToday(calendar.getTime());
    }
}
