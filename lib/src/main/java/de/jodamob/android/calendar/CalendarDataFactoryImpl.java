package de.jodamob.android.calendar;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

final class CalendarDataFactoryImpl extends CalendarDataFactory {

    private static final int DAYS_PER_WEEK = 7;
    private final Locale locale;

    CalendarDataFactoryImpl(Locale locale) {
        this.locale = locale;
    }

    @Override
    public VisibleMonths create(Date month, int rows) {
        Calendar calendar = setToFirstDayOf(month);
        List<Day> lastMonth = calculateLastMonth(calendar);
        List<Day> currentMonth = calculateCurrentMonth(calendar);
        List<Day> nextMonth = calculateNextMonth(rows, lastMonth, currentMonth);
        List<String> dayNames = getDayNames(lastMonth, currentMonth);
        return new VisibleMonths(new VisibleMonth(lastMonth), new VisibleMonth(currentMonth), new VisibleMonth(nextMonth), dayNames);
    }

    @NonNull
    private List<String> getDayNames(List<Day> lastMonth, List<Day> currentMonth) {
        List<String> names = new ArrayList<>(DAYS_PER_WEEK);
        int i=0;
        for (; i<lastMonth.size() && i<DAYS_PER_WEEK; i++) {
            names.add(CalendarUtil.getDayOfWeekThreeChars(lastMonth.get(i).getDate(), locale));
        }
        for (; i<DAYS_PER_WEEK; i++) {
            names.add(CalendarUtil.getDayOfWeekThreeChars(currentMonth.get(i - lastMonth.size()).getDate(), locale));
        }
        return names;
    }

    @NonNull
    private Calendar setToFirstDayOf(Date month) {
        Calendar calendar = Calendar.getInstance(locale);
        Date firstDayOfThisMonth = CalendarUtil.rewindToBeginningOfMonth(month);
        calendar.setTime(firstDayOfThisMonth);
        return calendar;
    }

    @NonNull
    private List<Day> calculateNextMonth(int rows, List<Day> lastMonthDays, List<Day> days) {
        int maxDays = rows * DAYS_PER_WEEK;
        List<Day> nextMonth = new ArrayList<>(DAYS_PER_WEEK);
        Date firstDayOfNextMonth = CalendarUtil.getTomorrow(days.get(days.size() - 1).getDate());
        Calendar calendar = getCalendarFor(firstDayOfNextMonth);
        for (int i=0; i<maxDays-lastMonthDays.size()-days.size(); i++) {
            nextMonth.add(new Day(calendar.getTime()));
            calendar.add(Calendar.DATE, 1);
        }
        return nextMonth;
    }

    @NonNull
    private Calendar getCalendarFor(Date date) {
        Calendar calendar = Calendar.getInstance(locale);
        calendar.setTime(date);
        return calendar;
    }

    @NonNull
    private List<Day> calculateCurrentMonth(Calendar calendar) {
        int actualMaximum = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        return calculateCurrentMonth(actualMaximum, calendar.getTime());
    }

    @NonNull
    private List<Day> calculateCurrentMonth(int actualMaximum, Date date) {
        Calendar calendar = getCalendarFor(date);
        List<Day> days = new ArrayList<>(actualMaximum);
        for (int i=0; i< actualMaximum; i++) {
            days.add(new Day(calendar.getTime()));
            calendar.add(Calendar.DATE, 1);
        }
        return days;
    }

    @NonNull
    private List<Day> calculateLastMonth(Calendar calendar) {
        int firstDayOfWeek = calendar.getFirstDayOfWeek();
        int firstDay = calendar.get(Calendar.DAY_OF_WEEK);
        return calculateLastMonth(calendar.getTime(), firstDayOfWeek, firstDay);
    }

    @NonNull
    private List<Day> calculateLastMonth(Date firstDayOfThisMonth, int firstDayOfWeek, int firstDay) {
        List<Day> lastMonthDays= new ArrayList<>(DAYS_PER_WEEK);
        Date lastDayOfLastMonth = CalendarUtil.getYesterday(firstDayOfThisMonth);
        Calendar calendar = getCalendarFor(lastDayOfLastMonth);
        if (firstDay != firstDayOfWeek) {
            int diff = firstDay > firstDayOfWeek ? firstDay - firstDayOfWeek : DAYS_PER_WEEK - (firstDayOfWeek - firstDay);
            for (int i=diff-1; i>=0; i--) {
                calendar.add(Calendar.DATE, -i);
                lastMonthDays.add(new Day(calendar.getTime()));
                calendar.setTime(lastDayOfLastMonth);
            }
        }
        return lastMonthDays;
    }
}
