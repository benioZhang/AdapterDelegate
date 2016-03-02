package com.benio.adapterdelegate;

import android.support.v7.widget.RecyclerView;

public abstract class AbsRecyclerAdapterDelegate<VH extends RecyclerView.ViewHolder>
        extends AbsAdapterDelegate<VH> implements RecyclerAdapterDelegate<VH> {

    public AbsRecyclerAdapterDelegate(DataProvider dataProvider, int viewType) {
        super(dataProvider, viewType);
    }

    @Override
    public void onViewAttachedToWindow(VH holder) {

    }

    @Override
    public void onViewDetachedFromWindow(VH holder) {

    }

    @Override
    public void onViewRecycled(VH holder) {

    }

    @Override
    public boolean onFailedToRecycleView(VH holder) {
        return false;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {

    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {

    }
}
