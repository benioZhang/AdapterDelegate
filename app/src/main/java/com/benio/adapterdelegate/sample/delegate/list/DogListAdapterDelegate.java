package com.benio.adapterdelegate.sample.delegate.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.benio.adapterdelegate.AbsAdapterDelegate;
import com.benio.adapterdelegate.interf.DataProvider;
import com.benio.adapterdelegate.sample.R;
import com.benio.adapterdelegate.sample.model.Dog;
import com.benio.adapterdelegate.sample.model.ListViewHolder;

/**
 * Created by benio on 2016/3/2.
 */
public class DogListAdapterDelegate extends AbsAdapterDelegate<ListViewHolder> {

    public DogListAdapterDelegate(DataProvider dataProvider, int viewType) {
        super(dataProvider, viewType);
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dog, parent, false);
        return new ListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(int position, ListViewHolder holder) {
        TextView textView = holder.getView(R.id.name);
        Dog dog = (Dog) getItem(position);
        textView.setText(dog.getName());
    }

    @Override
    public boolean isForViewType(int position) {
        return getItem(position) instanceof Dog;
    }
}
