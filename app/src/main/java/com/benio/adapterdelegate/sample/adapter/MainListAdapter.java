package com.benio.adapterdelegate.sample.adapter;

import com.benio.adapterdelegate.AdapterDelegateManager;
import com.benio.adapterdelegate.DelegateBaseAdapter;
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
public class MainListAdapter extends DelegateBaseAdapter<ListViewHolder> {
    private List<DisplayableItem> mData;

    public MainListAdapter(List<DisplayableItem> data) {
        this.mData = data;

        AdapterDelegateManager<ListViewHolder> manager = getDelegateManager();
//        manager.setDataProvider(this);
        manager.addDelegate(new AdvertisementListAdapterDelegate())
                .addDelegate(new CatListAdapterDelegate())
                .addDelegate(new DogListAdapterDelegate())
                .addDelegate(new GeckoListAdapterDelegate())
                .addDelegate(new SnakeListAdapterDelegate());
    }

    @Override
    public int getItemCount() {
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
