package com.benio.adapterdelegate;

import com.benio.adapterdelegate.interf.DataProvider;
import com.benio.adapterdelegate.interf.Delegate;

/**
 * A simple {@link Delegate} implementation that already implements {@link #getItem(int)} {@link #getItemCount()}
 * <p/>
 * Created by benio on 2016/1/26.
 *
 * @param <VH> The type of the ViewHolder
 */
public abstract class AdapterDelegate<VH> implements Delegate<VH>, DataProvider {

    private DataProvider mDataProvider;

    public AdapterDelegate() {
    }

    public AdapterDelegate(DataProvider dataProvider) {
        this.mDataProvider = dataProvider;
    }

    public void setDataProvider(DataProvider provider) {
        this.mDataProvider = provider;
    }

    public DataProvider getDataProvider() {
        return mDataProvider;
    }

    @Override
    public Object getItem(int position) {
        return mDataProvider != null ? mDataProvider.getItem(position) : null;
    }

    @Override
    public int getItemCount() {
        return mDataProvider != null ? mDataProvider.getItemCount() : 0;
    }
}
