package com.benio.adapterdelegate;

import android.support.v7.widget.RecyclerView;

public interface RecyclerAdapterDelegate<VH extends RecyclerView.ViewHolder> extends AdapterDelegate<VH> {

    void onViewAttachedToWindow(VH holder);

    void onViewDetachedFromWindow(VH holder);

    void onViewRecycled(VH holder);

    boolean onFailedToRecycleView(VH holder);

    void onAttachedToRecyclerView(RecyclerView recyclerView);

    void onDetachedFromRecyclerView(RecyclerView recyclerView);
}
