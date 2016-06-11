package com.benio.adapterdelegate.sample.adapter;

import android.support.v7.widget.RecyclerView;

import com.benio.adapterdelegate.DelegateRecyclerAdapter;
import com.benio.adapterdelegate.interf.DelegateManager;
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
public class MainAdapter extends DelegateRecyclerAdapter<RecyclerView.ViewHolder> {
    private List<DisplayableItem> mData;

    GeckoAdapterDelegate mGeckoAdapterDelegate;

    public MainAdapter(List<DisplayableItem> data) {
        this.mData = data;

        DelegateManager<RecyclerView.ViewHolder> manager = getDelegateManager();
        // 手动调用onViewAttachedToWindow，onViewDetachedFromWindow方法
        mGeckoAdapterDelegate = new GeckoAdapterDelegate(this);
        // Delegates
        manager.addDelegate(mGeckoAdapterDelegate)
                .addDelegate(new AdvertisementAdapterDelegate())
                .addDelegate(new CatAdapterDelegate())
                .addDelegate(new DogAdapterDelegate())
                .addDelegate(new SnakeAdapterDelegate());
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
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        if (mGeckoAdapterDelegate.isForPosition(holder.getAdapterPosition())) {
            mGeckoAdapterDelegate.onViewAttachedToWindow(holder);
        }
    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        if (mGeckoAdapterDelegate.isForPosition(holder.getAdapterPosition())) {
            mGeckoAdapterDelegate.onViewDetachedFromWindow(holder);
        }
    }
}
