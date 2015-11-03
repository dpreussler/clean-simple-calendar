package de.jodamob.android.calendar;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class CalendarHeaderAdapter extends RecyclerView.Adapter {

    private final int layout;
    private final LayoutInflater inflater;
    private final List<String> data = new ArrayList<>();

    public CalendarHeaderAdapter(@LayoutRes int layout, LayoutInflater inflater, List<String> data) {
        this.layout = layout;
        this.inflater = inflater;
        this.data.addAll(data);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new WeekdayNameViewHolder(inflater.inflate(layout, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((WeekdayNameViewHolder)holder).bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
