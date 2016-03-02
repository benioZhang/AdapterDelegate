package com.benio.adapterdelegate.sample.delegate;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.benio.adapterdelegate.AbsRecyclerAdapterDelegate;
import com.benio.adapterdelegate.DataProvider;
import com.benio.adapterdelegate.sample.R;
import com.benio.adapterdelegate.sample.model.Advertisement;

/**
 * Created by benio on 2016/3/2.
 */
public class AdvertisementAdapterDelegate extends AbsRecyclerAdapterDelegate<RecyclerViewHolder> {

    public AdvertisementAdapterDelegate(DataProvider dataProvider, int viewType) {
        super(dataProvider, viewType);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_advertisement, parent, false);
        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(int position, RecyclerViewHolder holder) {

    }

    @Override
    public boolean isForViewType(int position) {
        return getItem(position) instanceof Advertisement;
    }
}
