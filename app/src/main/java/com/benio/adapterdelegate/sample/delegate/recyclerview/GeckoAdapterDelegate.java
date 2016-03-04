package com.benio.adapterdelegate.sample.delegate.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.benio.adapterdelegate.AbsRecyclerAdapterDelegate;
import com.benio.adapterdelegate.interf.DataProvider;
import com.benio.adapterdelegate.sample.R;
import com.benio.adapterdelegate.sample.model.Gecko;
import com.benio.adapterdelegate.sample.model.RecyclerViewHolder;

/**
 * Created by benio on 2016/3/2.
 */
public class GeckoAdapterDelegate extends AbsRecyclerAdapterDelegate<RecyclerViewHolder> {

    public GeckoAdapterDelegate(DataProvider dataProvider, int viewType) {
        super(dataProvider, viewType);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gecko, parent, false);
        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(int position, RecyclerViewHolder holder) {
        Gecko gecko = (Gecko) getItem(position);
        TextView nameView = holder.getView(R.id.name);
        TextView raceView = holder.getView(R.id.race);
        nameView.setText(gecko.getName());
        raceView.setText(gecko.getRace());
    }

    @Override
    public boolean isForViewType(int position) {
        return getItem(position) instanceof Gecko;
    }
}
