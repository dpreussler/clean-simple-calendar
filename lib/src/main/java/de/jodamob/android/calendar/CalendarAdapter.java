package de.jodamob.android.calendar;

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
    protected final VisibleMonths data;
    private int selectedPosition = -1;

    public CalendarAdapter(@LayoutRes int layout, LayoutInflater inflater, VisibleMonths data) {
        this.layout = layout;
        this.inflater = inflater;
        this.data = data;
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

    @Override
    public int getItemCount() {
        return data.getCount();
    }

    public void select(int position) {
        setSelection(position);
        notifyDataSetChanged();
    }

    @VisibleForTesting
    void setSelection(int position) {
        this.selectedPosition = position;
    }

    private void fillViewState(int position, int selectedPosition, VisibleMonths data, DayState state) {
        state.setIsSelected(position == selectedPosition);
        state.setIsCurrentMonth(data.isCurrentMonth(position));
        state.setIsToday(data.getAt(position).isToday());
    }
}
