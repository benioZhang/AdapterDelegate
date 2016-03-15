package com.benio.adapterdelegate.sample.adapter;

import com.benio.adapterdelegate.AbsDelegateRecyclerAdapter;
import com.benio.adapterdelegate.interf.RecyclerDelegate;
import com.benio.adapterdelegate.sample.delegate.recyclerview.AdvertisementAdapterDelegate;
import com.benio.adapterdelegate.sample.delegate.recyclerview.CatAdapterDelegate;
import com.benio.adapterdelegate.sample.delegate.recyclerview.DogAdapterDelegate;
import com.benio.adapterdelegate.sample.delegate.recyclerview.GeckoAdapterDelegate;
import com.benio.adapterdelegate.sample.delegate.recyclerview.SnakeAdapterDelegate;
import com.benio.adapterdelegate.sample.model.DisplayableItem;
import com.benio.adapterdelegate.sample.model.RecyclerViewHolder;

import java.util.List;

/**
 * Created by benio on 2016/3/2.
 */
public class MainAdapter extends AbsDelegateRecyclerAdapter<RecyclerViewHolder, RecyclerDelegate<RecyclerViewHolder>> {
    private List<DisplayableItem> mData;

    public MainAdapter(List<DisplayableItem> data) {
        this.mData = data;

        // Delegates
        getDelegateManager().addDelegate(new AdvertisementAdapterDelegate(this, 5))
                .addDelegate(new CatAdapterDelegate(this, 1))
                .addDelegate(new DogAdapterDelegate(this, 2))
                .addDelegate(new GeckoAdapterDelegate(this, 3))
                .addDelegate(new SnakeAdapterDelegate(this, 4));
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
