package de.jodamob.android.calendar;

import java.util.ArrayList;
import java.util.List;

public class VisibleMonths {

    private final VisibleMonth lastMonth;
    private final VisibleMonth thisMonth;
    private final VisibleMonth nextMonth;
    private final List<String> dayNames = new ArrayList<>();

    public VisibleMonths(VisibleMonth lastMonth, VisibleMonth thisMonth, VisibleMonth nextMonth, List<String> dayNames) {
        this.lastMonth = lastMonth;
        this.thisMonth = thisMonth;
        this.nextMonth = nextMonth;
        this.dayNames.addAll(dayNames);
    }

    public int getCount() {
        return lastMonth.size() + thisMonth.size() + nextMonth.size();
    }

    public List<String> getDayNames() {
        return dayNames;
    }

    public Day getAt(int position) {
        if (position < lastMonth.size()) {
            return lastMonth.getAt(position);
        }
        position -= lastMonth.size();
        if (position < thisMonth.size()) {
            return thisMonth.getAt(position);
        }
        position -= thisMonth.size();
        return nextMonth.getAt(position);
    }

    VisibleMonth getLastMonth() {
        return lastMonth;
    }

    VisibleMonth getCurrentMonth() {
        return thisMonth;
    }

    VisibleMonth getNextMonth() {
        return nextMonth;
    }

    public boolean isCurrentMonth(int position) {
        return position >= lastMonth.size() && position < (lastMonth.size()+thisMonth.size());
    }
}
