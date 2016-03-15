package com.benio.adapterdelegate.interf;

import android.support.v7.widget.RecyclerView;

/**
 * A delegate provides more methods to hook in {@link RecyclerView.Adapter} lifecycle.
 * <p>
 * Created by benio on 2016/3/4.
 *
 * @param <VH> The type of the ViewHolder extends {@link RecyclerView.ViewHolder}
 */
public interface RecyclerDelegate<VH extends RecyclerView.ViewHolder> extends Delegate<VH> {
    /**
     * Called from {@link RecyclerView.Adapter#onViewAttachedToWindow(RecyclerView.ViewHolder)}
     */
    void onViewAttachedToWindow(VH holder);

    /**
     * Called from {@link RecyclerView.Adapter#onViewDetachedFromWindow(RecyclerView.ViewHolder)}
     */
    void onViewDetachedFromWindow(VH holder);

    /**
     * Called from {@link RecyclerView.Adapter#onViewRecycled(RecyclerView.ViewHolder)}
     */
    void onViewRecycled(VH holder);

    /**
     * Called from {@link RecyclerView.Adapter#onFailedToRecycleView(RecyclerView.ViewHolder)}
     */
    boolean onFailedToRecycleView(VH holder);

    /**
     * Called from {@link RecyclerView.Adapter#onAttachedToRecyclerView(RecyclerView)})}
     */
    void onAttachedToRecyclerView(RecyclerView recyclerView);

    /**
     * Called from {@link RecyclerView.Adapter#onAttachedToRecyclerView(RecyclerView)})}
     */
    void onDetachedFromRecyclerView(RecyclerView recyclerView);
}
