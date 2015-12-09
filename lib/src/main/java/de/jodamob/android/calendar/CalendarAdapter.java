package de.jodamob.android.calendar;

import static de.jodamob.android.calendar.CalendarUtil.isSameDay;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CalendarAdapter extends RecyclerView.Adapter {
    private final int layout;
    private final LayoutInflater inflater;
    protected VisibleMonths data;
    private int selectedPosition = -1;

    public CalendarAdapter(@LayoutRes int layout, LayoutInflater inflater, VisibleMonths data) {
        this.layout = layout;
        this.inflater = inflater;
        this.data = data;
    }

    public void replace(@NonNull VisibleMonths months) {
        this.data = months;
    }

    @Override
    public final RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return createViewHolder(inflater.inflate(layout, parent, false));
    }

    @NonNull
    protected RecyclerView.ViewHolder createViewHolder(View view) {
        return new CalendarDayViewHolder(view);
    }

    @Override
    public final void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CalendarDayViewHolder viewHolder = (CalendarDayViewHolder) holder;
        DayState state = new DayState();
        fillViewState(position, selectedPosition, data, state);
        viewHolder.bind(data.getAt(position), state);
        addCustomData(viewHolder, data.getAt(position));
    }

    protected void addCustomData(CalendarDayViewHolder viewHolder, Day day) {
    }

    protected  int getSelectedPosition() {
        return selectedPosition;
    }

    @Override
    public int getItemCount() {
        return data.getCount();
    }

    public void select(Day selectedDay) {
        int position = getPositionOf(selectedDay);
        if (position >= 0) {
            select(position);
        }
    }

    public void select(int position) {
        int oldSelection = selectedPosition;
        setSelection(position);
        notifyItemChanged(oldSelection);
        notifyItemChanged(selectedPosition);
    }

    @VisibleForTesting
    void setSelection(int position) {
        this.selectedPosition = position;
    }

    protected int getPositionOf(Day selectedDay) {
        int count = data.getCount();
        for (int i=0; i< count; i++) {
            if (isSameDay(data.getAt(i).getDate(), selectedDay.getDate())) {
                return i;
            }
        }
        return -1;
    }

    private void fillViewState(int position, int selectedPosition, VisibleMonths data, DayState state) {
        state.setIsSelected(position == selectedPosition);
        state.setIsCurrentMonth(data.isCurrentMonth(position));
        state.setIsToday(data.getAt(position).isToday());
    }
}
