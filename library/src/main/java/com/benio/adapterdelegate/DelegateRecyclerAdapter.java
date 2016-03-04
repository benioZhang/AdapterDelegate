package com.benio.adapterdelegate;

import android.support.v7.widget.RecyclerView;

import com.benio.adapterdelegate.interf.RecyclerAdapterDelegate;
import com.benio.adapterdelegate.interf.RecyclerDelegateManager;

/**
 * Created by benio on 2016/3/3.
 */
public abstract class DelegateRecyclerAdapter<VH extends RecyclerView.ViewHolder, AD extends RecyclerAdapterDelegate<VH>>
        extends AbsDelegateRecyclerAdapter<VH, AD> {

    protected DelegateRecyclerAdapter(RecyclerDelegateManager<VH, AD> manager) {
        super(manager);
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        ((RecyclerDelegateManager<VH, AD>) getDelegateManager()).onDetachedFromRecyclerView(recyclerView);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        ((RecyclerDelegateManager<VH, AD>) getDelegateManager()).onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onViewDetachedFromWindow(VH holder) {
        super.onViewDetachedFromWindow(holder);
        ((RecyclerDelegateManager<VH, AD>) getDelegateManager()).onViewDetachedFromWindow(holder);
    }

    @Override
    public void onViewAttachedToWindow(VH holder) {
        super.onViewAttachedToWindow(holder);
        ((RecyclerDelegateManager<VH, AD>) getDelegateManager()).onViewAttachedToWindow(holder);
    }

    @Override
    public boolean onFailedToRecycleView(VH holder) {
        return ((RecyclerDelegateManager<VH, AD>) getDelegateManager()).onFailedToRecycleView(holder);
    }

    @Override
    public void onViewRecycled(VH holder) {
        super.onViewRecycled(holder);
        ((RecyclerDelegateManager<VH, AD>) getDelegateManager()).onViewRecycled(holder);
    }
}
