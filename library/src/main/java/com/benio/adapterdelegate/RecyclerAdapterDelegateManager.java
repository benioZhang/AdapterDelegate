package com.benio.adapterdelegate;

import android.support.v7.widget.RecyclerView;

import com.benio.adapterdelegate.interf.RecyclerAdapterDelegate;
import com.benio.adapterdelegate.interf.RecyclerDelegateManager;

/**
 * Created by benio on 2016/2/19.
 */
public class RecyclerAdapterDelegateManager<VH extends RecyclerView.ViewHolder, AD extends RecyclerAdapterDelegate<VH>>
        extends AdapterDelegateManager<VH, AD> implements RecyclerDelegateManager<VH, AD> {

    public RecyclerAdapterDelegateManager() {
        super();
    }

    public RecyclerAdapterDelegateManager(int initialCapacity) {
        super(initialCapacity);
    }

    @Override
    public RecyclerAdapterDelegateManager<VH, AD> addDelegate(AD delegate) {
        return (RecyclerAdapterDelegateManager<VH, AD>) super.addDelegate(delegate);
    }

    @Override
    public RecyclerAdapterDelegateManager<VH, AD> addDelegate(AD delegate, boolean allowReplacingDelegate) {
        return (RecyclerAdapterDelegateManager<VH, AD>) super.addDelegate(delegate, allowReplacingDelegate);
    }

    @Override
    public RecyclerAdapterDelegateManager<VH, AD> removeDelegate(AD delegate) {
        return (RecyclerAdapterDelegateManager<VH, AD>) super.removeDelegate(delegate);
    }

    @Override
    public RecyclerAdapterDelegateManager<VH, AD> removeDelegate(int viewType) {
        return (RecyclerAdapterDelegateManager<VH, AD>) super.removeDelegate(viewType);
    }

    @Override
    public RecyclerAdapterDelegateManager<VH, AD> setFallbackDelegate(AD fallbackDelegate) {
        return (RecyclerAdapterDelegateManager<VH, AD>) super.setFallbackDelegate(fallbackDelegate);
    }

    @Override
    public void onViewAttachedToWindow(VH holder) {
        int viewType = holder.getItemViewType();
        RecyclerAdapterDelegate<VH> delegate = getDelegate(viewType);
        delegate.onViewAttachedToWindow(holder);
    }

    @Override
    public void onViewDetachedFromWindow(VH holder) {
        int viewType = holder.getItemViewType();
        RecyclerAdapterDelegate<VH> delegate = getDelegate(viewType);
        delegate.onViewDetachedFromWindow(holder);
    }

    @Override
    public void onViewRecycled(VH holder) {
        int viewType = holder.getItemViewType();
        RecyclerAdapterDelegate<VH> delegate = getDelegate(viewType);
        delegate.onViewRecycled(holder);
    }

    @Override
    public boolean onFailedToRecycleView(VH holder) {
        int viewType = holder.getItemViewType();
        RecyclerAdapterDelegate<VH> delegate = getDelegate(viewType);
        return delegate.onFailedToRecycleView(holder);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        RecyclerAdapterDelegate<VH> fallback = getFallbackDelegate();
        if (fallback != null) {
            fallback.onAttachedToRecyclerView(recyclerView);
        }
        int count = getDelegateCount();
        for (int i = 0; i < count; i++) {
            RecyclerAdapterDelegate<VH> delegate = getDelegateAt(i);
            delegate.onAttachedToRecyclerView(recyclerView);
        }
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        RecyclerAdapterDelegate<VH> fallback = getFallbackDelegate();
        if (fallback != null) {
            fallback.onDetachedFromRecyclerView(recyclerView);
        }
        int count = getDelegateCount();
        for (int i = 0; i < count; i++) {
            RecyclerAdapterDelegate<VH> delegate = getDelegateAt(i);
            delegate.onDetachedFromRecyclerView(recyclerView);
        }
    }
}
