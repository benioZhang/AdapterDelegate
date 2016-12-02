package com.benio.adapterdelegate;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;

import com.benio.adapterdelegate.interf.DataProvider;
import com.benio.adapterdelegate.interf.DelegateManager;

/**
 * A {@link RecyclerView.Adapter} subclass using ViewHolder and DelegateManager.<p>
 * Created by benio on 2016/1/30.
 */
public abstract class DelegateRecyclerAdapter<VH extends ViewHolder> extends Adapter<VH>
        implements DataProvider {

    private final DelegateManager<VH> mDelegateManager;

    public DelegateRecyclerAdapter() {
        this(null);
    }

    public DelegateRecyclerAdapter(DelegateManager<VH> manager) {
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
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return mDelegateManager.createViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        mDelegateManager.bindViewHolder(holder, position, holder.getItemViewType());
    }

    @Override
    public int getItemViewType(int position) {
        int viewType = mDelegateManager.getItemViewType(position);
        if (viewType == DelegateManager.INVALID_TYPE) {
            throw new IllegalArgumentException("No Delegate is responsible for position =" + position
                    + ". Please check your Delegates");
        }
        return viewType;
    }
}
