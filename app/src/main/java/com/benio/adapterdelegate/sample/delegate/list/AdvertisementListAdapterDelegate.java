package com.benio.adapterdelegate.sample.delegate.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.benio.adapterdelegate.AdapterDelegate;
import com.benio.adapterdelegate.interf.DataProvider;
import com.benio.adapterdelegate.sample.R;
import com.benio.adapterdelegate.sample.model.Advertisement;
import com.benio.adapterdelegate.sample.model.ListViewHolder;

/**
 * Created by benio on 2016/3/2.
 */
public class AdvertisementListAdapterDelegate extends AdapterDelegate<ListViewHolder> {
    public AdvertisementListAdapterDelegate() {
    }

    public AdvertisementListAdapterDelegate(DataProvider dataProvider) {
        super(dataProvider);
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_advertisement, parent, false);
        return new ListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(int position, ListViewHolder holder) {

    }

    @Override
    public boolean isForPosition(int position) {
        return getItem(position) instanceof Advertisement;
    }
}
