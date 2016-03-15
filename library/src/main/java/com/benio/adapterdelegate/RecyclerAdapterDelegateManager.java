package com.benio.adapterdelegate;

import android.support.v7.widget.RecyclerView;

import com.benio.adapterdelegate.interf.RecyclerDelegate;
import com.benio.adapterdelegate.interf.RecyclerDelegateManager;

/**
 * An implementation of RecyclerDelegateManager and extends {@link AdapterDelegateManager}
 * Created by benio on 2016/2/19.
 */
public class RecyclerAdapterDelegateManager<VH extends RecyclerView.ViewHolder, RD extends RecyclerDelegate<VH>>
        extends AdapterDelegateManager<VH, RD> implements RecyclerDelegateManager<VH, RD> {

    public RecyclerAdapterDelegateManager() {
        super();
    }

    public RecyclerAdapterDelegateManager(int initialCapacity) {
        super(initialCapacity);
    }

    @Override
    public RecyclerAdapterDelegateManager<VH, RD> addDelegate(RD delegate) {
        //cast self to RecyclerAdapterDelegateManager
        return (RecyclerAdapterDelegateManager<VH, RD>) super.addDelegate(delegate);
    }

    @Override
    public RecyclerAdapterDelegateManager<VH, RD> addDelegate(RD delegate, boolean allowReplacingDelegate) {
        return (RecyclerAdapterDelegateManager<VH, RD>) super.addDelegate(delegate, allowReplacingDelegate);
    }

    @Override
    public RecyclerAdapterDelegateManager<VH, RD> removeDelegate(RD delegate) {
        return (RecyclerAdapterDelegateManager<VH, RD>) super.removeDelegate(delegate);
    }

    @Override
    public RecyclerAdapterDelegateManager<VH, RD> removeDelegate(int viewType) {
        return (RecyclerAdapterDelegateManager<VH, RD>) super.removeDelegate(viewType);
    }

    @Override
    public RecyclerAdapterDelegateManager<VH, RD> setFallbackDelegate(RD fallbackDelegate) {
        return (RecyclerAdapterDelegateManager<VH, RD>) super.setFallbackDelegate(fallbackDelegate);
    }

    @Override
    public void onViewAttachedToWindow(VH holder) {
        int viewType = holder.getItemViewType();
        RecyclerDelegate<VH> delegate = getDelegate(viewType);
        delegate.onViewAttachedToWindow(holder);
    }

    @Override
    public void onViewDetachedFromWindow(VH holder) {
        int viewType = holder.getItemViewType();
        RecyclerDelegate<VH> delegate = getDelegate(viewType);
        delegate.onViewDetachedFromWindow(holder);
    }

    @Override
    public void onViewRecycled(VH holder) {
        int viewType = holder.getItemViewType();
        RecyclerDelegate<VH> delegate = getDelegate(viewType);
        delegate.onViewRecycled(holder);
    }

    @Override
    public boolean onFailedToRecycleView(VH holder) {
        int viewType = holder.getItemViewType();
        RecyclerDelegate<VH> delegate = getDelegate(viewType);
        return delegate.onFailedToRecycleView(holder);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        RecyclerDelegate<VH> fallback = getFallbackDelegate();
        if (fallback != null) {
            fallback.onAttachedToRecyclerView(recyclerView);
        }
        int count = getDelegateCount();
        for (int i = 0; i < count; i++) {
            RecyclerDelegate<VH> delegate = getDelegateAt(i);
            delegate.onAttachedToRecyclerView(recyclerView);
        }
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        RecyclerDelegate<VH> fallback = getFallbackDelegate();
        if (fallback != null) {
            fallback.onDetachedFromRecyclerView(recyclerView);
        }
        int count = getDelegateCount();
        for (int i = 0; i < count; i++) {
            RecyclerDelegate<VH> delegate = getDelegateAt(i);
            delegate.onDetachedFromRecyclerView(recyclerView);
        }
    }
}
