package com.example.sample;

import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.List;

import de.jodamob.android.calendar.CalendarAdapter;
import de.jodamob.android.calendar.CalendarBuilder;
import de.jodamob.android.calendar.VisibleMonths;

public class StyledCalendarBuilder extends CalendarBuilder {

    private final List<Birthday> birthdays = new ArrayList<>();

    public StyledCalendarBuilder(List<Birthday> birthdays) {
        super(R.layout.calendar_item, R.layout.calendar_header);
        this.birthdays.addAll(birthdays);
    }

    @Override
    public CalendarAdapter createAdapterFor(LayoutInflater inflater, VisibleMonths months) {
        return new StyledAdapter(R.layout.calendar_item, inflater, months, birthdays);
    }
}
