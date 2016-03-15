package com.benio.adapterdelegate;

import android.support.v7.widget.RecyclerView;

import com.benio.adapterdelegate.interf.DataProvider;
import com.benio.adapterdelegate.interf.RecyclerDelegate;

/**
 * A simple {@link RecyclerDelegate} implementation that already implements {@link
 * #getItemViewType()}
 * <p/>
 * Created by benio on 2016/1/26.
 *
 * @param <VH> The type of the ViewHolder extends {@link RecyclerView.ViewHolder}
 */
public abstract class RecyclerAdapterDelegate<VH extends RecyclerView.ViewHolder>
        extends AdapterDelegate<VH> implements RecyclerDelegate<VH> {

    public RecyclerAdapterDelegate(DataProvider dataProvider, int viewType) {
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
