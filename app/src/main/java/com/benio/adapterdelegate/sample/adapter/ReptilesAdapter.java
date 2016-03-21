package com.benio.adapterdelegate.sample.adapter;

import android.support.v7.widget.RecyclerView;

import com.benio.adapterdelegate.AdapterDelegateManager;
import com.benio.adapterdelegate.DelegateRecyclerAdapter;
import com.benio.adapterdelegate.interf.RecyclerDelegate;
import com.benio.adapterdelegate.sample.delegate.recyclerview.GeckoAdapterDelegate;
import com.benio.adapterdelegate.sample.delegate.recyclerview.ReptilesFallbackDelegate;
import com.benio.adapterdelegate.sample.delegate.recyclerview.SnakeAdapterDelegate;
import com.benio.adapterdelegate.sample.model.DisplayableItem;

import java.util.List;

/**
 * Created by benio on 2016/3/2.
 */
public class ReptilesAdapter extends DelegateRecyclerAdapter<RecyclerView.ViewHolder, RecyclerDelegate<RecyclerView.ViewHolder>> {
    private List<DisplayableItem> mData;

    public ReptilesAdapter(List<DisplayableItem> data) {
        this.mData = data;
        AdapterDelegateManager<RecyclerView.ViewHolder, RecyclerDelegate<RecyclerView.ViewHolder>> manager = getDelegateManager();
        // Delegates
        manager.addDelegate(new GeckoAdapterDelegate(this, 3))
                .addDelegate(new SnakeAdapterDelegate(this, 1))
                .setFallbackDelegate(new ReptilesFallbackDelegate(this, 2));
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
