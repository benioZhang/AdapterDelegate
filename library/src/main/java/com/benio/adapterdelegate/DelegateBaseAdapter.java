package com.benio.adapterdelegate;

import android.view.ViewGroup;

import com.benio.adapterdelegate.interf.DataProvider;
import com.benio.adapterdelegate.interf.DelegateManager;

/**
 * A {@link ViewHolderAdapter} subclass using {@link ViewHolder} and {@link DelegateManager}.<p>
 * Created by benio on 2016/3/3.
 */
public abstract class DelegateBaseAdapter<VH extends ViewHolder> extends ViewHolderAdapter<VH>
        implements DataProvider {

    private final DelegateManager<VH> mDelegateManager;

    public DelegateBaseAdapter() {
        this(null);
    }

    public DelegateBaseAdapter(DelegateManager<VH> manager) {
        if (manager == null) {
            manager = generateDefaultDelegateManager();
        }
        this.mDelegateManager = manager;
    }

    protected DelegateManager<VH> generateDefaultDelegateManager() {
        return new AdapterDelegateManager<>(this);
    }

    /**
     * @return DelegateManager using in this adapter.
     */
    @SuppressWarnings("unchecked")
    protected <T extends DelegateManager<VH>> T getDelegateManager() {
        return (T) mDelegateManager;
    }

    @Override
    public final int getCount() {
        // same as getItemCount()
        return getItemCount();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return mDelegateManager.createViewHolder(parent, viewType);
    }

    public void onBindViewHolder(VH holder, int position) {
        mDelegateManager.bindViewHolder(holder, position, holder.mItemViewType);
    }

    @Override
    public int getViewTypeCount() {
        // same as Delegate count
        return mDelegateManager.getDelegateCount();
    }

    @Override
    public int getItemViewType(int position) {
        int viewType = mDelegateManager.getItemViewType(position);
        if (viewType == DelegateManager.INVALID_TYPE) {
            throw new IllegalArgumentException("No Delegate is responsible for position = " + position
                    + ". Please check your Delegates");
        }
        return viewType;
    }
}
