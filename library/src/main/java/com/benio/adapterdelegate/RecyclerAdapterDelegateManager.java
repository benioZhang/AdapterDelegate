package com.benio.adapterdelegate;

import android.support.v7.widget.RecyclerView;

/**
 * Created by benio on 2016/2/19.
 */
public class RecyclerAdapterDelegateManager<VH extends RecyclerView.ViewHolder, AD extends RecyclerAdapterDelegate<VH>>
        extends AdapterDelegateManager<VH, AD> {

    public RecyclerAdapterDelegateManager() {
        super();
    }

    public RecyclerAdapterDelegateManager(int initialCapacity) {
        super(initialCapacity);
    }

    public void onViewAttachedToWindow(VH holder) {
        int viewType = holder.getItemViewType();
        AD delegate = getDelegate(viewType);
        delegate.onViewAttachedToWindow(holder);
    }

    public void onViewDetachedFromWindow(VH holder) {
        int viewType = holder.getItemViewType();
        AD delegate = getDelegate(viewType);
        delegate.onViewDetachedFromWindow(holder);
    }

    public void onViewRecycled(VH holder) {
        int viewType = holder.getItemViewType();
        AD delegate = getDelegate(viewType);
        delegate.onViewRecycled(holder);
    }

    public boolean onFailedToRecycleView(VH holder) {
        int viewType = holder.getItemViewType();
        AD delegate = getDelegate(viewType);
        return delegate.onFailedToRecycleView(holder);
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        AD fallback = getFallbackDelegate();
        if (fallback != null) {
            fallback.onAttachedToRecyclerView(recyclerView);
        }
        int count = getDelegateCount();
        for (int i = 0; i < count; i++) {
            AD delegate = getDelegateAt(i);
            delegate.onAttachedToRecyclerView(recyclerView);
        }
    }

    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        AD fallback = getFallbackDelegate();
        if (fallback != null) {
            fallback.onDetachedFromRecyclerView(recyclerView);
        }
        int count = getDelegateCount();
        for (int i = 0; i < count; i++) {
            AD delegate = getDelegateAt(i);
            delegate.onDetachedFromRecyclerView(recyclerView);
        }
    }
}
