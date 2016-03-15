package com.benio.adapterdelegate;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.benio.adapterdelegate.interf.DataProvider;
import com.benio.adapterdelegate.interf.Delegate;
import com.benio.adapterdelegate.interf.DelegateManager;

/**
 * A {@link RecyclerView.Adapter} subclass using ViewHolder and DelegateManager.<p>
 * Created by benio on 2016/1/30.
 */
public abstract class AbsDelegateRecyclerAdapter<VH extends RecyclerView.ViewHolder, D extends Delegate<VH>>
        extends RecyclerView.Adapter<VH> implements DataProvider {

    private final DelegateManager<VH, D> mDelegateManager;

    public AbsDelegateRecyclerAdapter() {
        this(new AdapterDelegateManager<VH, D>());
    }

    protected AbsDelegateRecyclerAdapter(DelegateManager<VH, D> manager) {
        this.mDelegateManager = manager;
        if (null == mDelegateManager) {
            throw new NullPointerException("Delegate manager is null");
        }
    }

    @SuppressWarnings("unchecked")
    protected <T extends DelegateManager<VH, D>> T getDelegateManager() {
        return (T) mDelegateManager;
    }

    @Override
    public VH onCreateViewHolder(final ViewGroup parent, int viewType) {
        return mDelegateManager.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        mDelegateManager.onBindViewHolder(holder, position, holder.getItemViewType());
    }

    @Override
    public int getItemViewType(int position) {
        return mDelegateManager.getItemViewType(position);
    }
}
