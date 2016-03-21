package com.benio.adapterdelegate.sample.adapter;

import android.support.v7.widget.RecyclerView;

import com.benio.adapterdelegate.AbsDelegateRecyclerAdapter;
import com.benio.adapterdelegate.interf.Delegate;
import com.benio.adapterdelegate.sample.delegate.recyclerview.AdvertisementAdapterDelegate;
import com.benio.adapterdelegate.sample.delegate.recyclerview.CatAdapterDelegate;
import com.benio.adapterdelegate.sample.delegate.recyclerview.DogAdapterDelegate;
import com.benio.adapterdelegate.sample.delegate.recyclerview.GeckoAdapterDelegate;
import com.benio.adapterdelegate.sample.delegate.recyclerview.SnakeAdapterDelegate;
import com.benio.adapterdelegate.sample.model.DisplayableItem;

import java.util.List;

/**
 * Created by benio on 2016/3/2.
 */
public class MainAdapter extends AbsDelegateRecyclerAdapter<RecyclerView.ViewHolder, Delegate<RecyclerView.ViewHolder>> {
    private List<DisplayableItem> mData;

    GeckoAdapterDelegate mGeckoAdapterDelegate;

    public MainAdapter(List<DisplayableItem> data) {
        this.mData = data;

        mGeckoAdapterDelegate = new GeckoAdapterDelegate(this, 3);
        // Delegates
        getDelegateManager().addDelegate(new AdvertisementAdapterDelegate(this, 5))
                .addDelegate(new CatAdapterDelegate(this, 1))
                .addDelegate(new DogAdapterDelegate(this, 2))
                .addDelegate(mGeckoAdapterDelegate)
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

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        super.onViewRecycled(holder);
        // hook adapter lifecycle
        mGeckoAdapterDelegate.onViewRecycled(holder);
    }

    @Override
    public boolean onFailedToRecycleView(RecyclerView.ViewHolder holder) {
        mGeckoAdapterDelegate.onFailedToRecycleView(holder);
        return super.onFailedToRecycleView(holder);
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        mGeckoAdapterDelegate.onViewAttachedToWindow(holder);
        super.onViewAttachedToWindow(holder);
    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        mGeckoAdapterDelegate.onViewDetachedFromWindow(holder);
        super.onViewDetachedFromWindow(holder);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mGeckoAdapterDelegate.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        mGeckoAdapterDelegate.onDetachedFromRecyclerView(recyclerView);
    }
}
