package com.benio.adapterdelegate.sample.adapter;

import com.benio.adapterdelegate.AbsDelegateBaseAdapter;
import com.benio.adapterdelegate.AdapterDelegateManager;
import com.benio.adapterdelegate.interf.Delegate;
import com.benio.adapterdelegate.sample.delegate.list.GeckoListAdapterDelegate;
import com.benio.adapterdelegate.sample.delegate.list.ReptilesListFallbackDelegate;
import com.benio.adapterdelegate.sample.delegate.list.SnakeListAdapterDelegate;
import com.benio.adapterdelegate.sample.model.DisplayableItem;
import com.benio.adapterdelegate.sample.model.ListViewHolder;

import java.util.List;

/**
 * Created by benio on 2016/3/2.
 */
public class ReptilesListAdapter extends AbsDelegateBaseAdapter<ListViewHolder, Delegate<ListViewHolder>> {
    private List<DisplayableItem> mData;

    public ReptilesListAdapter(List<DisplayableItem> data) {
        this.mData = data;
        // Delegates
        AdapterDelegateManager<ListViewHolder, Delegate<ListViewHolder>> manager = getDelegateManager();
        manager.addDelegate(new GeckoListAdapterDelegate(this, 0))
                .addDelegate(new SnakeListAdapterDelegate(this, 1))
                .setFallbackDelegate(new ReptilesListFallbackDelegate(this, 2));
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
