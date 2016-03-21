package com.benio.adapterdelegate.sample.adapter;

import com.benio.adapterdelegate.AbsDelegateBaseAdapter;
import com.benio.adapterdelegate.interf.Delegate;
import com.benio.adapterdelegate.sample.delegate.list.AdvertisementListAdapterDelegate;
import com.benio.adapterdelegate.sample.delegate.list.CatListAdapterDelegate;
import com.benio.adapterdelegate.sample.delegate.list.DogListAdapterDelegate;
import com.benio.adapterdelegate.sample.delegate.list.GeckoListAdapterDelegate;
import com.benio.adapterdelegate.sample.delegate.list.SnakeListAdapterDelegate;
import com.benio.adapterdelegate.sample.model.DisplayableItem;
import com.benio.adapterdelegate.sample.model.ListViewHolder;

import java.util.List;

/**
 * Created by benio on 2016/3/2.
 */
public class MainListAdapter extends AbsDelegateBaseAdapter<ListViewHolder, Delegate<ListViewHolder>> {
    private List<DisplayableItem> mData;

    public MainListAdapter(List<DisplayableItem> data) {
        this.mData = data;

        // 这里要注意viewType必须连续，而且从0开始
        // 这个限制是因为ListView的RecycleBin造成的
        getDelegateManager().addDelegate(new AdvertisementListAdapterDelegate(this, 1))
                .addDelegate(new CatListAdapterDelegate(this, 2))
                .addDelegate(new DogListAdapterDelegate(this, 3))
                .addDelegate(new GeckoListAdapterDelegate(this, 4))
                .addDelegate(new SnakeListAdapterDelegate(this, 0));
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
