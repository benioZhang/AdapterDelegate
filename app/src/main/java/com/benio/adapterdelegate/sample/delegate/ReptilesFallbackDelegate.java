package com.benio.adapterdelegate.sample.delegate;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.benio.adapterdelegate.AbsRecyclerAdapterDelegate;
import com.benio.adapterdelegate.DataProvider;
import com.benio.adapterdelegate.sample.R;

/**
 * @author Hannes Dorfmann
 */
public class ReptilesFallbackDelegate extends AbsRecyclerAdapterDelegate<RecyclerViewHolder> {

    public ReptilesFallbackDelegate(DataProvider dataProvider, int viewType) {
        super(dataProvider, viewType);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_unknown_reptile, parent, false);
        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(int position, RecyclerViewHolder holder) {

    }

    /**
     * As a fallback delegate, return value will be ignored
     */
    @Override
    public boolean isForViewType(int position) {
        return false;
    }
}
