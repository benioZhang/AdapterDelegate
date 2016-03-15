package com.benio.adapterdelegate;

import com.benio.adapterdelegate.interf.DataProvider;
import com.benio.adapterdelegate.interf.Delegate;

/**
 * A simple {@link Delegate} implementation that already implements {@link
 * #getItemViewType()} {@link #getItem(int)} {@link #getItemCount()}
 * <p/>
 * Created by benio on 2016/1/26.
 *
 * @param <VH> The type of the ViewHolder
 */
public abstract class AdapterDelegate<VH> implements Delegate<VH>, DataProvider {
    private int mViewType;
    private DataProvider mDataProvider;

    public AdapterDelegate(DataProvider dataProvider, int viewType) {
        this.mDataProvider = dataProvider;
        this.mViewType = viewType;
    }

    public void setDataProvider(DataProvider provider) {
        if (mDataProvider != null) {
            throw new IllegalStateException("DataProvider " + mDataProvider + " already exists in this delegate");
        }
        this.mDataProvider = provider;
    }

    public DataProvider getDataProvider() {
        return mDataProvider;
    }

    @Override
    public final int getItemViewType() {
        return mViewType;
    }

    @Override
    public final Object getItem(int position) {
        return mDataProvider != null ? mDataProvider.getItem(position) : null;
    }

    @Override
    public final int getItemCount() {
        return mDataProvider != null ? mDataProvider.getItemCount() : 0;
    }
}
