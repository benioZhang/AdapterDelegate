package com.benio.adapterdelegate.sample.delegate;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.benio.adapterdelegate.AbsRecyclerAdapterDelegate;
import com.benio.adapterdelegate.DataProvider;
import com.benio.adapterdelegate.sample.R;
import com.benio.adapterdelegate.sample.model.Snake;

/**
 * Created by benio on 2016/3/2.
 */
public class SnakeAdapterDelegate extends AbsRecyclerAdapterDelegate<RecyclerViewHolder> {

    public SnakeAdapterDelegate(DataProvider dataProvider, int viewType) {
        super(dataProvider, viewType);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_snake, parent, false);
        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(int position, RecyclerViewHolder holder) {
        Snake snake = (Snake) getItem(position);
        TextView nameView = holder.getView(R.id.name);
        TextView raceView = holder.getView(R.id.race);
        nameView.setText(snake.getName());
        raceView.setText(snake.getRace());
    }

    @Override
    public boolean isForViewType(int position) {
        return getItem(position) instanceof Snake;
    }
}
