package com.benio.adapterdelegate.interf;

import android.support.v7.widget.RecyclerView;

/**
 * Created by benio on 2016/3/4.
 */
public interface RecyclerDelegateManager<VH extends RecyclerView.ViewHolder, AD extends RecyclerAdapterDelegate<VH>>
        extends DelegateManager<VH, AD> {
    /**
     * call on {@link RecyclerView.Adapter#onViewAttachedToWindow(RecyclerView.ViewHolder)}
     *
     * @param holder
     */
    void onViewAttachedToWindow(VH holder);

    /**
     * call on {@link RecyclerView.Adapter#onViewDetachedFromWindow(RecyclerView.ViewHolder)}
     *
     * @param holder
     */
    void onViewDetachedFromWindow(VH holder);

    /**
     * call on {@link RecyclerView.Adapter#onViewRecycled(RecyclerView.ViewHolder)}
     *
     * @param holder
     */
    void onViewRecycled(VH holder);

    /**
     * call on {@link RecyclerView.Adapter#onFailedToRecycleView(RecyclerView.ViewHolder)}
     *
     * @param holder
     */
    boolean onFailedToRecycleView(VH holder);

    /**
     * call on {@link RecyclerView.Adapter#onAttachedToRecyclerView(RecyclerView)}
     *
     * @param recyclerView
     */
    void onAttachedToRecyclerView(RecyclerView recyclerView);

    /**
     * call on {@link RecyclerView.Adapter#onDetachedFromRecyclerView(RecyclerView)}
     *
     * @param recyclerView
     */
    void onDetachedFromRecyclerView(RecyclerView recyclerView);
}
