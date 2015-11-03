package de.jodamob.android.calendar;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public class ItemClickAttacher {

    public void attach(RecyclerView view, OnItemClickListener itemClickListener) {
        ItemClickSupport.addTo(view).setOnItemClickListener(itemClickListener);
    }

    public void detach(RecyclerView view) {
        ItemClickSupport.removeFrom(view);
    }

    public interface OnItemClickListener {
        void onItemClicked(RecyclerView recyclerView, int position, View v);
    }

    public interface OnItemLongClickListener {
        boolean onItemLongClicked(RecyclerView recyclerView, int position, View v);
    }
}
