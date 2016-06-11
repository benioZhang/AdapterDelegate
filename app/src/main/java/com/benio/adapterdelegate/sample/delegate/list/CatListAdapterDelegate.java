package com.benio.adapterdelegate.sample.delegate.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.benio.adapterdelegate.AdapterDelegate;
import com.benio.adapterdelegate.interf.DataProvider;
import com.benio.adapterdelegate.sample.R;
import com.benio.adapterdelegate.sample.model.Cat;
import com.benio.adapterdelegate.sample.model.ListViewHolder;

/**
 * Created by benio on 2016/3/2.
 */
public class CatListAdapterDelegate extends AdapterDelegate<ListViewHolder> {
    public CatListAdapterDelegate() {
    }

    public CatListAdapterDelegate(DataProvider dataProvider) {
        super(dataProvider);
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cat, parent, false);
        return new ListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(int position, ListViewHolder holder) {
        TextView textView = holder.getView(R.id.name);
        Cat cat = (Cat) getItem(position);
        textView.setText(cat.getName());
    }

    @Override
    public boolean isForPosition(int position) {
        return getItem(position) instanceof Cat;
    }
}
