package com.benio.adapterdelegate;

import android.support.v7.widget.RecyclerView;

import com.benio.adapterdelegate.interf.RecyclerDelegate;
import com.benio.adapterdelegate.interf.RecyclerDelegateManager;

/**
 * Enhanced {@link AbsDelegateRecyclerAdapter}
 * Created by benio on 2016/3/3.
 */
public abstract class DelegateRecyclerAdapter<VH extends RecyclerView.ViewHolder, RD extends RecyclerDelegate<VH>>
        extends AbsDelegateRecyclerAdapter<VH, RD> {
    private final RecyclerDelegateManager<VH, RD> mManager;

    public DelegateRecyclerAdapter() {
        this(new RecyclerAdapterDelegateManager<VH, RD>());
    }

    protected DelegateRecyclerAdapter(RecyclerDelegateManager<VH, RD> manager) {
        super(manager);
        mManager = manager;
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        mManager.onDetachedFromRecyclerView(recyclerView);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mManager.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onViewDetachedFromWindow(VH holder) {
        super.onViewDetachedFromWindow(holder);
        mManager.onViewDetachedFromWindow(holder);
    }

    @Override
    public void onViewAttachedToWindow(VH holder) {
        super.onViewAttachedToWindow(holder);
        mManager.onViewAttachedToWindow(holder);
    }

    @Override
    public boolean onFailedToRecycleView(VH holder) {
        return mManager.onFailedToRecycleView(holder);
    }

    @Override
    public void onViewRecycled(VH holder) {
        super.onViewRecycled(holder);
        mManager.onViewRecycled(holder);
    }
}
