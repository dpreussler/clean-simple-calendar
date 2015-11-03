package de.jodamob.android.calendar;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import calendar.android.jodamob.de.cleansimplecalendar.R;

/**
 * http://www.littlerobots.nl/blog/Handle-Android-RecyclerView-Clicks/
 * free to use, unlicense.org
 */
@SuppressWarnings("unused")
class ItemClickSupport {

    private final RecyclerView recyclerView;
    private ItemClickAttacher.OnItemClickListener itemClickListener;
    private ItemClickAttacher.OnItemLongClickListener itemLongClickListener;
    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (itemClickListener != null) {
                RecyclerView.ViewHolder holder = recyclerView.getChildViewHolder(v);
                itemClickListener.onItemClicked(recyclerView, holder.getAdapterPosition(), v);
            }
        }
    };
    private View.OnLongClickListener longClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            if (itemLongClickListener != null) {
                RecyclerView.ViewHolder holder = recyclerView.getChildViewHolder(v);
                return itemLongClickListener.onItemLongClicked(recyclerView, holder.getAdapterPosition(), v);
            }
            return false;
        }
    };
    private RecyclerView.OnChildAttachStateChangeListener stateChangeListener
            = new RecyclerView.OnChildAttachStateChangeListener() {
        @Override
        public void onChildViewAttachedToWindow(View view) {
            if (itemClickListener != null) {
                view.setOnClickListener(clickListener);
            }
            if (itemLongClickListener != null) {
                view.setOnLongClickListener(longClickListener);
            }
        }

        @Override
        public void onChildViewDetachedFromWindow(View view) {
        }
    };

    private ItemClickSupport(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        this.recyclerView.setTag(R.id.item_click_support, this);
        this.recyclerView.addOnChildAttachStateChangeListener(stateChangeListener);
    }

    static ItemClickSupport addTo(RecyclerView view) {
        ItemClickSupport support = (ItemClickSupport) view.getTag(R.id.item_click_support);
        if (support == null) {
            support = new ItemClickSupport(view);
        }
        return support;
    }

    static ItemClickSupport removeFrom(RecyclerView view) {
        ItemClickSupport support = (ItemClickSupport) view.getTag(R.id.item_click_support);
        if (support != null) {
            support.detach(view);
        }
        return support;
    }

    ItemClickSupport setOnItemClickListener(ItemClickAttacher.OnItemClickListener listener) {
        itemClickListener = listener;
        return this;
    }

    ItemClickSupport setOnItemLongClickListener(ItemClickAttacher.OnItemLongClickListener listener) {
        itemLongClickListener = listener;
        return this;
    }

    void detach(RecyclerView view) {
        view.removeOnChildAttachStateChangeListener(stateChangeListener);
        view.setTag(R.id.item_click_support, null);
    }
}
