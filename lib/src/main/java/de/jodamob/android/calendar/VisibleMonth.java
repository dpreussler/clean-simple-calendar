package de.jodamob.android.calendar;

import java.util.ArrayList;
import java.util.List;

public class VisibleMonth {
    private final List<Day> days = new ArrayList<>();

    public VisibleMonth(List<Day> days) {
        this.days.addAll(days);
    }

    public int size() {
        return days.size();
    }

    public Day getAt(int position) {
        return days.get(position);
    }
}
