package com.benio.adapterdelegate;

import com.benio.adapterdelegate.interf.DataProvider;
import com.benio.adapterdelegate.interf.Delegate;

/**
 * An extension of {@link ListDelegateManager} that can set {@link DataProvider}
 * automatically for {@link AdapterDelegate} when a delegate is added to this DelegateManager
 *
 * @param <VH> The type of the ViewHolder
 */
public class AdapterDelegateManager<VH> extends ListDelegateManager<VH> {

    /**
     * dataProvider which is to provide data for AdapterDelegate
     */
    private DataProvider mDataProvider;

    public AdapterDelegateManager() {
    }

    public AdapterDelegateManager(int initialCapacity) {
        super(initialCapacity);
    }

    public AdapterDelegateManager(int initialCapacity, DataProvider dataProvider) {
        super(initialCapacity);
        mDataProvider = dataProvider;
    }

    public AdapterDelegateManager(DataProvider dataProvider) {
        mDataProvider = dataProvider;
    }

    public DataProvider getDataProvider() {
        return mDataProvider;
    }

    @Override
    public void onDelegateAdded(Delegate<VH> delegate) {
        if (mDataProvider != null
                && delegate instanceof AdapterDelegate
                && (((AdapterDelegate<VH>) delegate).mDataProvider) == null) {
            ((AdapterDelegate<VH>) delegate).mDataProvider = mDataProvider;
        }
    }

    @Override
    public void onDelegateRemoved(Delegate<VH> delegate) {
        if (mDataProvider != null
                && delegate instanceof AdapterDelegate
                && (((AdapterDelegate<VH>) delegate).mDataProvider) == mDataProvider) {
            ((AdapterDelegate<VH>) delegate).mDataProvider = null;
        }
    }
}
