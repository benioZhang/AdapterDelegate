package com.benio.adapterdelegate.sample.adapter;

import com.benio.adapterdelegate.DelegateRecyclerAdapter;
import com.benio.adapterdelegate.RecyclerAdapterDelegate;
import com.benio.adapterdelegate.RecyclerAdapterDelegateManager;
import com.benio.adapterdelegate.sample.delegate.GeckoAdapterDelegate;
import com.benio.adapterdelegate.sample.delegate.RecyclerViewHolder;
import com.benio.adapterdelegate.sample.delegate.ReptilesFallbackDelegate;
import com.benio.adapterdelegate.sample.delegate.SnakeAdapterDelegate;
import com.benio.adapterdelegate.sample.model.DisplayableItem;

import java.util.List;

/**
 * Created by benio on 2016/3/2.
 */
public class ReptilesAdapter extends DelegateRecyclerAdapter<RecyclerAdapterDelegateManager<RecyclerViewHolder, RecyclerAdapterDelegate<RecyclerViewHolder>>,
        RecyclerViewHolder, RecyclerAdapterDelegate<RecyclerViewHolder>> {
    private List<DisplayableItem> mData;

    public ReptilesAdapter(List<DisplayableItem> data) {
        super(new RecyclerAdapterDelegateManager<RecyclerViewHolder, RecyclerAdapterDelegate<RecyclerViewHolder>>());
        this.mData = data;
        // Delegates
        this.mDelegateManager.addDelegate(new GeckoAdapterDelegate(this, 0));
        this.mDelegateManager.addDelegate(new SnakeAdapterDelegate(this, 1));
        this.mDelegateManager.setFallbackDelegate(new ReptilesFallbackDelegate(this, 2));
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
