package de.jodamob.android.calendar;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.VisibleForTesting;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import java.util.Date;
import java.util.Locale;

import calendar.android.jodamob.de.cleansimplecalendar.R;

public class CalenderWidget extends FrameLayout {

    private static final int DAYS_PER_WEEK = 7;
    private static final int ROW_COUNT = 6;
    private RecyclerView headerView;
    private RecyclerView contentView;
    private CalendarAdapter adapter;
    private ItemClickAttacher clickAttacher = new ItemClickAttacher();

    public CalenderWidget(Context context) {
        super(context);
        init();
    }

    public CalenderWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CalenderWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CalenderWidget(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public int getRowCount() {
        return ROW_COUNT;
    }

    public void set(CalendarBuilder factory) {
        set(CalendarDataFactory.getInstance(Locale.getDefault()).create(new Date(), ROW_COUNT), factory);
    }

    public void set(VisibleMonths months, CalendarBuilder factory) {
        setContent(months, factory);
        setHeader(months, factory);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        addListener();
    }

    @Override
    protected void onDetachedFromWindow() {
        removeListener();
        super.onDetachedFromWindow();
    }

    public void select(Date date) {
        getAdapter().select(new Day(date));
    }

    @SuppressWarnings("unused")
    protected CalendarAdapter getAdapter() {
        return adapter;
    }

    @VisibleForTesting
    void addListener() {
        clickAttacher.attach(contentView, new MyOnItemClickListener());
    }

    private void removeListener() {
        clickAttacher.detach(contentView);
    }

    protected RecyclerView.LayoutManager getLayoutManager() {
        return new NonScrollableGridLayoutManager(getContext(), DAYS_PER_WEEK);
    }

    protected LayoutInflater getLayoutInflater() {
        return LayoutInflater.from(getContext());
    }

    @VisibleForTesting
    void setItemClickAttacher(ItemClickAttacher itemClickAttacher) {
        this.clickAttacher = itemClickAttacher;
    }

    private void setHeader(VisibleMonths months, CalendarBuilder factory) {
        headerView.setLayoutManager(getLayoutManager());
        CalendarHeaderAdapter adapter = factory.createHeaderAdapterFor(getLayoutInflater(), months.getDayNames());
        headerView.setAdapter(adapter);
    }

    private void setContent(VisibleMonths months, CalendarBuilder factory) {
        if (adapter == null) {
            contentView.setLayoutManager(getLayoutManager());
            adapter = factory.createAdapterFor(getLayoutInflater(), months);
            contentView.setAdapter(adapter);
        } else {
            adapter.replace(months);
            adapter.notifyDataSetChanged();
        }
    }

    private void init() {
        View view = getLayoutInflater().inflate(R.layout.calender_widget, this, true);
        headerView = (RecyclerView) view.findViewById(R.id.header);
        contentView = (RecyclerView) view.findViewById(R.id.content);
    }

    private class MyOnItemClickListener implements ItemClickAttacher.OnItemClickListener {
        @Override
        public void onItemClicked(RecyclerView recyclerView, int position, View v) {
            onSelection(position);
        }
    }

    private void onSelection(int position) {
        getAdapter().select(position);
    }

    private static class NonScrollableGridLayoutManager extends GridLayoutManager {

        NonScrollableGridLayoutManager(Context context, int span) {
            super(context, span);
        }

        @Override
        public boolean canScrollVertically() {
            return false;
        }

        @Override
        public boolean canScrollHorizontally() {
            return false;
        }
    }
}
