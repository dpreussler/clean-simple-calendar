package de.jodamob.android.calendar;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.junit.Before;
import org.junit.Test;

import de.jodamob.android.testutils.SuperReflect;

@SuppressLint("NewApi")
public class RTLUtilTest {

    private static final int ROWS = 3;
    private static final int COUNT = 7;
    Configuration configuration;
    RTLUtil tested;

    @Before
    public void setup() {

        SuperReflect.on(Build.VERSION.class).set("SDK_INT", 21);

        RecyclerView recyclerView = mock(RecyclerView.class);
        RecyclerView.Adapter adapter = mock(RecyclerView.Adapter.class);
        when(recyclerView.getAdapter()).thenReturn(adapter);
        when(adapter.getItemCount()).thenReturn(COUNT);
        tested = new RTLUtil(recyclerView);
        GridLayoutManager mock = mock(GridLayoutManager.class);
        when(recyclerView.getLayoutManager()).thenReturn(mock);
        when(mock.getSpanCount()).thenReturn(ROWS);
        Resources resources = mock(Resources.class);
        when(recyclerView.getResources()).thenReturn(resources);
        configuration = mock(Configuration.class);
        when(resources.getConfiguration()).thenReturn(configuration);
    }

    @Test
    public void should_revert_on_rtl() {
        when(configuration.getLayoutDirection()).thenReturn(View.LAYOUT_DIRECTION_RTL);

        // first row
        assertEquals(2, tested.verifyGridPosition(0));
        assertEquals(1, tested.verifyGridPosition(1));
        assertEquals(0, tested.verifyGridPosition(2));

        // second row
        assertEquals(5, tested.verifyGridPosition(3));
        assertEquals(4, tested.verifyGridPosition(4));
        assertEquals(3, tested.verifyGridPosition(5));

        // last row
        assertEquals(6, tested.verifyGridPosition(6));
    }
    @Test
    public void should_not_revert_on_old() {
        when(configuration.getLayoutDirection()).thenReturn(View.LAYOUT_DIRECTION_LTR);
        SuperReflect.on(Build.VERSION.class).set("SDK_INT", 16);

        // first row
        assertEquals(2, tested.verifyGridPosition(2));
        assertEquals(1, tested.verifyGridPosition(1));
        assertEquals(0, tested.verifyGridPosition(0));

        // second row
        assertEquals(5, tested.verifyGridPosition(5));
        assertEquals(4, tested.verifyGridPosition(4));
        assertEquals(3, tested.verifyGridPosition(3));

        // last row
        assertEquals(6, tested.verifyGridPosition(6));
    }

    @Test
    public void should_not_revert_on_ltr() {
        when(configuration.getLayoutDirection()).thenReturn(View.LAYOUT_DIRECTION_LTR);

        // first row
        assertEquals(2, tested.verifyGridPosition(2));
        assertEquals(1, tested.verifyGridPosition(1));
        assertEquals(0, tested.verifyGridPosition(0));

        // second row
        assertEquals(5, tested.verifyGridPosition(5));
        assertEquals(4, tested.verifyGridPosition(4));
        assertEquals(3, tested.verifyGridPosition(3));

        // last row
        assertEquals(6, tested.verifyGridPosition(6));
    }
}