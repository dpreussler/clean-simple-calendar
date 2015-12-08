package de.jodamob.android.calendar;

import java.util.Date;
import java.util.Locale;

public abstract class CalendarDataFactory {

    public static CalendarDataFactory getInstance(Locale locale) {
        return new CalendarDataFactoryImpl(locale);
    }

    public abstract VisibleMonths create(Date month, int rows);
}
