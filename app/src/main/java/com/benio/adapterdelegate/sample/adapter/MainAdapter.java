package com.benio.adapterdelegate.sample.adapter;

import com.benio.adapterdelegate.DelegateRecyclerAdapter;
import com.benio.adapterdelegate.RecyclerAdapterDelegate;
import com.benio.adapterdelegate.RecyclerAdapterDelegateManager;
import com.benio.adapterdelegate.sample.delegate.AdvertisementAdapterDelegate;
import com.benio.adapterdelegate.sample.delegate.CatAdapterDelegate;
import com.benio.adapterdelegate.sample.delegate.DogAdapterDelegate;
import com.benio.adapterdelegate.sample.delegate.GeckoAdapterDelegate;
import com.benio.adapterdelegate.sample.delegate.RecyclerViewHolder;
import com.benio.adapterdelegate.sample.delegate.SnakeAdapterDelegate;
import com.benio.adapterdelegate.sample.model.DisplayableItem;

import java.util.List;

/**
 * Created by benio on 2016/3/2.
 */
public class MainAdapter extends DelegateRecyclerAdapter<RecyclerAdapterDelegateManager<RecyclerViewHolder, RecyclerAdapterDelegate<RecyclerViewHolder>>,
        RecyclerViewHolder, RecyclerAdapterDelegate<RecyclerViewHolder>> {
    private List<DisplayableItem> mData;

    public MainAdapter(List<DisplayableItem> data) {
        super(new RecyclerAdapterDelegateManager<RecyclerViewHolder, RecyclerAdapterDelegate<RecyclerViewHolder>>());
        this.mData = data;
        // Delegates
        mDelegateManager.addDelegate(new AdvertisementAdapterDelegate(this, 5));
        mDelegateManager.addDelegate(new CatAdapterDelegate(this, 1));
        mDelegateManager.addDelegate(new DogAdapterDelegate(this, 2));
        mDelegateManager.addDelegate(new GeckoAdapterDelegate(this, 3));
        mDelegateManager.addDelegate(new SnakeAdapterDelegate(this, 4));
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
