package com.benio.adapterdelegate.sample.delegate.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.benio.adapterdelegate.RecyclerAdapterDelegate;
import com.benio.adapterdelegate.interf.DataProvider;
import com.benio.adapterdelegate.sample.R;
import com.benio.adapterdelegate.sample.model.Gecko;

/**
 * Created by benio on 2016/3/2.
 */
public class GeckoAdapterDelegate extends RecyclerAdapterDelegate<RecyclerView.ViewHolder> {
    private static final String TAG = "GeckoAdapterDelegate";

    public GeckoAdapterDelegate(DataProvider dataProvider, int viewType) {
        super(dataProvider, viewType);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gecko, parent, false);
        return new GeckoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(int position, RecyclerView.ViewHolder holder) {
        Gecko gecko = (Gecko) getItem(position);
        TextView nameView = ((GeckoViewHolder) holder).name;
        TextView raceView = ((GeckoViewHolder) holder).race;
        nameView.setText(gecko.getName());
        raceView.setText(gecko.getRace());
    }

    @Override
    public boolean isForViewType(int position) {
        return getItem(position) instanceof Gecko;
    }

    static class GeckoViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView race;

        public GeckoViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            race = (TextView) itemView.findViewById(R.id.race);
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

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        super.onViewRecycled(holder);
        Log.d(TAG, "onViewRecycled: ");
    }

    @Override
    public boolean onFailedToRecycleView(RecyclerView.ViewHolder holder) {
        Log.d(TAG, "onFailedToRecycleView: ");
        return super.onFailedToRecycleView(holder);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        Log.d(TAG, "onAttachedToRecyclerView: ");
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        Log.d(TAG, "onDetachedFromRecyclerView: ");
    }
}
