package com.benio.adapterdelegate;


public abstract class AbsAdapterDelegate<VH> implements AdapterDelegate<VH>, DataProvider {
    private int mViewType;
    private DataProvider mDataProvider;

    public AbsAdapterDelegate(DataProvider dataProvider, int viewType) {
        this.mDataProvider = dataProvider;
        this.mViewType = viewType;
    }

    public void setDataProvider(DataProvider provider) {
        if (mDataProvider != null) {
            throw new IllegalArgumentException("DataProvider " + mDataProvider + " already exists in this delegate");
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
