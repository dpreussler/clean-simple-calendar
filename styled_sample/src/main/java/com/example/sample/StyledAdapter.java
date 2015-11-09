package com.example.sample;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import java.util.Date;
import java.util.List;

import de.jodamob.android.calendar.CalendarAdapter;
import de.jodamob.android.calendar.CalendarUtil;
import de.jodamob.android.calendar.Day;
import de.jodamob.android.calendar.VisibleMonths;

import static de.jodamob.android.calendar.CalendarUtil.isSameDay;
import static de.jodamob.android.calendar.CalendarUtil.isSameDayIgnoreYear;

public class StyledAdapter extends CalendarAdapter {

    private final List<Birthday> birthdays;

    public StyledAdapter(@LayoutRes int layout, LayoutInflater inflater, VisibleMonths data, List<Birthday> birthdays) {
        super(layout, inflater, data);
        this.birthdays = birthdays;
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder createViewHolder(View view) {
        return new StyledViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List payloads) {
        super.onBindViewHolder(holder, position, payloads);
        Day day = data.getAt(position);
        for(Birthday birthday : birthdays) {
            if (isSameDayIgnoreYear(birthday.date, day.getDate())) {
                ((StyledViewHolder)holder).bindBirthday(birthday);
                break;
            }
        }
    }
}
