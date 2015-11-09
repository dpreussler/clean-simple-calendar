package com.example.sample;

import android.view.View;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

import de.jodamob.android.calendar.CalendarDayViewHolder;
import de.jodamob.android.calendar.Day;
import de.jodamob.android.calendar.DayState;

public class StyledViewHolder extends CalendarDayViewHolder {

    private final TextView detailView;

    public StyledViewHolder(View itemView) {
        super(itemView);
        detailView  = (TextView) itemView.findViewById(R.id.date_details);
    }

    @Override
    public void bind(Day day, DayState state) {
        super.bind(day, state);
        detailView.setText("");
    }

    public void bindBirthday(Birthday birthday) {
        detailView.setText(birthday.name);
    }
}
