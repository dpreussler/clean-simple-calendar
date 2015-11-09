package de.jodamob.android.calendar;

import android.text.format.DateUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CalendarUtil {

    private static final DateFormat EEE_DAY_OF_WEEK_FORMAT = new SimpleDateFormat("EEE", Locale.getDefault());

    public static String getDayOfWeekThreeChars(Date date, Locale locale) {
        return EEE_DAY_OF_WEEK_FORMAT.format(date).toUpperCase(locale);
    }

    public static Date rewindToBeginningOfMonth(Date date) {
        Calendar cal = buildFromDate(rewindToBeginningOfDay(date));
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    public static Date rewindToBeginningOfDay(Date date) {
        Calendar cal = buildFromDate(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date getTomorrow(Date from) {
        Calendar c = buildFromDate(from);
        c.add(Calendar.DATE, 1);  // number of days to add
        return c.getTime();
    }

    public static Date getYesterday(Date from) {
        Calendar c = buildFromDate(from);
        c.add(Calendar.DATE, -1);  // number of days to add
        return c.getTime();
    }

    public static boolean isToday(Date time) {
        return isToday(time.getTime());
    }

    public static boolean isToday(long date) {
        return DateUtils.isToday(date);
    }

    public static boolean isSameDay(Date date1, Date date2) {
        Calendar cal1 = buildFromDate(date1);
        Calendar cal2 = buildFromDate(date2);
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }

    public static boolean isSameDayIgnoreYear(Date date1, Date date2) {
        Calendar cal1 = buildFromDate(date1);
        Calendar cal2 = buildFromDate(date2);
        return cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }

    private static Calendar buildFromDate(Date from) {
        Calendar c = Calendar.getInstance();
        c.setTime(from);
        return c;
    }
}
