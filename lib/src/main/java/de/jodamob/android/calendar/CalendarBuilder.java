package de.jodamob.android.calendar;

import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;

import java.util.List;

public class CalendarBuilder {

    private final int layoutId;
    private final int headerLayoutId;

    public CalendarBuilder(@LayoutRes int layoutId, @LayoutRes int headerLayoutId) {
        this.layoutId = layoutId;
        this.headerLayoutId = headerLayoutId;
    }

    @LayoutRes
    public final int getLayout() {
        return layoutId;
    }

    public CalendarAdapter createAdapterFor(LayoutInflater inflater, VisibleMonths months) {
        return new CalendarAdapter(getLayout(), inflater, months);
    }

    public CalendarHeaderAdapter createHeaderAdapterFor(LayoutInflater inflater, List<String> dayNames) {
        return new CalendarHeaderAdapter(headerLayoutId, inflater, dayNames);
    }
}

