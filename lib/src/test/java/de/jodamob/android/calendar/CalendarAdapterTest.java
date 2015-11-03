package de.jodamob.android.calendar;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.view.LayoutInflater;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

public class CalendarAdapterTest {

    Day day = mock(Day.class);
    VisibleMonths months = mock(VisibleMonths.class);
    CalendarDayViewHolder viewHolder = mock(CalendarDayViewHolder.class);
    ArgumentCaptor<DayState> captor = ArgumentCaptor.forClass(DayState.class);
    CalendarAdapter tested = new CalendarAdapter(0, mock(LayoutInflater.class), months);


    @Before
    public void setup() {
        when(months.getAt(anyInt())).thenReturn(day);
    }

    @Test
    public void should_bind() {
        tested.onBindViewHolder(viewHolder, 10);
        verify(viewHolder).bind(eq(day), captor.capture());
        DayState state = captor.getValue();
        assertFalse(state.isSelected());
        assertFalse(state.isCurrentMonth());
        assertFalse(state.isToday());
    }

    @Test
    public void should_select_current_position() {
        tested.setSelection(10);
        tested.onBindViewHolder(viewHolder, 10);
        verify(viewHolder).bind(eq(day), captor.capture());
        DayState state = captor.getValue();
        assertTrue(state.isSelected());
    }

    @Test
    public void should_set_current_month() {
        when(months.isCurrentMonth(anyInt())).thenReturn(true);
        tested.onBindViewHolder(viewHolder, 10);
        verify(viewHolder).bind(eq(day), captor.capture());
        DayState state = captor.getValue();
        assertTrue(state.isCurrentMonth());
    }

    @Test
    public void should_mark_today() {
        when(day.isToday()).thenReturn(true);
        tested.onBindViewHolder(viewHolder, 10);
        verify(viewHolder).bind(eq(day), captor.capture());
        DayState state = captor.getValue();
        assertTrue(state.isToday());
    }
}