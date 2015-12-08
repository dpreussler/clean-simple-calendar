package de.jodamob.android.calendar;

import static org.fest.assertions.api.Assertions.assertThat;

import org.junit.Test;

import java.util.Calendar;
import java.util.Locale;

public class CalendarDataFactoryImplTest {

    private static final int FIVE_ROWS = 5;
    private static final int SIX_ROWS = 6;

    @Test
    public void should_create_for_germany1() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2015, 1, 1);  // feb 2015, has 28 days, jan has 31, first day was sun
        VisibleMonths months = CalendarDataFactory.getInstance(Locale.GERMANY).create(calendar.getTime(), FIVE_ROWS);

        assertThat(months.getLastMonth().size()).isEqualTo(6);
        assertThat(months.getCurrentMonth().size()).isEqualTo(28);
        assertThat(months.getNextMonth().size()).isEqualTo(1);

        assertThat(months.getLastMonth().getAt(0).getDay()).isEqualTo(26);
        assertThat(months.getCurrentMonth().getAt(0).getDay()).isEqualTo(1);
        assertThat(months.getNextMonth().getAt(0).getDay()).isEqualTo(1);

        assertThat(months.getDayNames().size()).isEqualTo(7);
    }

    @Test
    public void should_create_for_germany2() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2015, 9, 1);  // oct 2015, has 31 days, Sept has 30, first day was thu
        VisibleMonths months = CalendarDataFactory.getInstance(Locale.GERMANY).create(calendar.getTime(), FIVE_ROWS);

        assertThat(months.getLastMonth().size()).isEqualTo(3);
        assertThat(months.getCurrentMonth().size()).isEqualTo(31);
        assertThat(months.getNextMonth().size()).isEqualTo(1);

        assertThat(months.getDayNames().size()).isEqualTo(7);
    }

    @Test
    public void should_rewind_to_beginning_of_month() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2015, 10, 2);  // oct 2015, has 31 days, jan has 31, first day was sun
        VisibleMonths months = CalendarDataFactory.getInstance(Locale.GERMANY).create(calendar.getTime(), SIX_ROWS);

        assertThat(months.getLastMonth().size()).isEqualTo(6);
        assertThat(months.getCurrentMonth().size()).isEqualTo(30);
        assertThat(months.getNextMonth().size()).isEqualTo(6);
    }

    @Test
    public void should_create_for_us() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2015, 1, 1);  // feb 2015, has 28 days, jan has 31, first day was sun
        VisibleMonths months = CalendarDataFactory.getInstance(Locale.US).create(calendar.getTime(), FIVE_ROWS);

        assertThat(months.getLastMonth().size()).isEqualTo(0);
        assertThat(months.getCurrentMonth().size()).isEqualTo(28);
        assertThat(months.getNextMonth().size()).isEqualTo(7);

        assertThat(months.getDayNames().size()).isEqualTo(7);

        assertThat(months.getDayNames().get(0)).isEqualTo("SUN");
        assertThat(months.getDayNames().get(1)).isEqualTo("MON");
    }
}