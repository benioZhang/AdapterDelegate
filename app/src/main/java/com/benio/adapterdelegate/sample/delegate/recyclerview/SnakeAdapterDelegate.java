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
import com.benio.adapterdelegate.sample.model.Snake;

/**
 * Created by benio on 2016/3/2.
 */
public class SnakeAdapterDelegate extends RecyclerAdapterDelegate<RecyclerView.ViewHolder> {
    private static final String TAG = "SnakeAdapterDelegate";
    public SnakeAdapterDelegate(DataProvider dataProvider, int viewType) {
        super(dataProvider, viewType);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_snake, parent, false);
        return new SnakeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(int position, RecyclerView.ViewHolder holder) {
        Snake snake = (Snake) getItem(position);
        TextView nameView = ((SnakeViewHolder) holder).name;
        TextView raceView = ((SnakeViewHolder) holder).race;
        nameView.setText(snake.getName());
        raceView.setText(snake.getRace());
    }

    @Override
    public boolean isForViewType(int position) {
        return getItem(position) instanceof Snake;
    }

    static class SnakeViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView race;

        public SnakeViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            race = (TextView) itemView.findViewById(R.id.race);
        }
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        Log.d(TAG, "onViewAttachedToWindow: ");
        super.onViewAttachedToWindow(holder);
    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        Log.d(TAG, "onViewDetachedFromWindow: ");
        super.onViewDetachedFromWindow(holder);
    }
}
