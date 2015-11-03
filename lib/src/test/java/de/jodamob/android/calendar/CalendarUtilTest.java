package de.jodamob.android.calendar;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class CalendarUtilTest {

    @Test
    public void rewindToBeginningOfDay() {
        Calendar cal = Calendar.getInstance();
        cal.set(2014, 9, 23, 1, 28, 50);
        Date out = CalendarUtil.rewindToBeginningOfDay(cal.getTime());
        cal.setTime(out);
        assertEquals(23, cal.get(GregorianCalendar.DAY_OF_MONTH));
        assertEquals(9, cal.get(GregorianCalendar.MONTH));
        assertEquals(2014, cal.get(GregorianCalendar.YEAR));
        assertEquals(0, cal.get(GregorianCalendar.MINUTE));
        assertEquals(0, cal.get(GregorianCalendar.SECOND));
    }
}
