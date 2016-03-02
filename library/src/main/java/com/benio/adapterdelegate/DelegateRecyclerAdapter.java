package com.benio.adapterdelegate;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by benio on 2016/1/30.
 */
public abstract class DelegateRecyclerAdapter<M extends RecyclerAdapterDelegateManager<VH, AD>,
        VH extends RecyclerView.ViewHolder, AD extends RecyclerAdapterDelegate<VH>>
        extends RecyclerView.Adapter<VH> implements DataProvider {

    protected final M mDelegateManager;

    protected DelegateRecyclerAdapter(M manager) {
        this.mDelegateManager = manager;
        if (null == mDelegateManager) {
            throw new IllegalArgumentException("Delegate manager is null");
        }
    }

    @Override
    public VH onCreateViewHolder(final ViewGroup parent, int viewType) {
        return mDelegateManager.onCreateViewHolder(parent, viewType);
    }

    @Override
    public final void onBindViewHolder(VH holder, int position) {
        mDelegateManager.onBindViewHolder(holder, position, holder.getItemViewType());
    }

    @Override
    public final int getItemViewType(int position) {
        return mDelegateManager.getItemViewType(position);
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        mDelegateManager.onDetachedFromRecyclerView(recyclerView);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mDelegateManager.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onViewDetachedFromWindow(VH holder) {
        super.onViewDetachedFromWindow(holder);
        mDelegateManager.onViewDetachedFromWindow(holder);
    }

    @Override
    public void onViewAttachedToWindow(VH holder) {
        super.onViewAttachedToWindow(holder);
        mDelegateManager.onViewAttachedToWindow(holder);
    }

    @Override
    public boolean onFailedToRecycleView(VH holder) {
        return mDelegateManager.onFailedToRecycleView(holder);
    }

    @Override
    public void onViewRecycled(VH holder) {
        super.onViewRecycled(holder);
        mDelegateManager.onViewRecycled(holder);
    }
}
