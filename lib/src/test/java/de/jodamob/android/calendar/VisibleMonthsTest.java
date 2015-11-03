package de.jodamob.android.calendar;

import static org.fest.assertions.api.Assertions.assertThat;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

public class VisibleMonthsTest {

    @Test
    public void should_calculate() {
        Date date1 = new Date(1000);
        Date date2 = new Date(2000);
        VisibleMonths tested = new VisibleMonths(
                new VisibleMonth(Collections.<Day>emptyList()),
                new VisibleMonth(Arrays.asList(new Day(date1), new Day(date2))),
                new VisibleMonth(Collections.<Day>emptyList()),
                Collections.<String>emptyList());
        assertThat(tested.getCount()).isEqualTo(2);
        assertThat(tested.getAt(0).getDate()).isEqualTo(date1);
        assertThat(tested.getAt(1).getDate()).isEqualTo(date2);
    }

    @Test
    public void should_calculate2() {
        Date date1 = new Date(1000);
        Date date2 = new Date(2000);
        Date date3 = new Date(3000);
        Date date4 = new Date(4000);
        VisibleMonths tested = new VisibleMonths(
                new VisibleMonth(Arrays.asList(new Day(date1), new Day(date2))),
                new VisibleMonth(Arrays.asList(new Day(date3))),
                new VisibleMonth(Arrays.asList(new Day(date4))),
                Collections.<String>emptyList());
        assertThat(tested.getCount()).isEqualTo(4);
        assertThat(tested.getAt(0).getDate()).isEqualTo(date1);
        assertThat(tested.getAt(1).getDate()).isEqualTo(date2);
        assertThat(tested.getAt(2).getDate()).isEqualTo(date3);
        assertThat(tested.getAt(3).getDate()).isEqualTo(date4);
    }
}