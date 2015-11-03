package de.jodamob.android.calendar;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.isNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.support.v7.widget.RecyclerView;

import org.junit.Test;

public class ItemClickSupportTest {

    ItemClickSupport mock = mock(ItemClickSupport.class);
    RecyclerView recyclerView = mock(RecyclerView.class);

    @Test
    public void should_attach() {
        when(recyclerView.getTag(anyInt())).thenReturn(mock);
        ItemClickAttacher.OnItemClickListener listener = mock(ItemClickAttacher.OnItemClickListener.class);
        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(listener);
        verify(this.mock).setOnItemClickListener(listener);
    }

    @Test
    public void should_detach() {
        when(recyclerView.getTag(anyInt())).thenReturn(mock);
        ItemClickSupport.removeFrom(recyclerView);
        verify(mock).detach(recyclerView);
    }

    @Test
    public void should_remove_listeners() {
        ItemClickSupport.addTo(recyclerView).detach(recyclerView);
        verify(recyclerView).setTag(anyInt(), isNull(ItemClickSupport.class));
    }
}