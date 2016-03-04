package com.benio.adapterdelegate.sample.adapter;

import com.benio.adapterdelegate.AbsDelegateRecyclerAdapter;
import com.benio.adapterdelegate.interf.RecyclerAdapterDelegate;
import com.benio.adapterdelegate.RecyclerAdapterDelegateManager;
import com.benio.adapterdelegate.sample.delegate.recyclerview.GeckoAdapterDelegate;
import com.benio.adapterdelegate.sample.model.RecyclerViewHolder;
import com.benio.adapterdelegate.sample.delegate.recyclerview.ReptilesFallbackDelegate;
import com.benio.adapterdelegate.sample.delegate.recyclerview.SnakeAdapterDelegate;
import com.benio.adapterdelegate.sample.model.DisplayableItem;

import java.util.List;

/**
 * Created by benio on 2016/3/2.
 */
public class ReptilesAdapter extends AbsDelegateRecyclerAdapter<RecyclerViewHolder, RecyclerAdapterDelegate<RecyclerViewHolder>> {
    private List<DisplayableItem> mData;

    public ReptilesAdapter(List<DisplayableItem> data) {
        super(new RecyclerAdapterDelegateManager<RecyclerViewHolder, RecyclerAdapterDelegate<RecyclerViewHolder>>());
        this.mData = data;
        RecyclerAdapterDelegateManager<RecyclerViewHolder, RecyclerAdapterDelegate<RecyclerViewHolder>> manager = getDelegateManager();
        // Delegates
        manager.addDelegate(new GeckoAdapterDelegate(this, 0));
        manager.addDelegate(new SnakeAdapterDelegate(this, 1));
        manager.setFallbackDelegate(new ReptilesFallbackDelegate(this, 2));
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
