package com.benio.adapterdelegate.sample.delegate.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.benio.adapterdelegate.AdapterDelegate;
import com.benio.adapterdelegate.interf.DataProvider;
import com.benio.adapterdelegate.sample.R;
import com.benio.adapterdelegate.sample.model.ListViewHolder;

/**
 * @author Hannes Dorfmann
 */
public class ReptilesListFallbackDelegate extends AdapterDelegate<ListViewHolder> {

    public ReptilesListFallbackDelegate(DataProvider dataProvider, int viewType) {
        super(dataProvider, viewType);
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_unknown_reptile, parent, false);
        return new ListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(int position, ListViewHolder holder) {

    }

    /**
     * As a fallback delegate, return value will be ignored
     */
    @Override
    public boolean isForViewType(int position) {
        return false;
    }
}
