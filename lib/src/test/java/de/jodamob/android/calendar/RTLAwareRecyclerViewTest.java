package de.jodamob.android.calendar;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import android.support.v7.widget.RecyclerView;

import org.junit.Before;
import org.junit.Test;

import de.jodamob.android.testutils.SuperReflect;

public class RTLAwareRecyclerViewTest {

    RTLUtil rtlUtil;
    RecyclerView.Adapter adapter;
    RTLAwareRecyclerView.InvertingRowAdapter tested;
    RTLAwareRecyclerView recyclerView;

    @Before
    public void setup() {
        rtlUtil = mock(RTLUtil.class);
        adapter = mock(RecyclerView.Adapter.class);
        recyclerView = mock(RTLAwareRecyclerView.class);
        SuperReflect.on(recyclerView).set("rtlUtil", rtlUtil);
        tested = recyclerView.new InvertingRowAdapter(adapter);
    }

    @Test
    public void should_verify_bind() {
        tested.onBindViewHolder(mock(RecyclerView.ViewHolder.class), 1);
        verify(rtlUtil).verifyGridPosition(1);
    }

    @Test
    public void should_verify_id() {
        tested.getItemId(2);
        verify(rtlUtil).verifyGridPosition(2);
    }

    @Test
    public void should_verify_type() {
        tested.getItemViewType(3);
        verify(rtlUtil).verifyGridPosition(3);
    }
}