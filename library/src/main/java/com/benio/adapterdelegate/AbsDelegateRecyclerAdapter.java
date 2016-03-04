package com.benio.adapterdelegate;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.benio.adapterdelegate.interf.DataProvider;
import com.benio.adapterdelegate.interf.AdapterDelegate;
import com.benio.adapterdelegate.interf.DelegateManager;

/**
 * Created by benio on 2016/1/30.
 */
public abstract class AbsDelegateRecyclerAdapter<VH extends RecyclerView.ViewHolder, AD extends AdapterDelegate<VH>>
        extends RecyclerView.Adapter<VH> implements DataProvider {

    private final DelegateManager<VH, AD> mDelegateManager;

    protected AbsDelegateRecyclerAdapter(DelegateManager<VH, AD> manager) {
        this.mDelegateManager = manager;
        if (null == mDelegateManager) {
            throw new NullPointerException("AdapterDelegate manager is null");
        }
    }

    @SuppressWarnings("unchecked")
    protected <T extends DelegateManager<VH, AD>> T getDelegateManager() {
        return (T) mDelegateManager;
    }

    @Override
    public VH onCreateViewHolder(final ViewGroup parent, int viewType) {
        return mDelegateManager.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        mDelegateManager.onBindViewHolder(holder, position, holder.getItemViewType());
    }

    @Override
    public int getItemViewType(int position) {
        return mDelegateManager.getItemViewType(position);
    }
}
