package com.benio.adapterdelegate.sample.delegate.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.benio.adapterdelegate.RecyclerAdapterDelegate;
import com.benio.adapterdelegate.interf.DataProvider;
import com.benio.adapterdelegate.sample.R;
import com.benio.adapterdelegate.sample.model.Cat;
import com.benio.adapterdelegate.sample.model.RecyclerViewHolder;

/**
 * Created by benio on 2016/3/2.
 */
public class CatAdapterDelegate extends RecyclerAdapterDelegate<RecyclerViewHolder> {

    public CatAdapterDelegate(DataProvider dataProvider, int viewType) {
        super(dataProvider, viewType);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cat, parent, false);
        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(int position, RecyclerViewHolder holder) {
        TextView textView = holder.getView(R.id.name);
        Cat cat = (Cat) getItem(position);
        textView.setText(cat.getName());
    }

    @Override
    public boolean isForViewType(int position) {
        return getItem(position) instanceof Cat;
    }
}
