package de.jodamob.android.calendar;

import android.annotation.TargetApi;
import android.content.res.Resources;
import android.os.Build;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class RTLUtil {

    private final RecyclerView recyclerView;

    public RTLUtil(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public boolean isRTL(Resources resources) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return resources.getConfiguration().getLayoutDirection() == View.LAYOUT_DIRECTION_RTL;
        }
        return false;
    }

    public int verifyGridPosition(int position) {
        int modulo = position % getNumColumns(recyclerView);
        int startRow = position - modulo;
        int endRow = position + getNumColumns(recyclerView) - modulo -1;
        return isRTL(recyclerView.getResources()) ? Math.min(recyclerView.getAdapter().getItemCount() - 1, endRow - (position - startRow)) : position;
    }

    private int getNumColumns(RecyclerView view) {
        if (view.getLayoutManager() instanceof GridLayoutManager) {
            return ((GridLayoutManager)view.getLayoutManager()).getSpanCount();
        }
        return 1;
    }
}
