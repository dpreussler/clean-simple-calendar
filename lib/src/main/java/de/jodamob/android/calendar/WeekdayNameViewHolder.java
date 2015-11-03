package de.jodamob.android.calendar;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import calendar.android.jodamob.de.cleansimplecalendar.R;

public class WeekdayNameViewHolder extends RecyclerView.ViewHolder {

    private final TextView dayView;

    public WeekdayNameViewHolder(View itemView) {
        super(itemView);
        this.dayView = (TextView) itemView.findViewById(R.id.day);
    }

    public void bind(String day) {
        dayView.setText(day);
    }
}
