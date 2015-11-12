package de.jodamob.android.calendar;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.ViewGroup;

public class RTLAwareRecyclerView extends RecyclerView {

    private final RTLUtil rtlUtil;

    public RTLAwareRecyclerView(Context context) {
        super(context);
        rtlUtil = init();
    }

    public RTLAwareRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        rtlUtil = init();
    }

    public RTLAwareRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        rtlUtil = init();
    }

    private RTLUtil init() {
        return new RTLUtil(this);
    }

    @Override
    public void setAdapter(final Adapter adapter) {
        super.setAdapter(new InvertingRowAdapter(adapter));
    }

    class InvertingRowAdapter extends Adapter {
        private final Adapter adapter;

        public InvertingRowAdapter(Adapter adapter) {
            this.adapter = adapter;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return adapter.onCreateViewHolder(parent, viewType);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            adapter.onBindViewHolder(holder, rtlUtil.verifyGridPosition(position));
        }

        @Override
        public int getItemCount() {
            return adapter.getItemCount();
        }

        @Override
        public int getItemViewType(int position) {
            return super.getItemViewType(rtlUtil.verifyGridPosition(position));
        }

        @Override
        public long getItemId(int position) {
            return super.getItemId(rtlUtil.verifyGridPosition(position));
        }
    }
}
