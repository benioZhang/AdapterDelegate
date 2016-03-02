package com.benio.adapterdelegate.sample.delegate;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.benio.adapterdelegate.AbsRecyclerAdapterDelegate;
import com.benio.adapterdelegate.DataProvider;
import com.benio.adapterdelegate.sample.R;
import com.benio.adapterdelegate.sample.model.Dog;

/**
 * Created by benio on 2016/3/2.
 */
public class DogAdapterDelegate extends AbsRecyclerAdapterDelegate<RecyclerViewHolder> {

    public DogAdapterDelegate(DataProvider dataProvider, int viewType) {
        super(dataProvider, viewType);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dog, parent, false);
        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(int position, RecyclerViewHolder holder) {
        TextView textView = holder.getView(R.id.name);
        Dog dog = (Dog) getItem(position);
        textView.setText(dog.getName());
    }

    @Override
    public boolean isForViewType(int position) {
        return getItem(position) instanceof Dog;
    }
}
