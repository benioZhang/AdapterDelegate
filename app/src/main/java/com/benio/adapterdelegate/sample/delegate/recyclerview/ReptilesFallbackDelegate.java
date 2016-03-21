package com.benio.adapterdelegate.sample.delegate.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.benio.adapterdelegate.RecyclerAdapterDelegate;
import com.benio.adapterdelegate.interf.DataProvider;
import com.benio.adapterdelegate.sample.R;

/**
 * @author Hannes Dorfmann
 */
public class ReptilesFallbackDelegate extends RecyclerAdapterDelegate<RecyclerView.ViewHolder> {
    private static final String TAG = "FallbackDelegate";

    public ReptilesFallbackDelegate(DataProvider dataProvider, int viewType) {
        super(dataProvider, viewType);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_unknown_reptile, parent, false);
        return new ReptileFallbackViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(int position, RecyclerView.ViewHolder holder) {

    }

    /**
     * As a fallback delegate, return value will be ignored
     */
    @Override
    public boolean isForViewType(int position) {
        return false;
    }

    class ReptileFallbackViewHolder extends RecyclerView.ViewHolder {
        public ReptileFallbackViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        Log.d(TAG, "onViewAttachedToWindow: ");
    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        Log.d(TAG, "onViewDetachedFromWindow: ");
    }
}
