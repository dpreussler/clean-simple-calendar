package de.jodamob.android.calendar;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;

import calendar.android.jodamob.de.cleansimplecalendar.R;

public class CalenderWidgetTest {

    CalendarBuilder factory;
    CalenderWidget tested;
    CalendarAdapter adapter;
    RecyclerView contentView;

    @Before
    public void setup() {
        tested= new CalenderWidget(mock(Context.class)) {

            @Override
            protected RecyclerView.LayoutManager getLayoutManager() {
                return mock(RecyclerView.LayoutManager.class);
            }

            @Override
            protected LayoutInflater getLayoutInflater() {
                LayoutInflater layoutInflater = mock(LayoutInflater.class);
                View view = mock(View.class);
                when(layoutInflater.inflate(eq(R.layout.calender_widget), any(ViewGroup.class), anyBoolean())).thenReturn(view);
                contentView = mock(RecyclerView.class);
                when(view.findViewById(anyInt())).thenReturn(contentView);
                return layoutInflater;
            }};
        factory = mock(CalendarBuilder.class);
        adapter = mock(CalendarAdapter.class);
        when(factory.createAdapterFor(any(LayoutInflater.class), any(VisibleMonths.class))).thenReturn(adapter);
        when(factory.createHeaderAdapterFor(any(LayoutInflater.class), any(List.class))).thenReturn(mock(CalendarHeaderAdapter.class));
        tested.set(mock(VisibleMonths.class), factory);
    }

    @Test
    public void should_forward_click_to_adapter() {

        ArgumentCaptor<ItemClickAttacher.OnItemClickListener> captor = ArgumentCaptor.forClass(ItemClickAttacher.OnItemClickListener.class);
        ItemClickAttacher attacher = mock(ItemClickAttacher.class);
        tested.setItemClickAttacher(attacher);
        tested.addListener();
        verify(attacher).attach(any(RecyclerView.class), captor.capture());
        captor.getValue().onItemClicked(contentView, 1, mock(View.class));
        verify(adapter).select(1);
    }

    @Test
    public void should_unregister() {
        ItemClickAttacher attacher = mock(ItemClickAttacher.class);
        tested.setItemClickAttacher(attacher);
        tested.onDetachedFromWindow();
        verify(attacher).detach(any(RecyclerView.class));
    }
}