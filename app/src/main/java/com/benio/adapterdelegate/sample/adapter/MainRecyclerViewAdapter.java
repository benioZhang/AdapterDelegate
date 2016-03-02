package com.benio.adapterdelegate.sample.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.benio.adapterdelegate.AbsRecyclerAdapterDelegate;
import com.benio.adapterdelegate.AdapterDelegateManager;
import com.benio.adapterdelegate.DataProvider;
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
public class MainRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> implements DataProvider {
    private AdapterDelegateManager<RecyclerViewHolder, AbsRecyclerAdapterDelegate<RecyclerViewHolder>> mDelegateManager;
    private List<DisplayableItem> mData;

    public MainRecyclerViewAdapter(List<DisplayableItem> data) {
        this.mData = data;

        // Delegates
        mDelegateManager = new AdapterDelegateManager<>();
        mDelegateManager.addDelegate(new AdvertisementAdapterDelegate(this, 5));
        mDelegateManager.addDelegate(new CatAdapterDelegate(this, 1));
        mDelegateManager.addDelegate(new DogAdapterDelegate(this, 2));
        mDelegateManager.addDelegate(new GeckoAdapterDelegate(this, 3));
        mDelegateManager.addDelegate(new SnakeAdapterDelegate(this, 4));
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return mDelegateManager.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        mDelegateManager.onBindViewHolder(holder, position, holder.getItemViewType());
    }

    @Override
    public int getItemViewType(int position) {
        return mDelegateManager.getItemViewType(position);
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
