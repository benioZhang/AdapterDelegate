package com.benio.adapterdelegate;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.benio.adapterdelegate.interf.DataProvider;
import com.benio.adapterdelegate.interf.AdapterDelegate;
import com.benio.adapterdelegate.interf.DelegateManager;

/**
 * Created by benio on 2016/3/3.
 */
public abstract class AbsDelegateBaseAdapter<VH extends ViewHolder, AD extends AdapterDelegate<VH>>
        extends BaseAdapter implements DataProvider {
    private final DelegateManager<VH, AD> mDelegateManager;

    protected AbsDelegateBaseAdapter(DelegateManager<VH, AD> manager) {
        this.mDelegateManager = manager;
        if (null == mDelegateManager) {
            throw new NullPointerException("AdapterDelegate manager is null");
        }
    }

    @SuppressWarnings("unchecked")
    protected <T extends DelegateManager<VH, AD>> T getDelegateManager() {
        return (T) mDelegateManager;
    }

    @Override
    public final int getItemCount() {
        // same as getCount()
        return getCount();
    }

    @SuppressWarnings("unchecked")
    @Override
    public final View getView(int position, View convertView, ViewGroup parent) {
        VH holder;
        if (null == convertView) {
            holder = createViewHolder(parent, getItemViewType(position));
            // convert view set tag
            convertView = holder.itemView;
            convertView.setTag(holder);
        } else {
            holder = (VH) convertView.getTag();
        }
        bindViewHolder(holder, position);
        return holder.itemView;
    }

    /**
     * This method calls {@link #onCreateViewHolder(ViewGroup, int)} to create a new
     * {@link ViewHolder} and initializes some fields to be used by ViewHolder.
     *
     * @see #onCreateViewHolder(ViewGroup, int)
     */
    public final VH createViewHolder(final ViewGroup parent, int viewType) {
        final VH holder = onCreateViewHolder(parent, viewType);
        holder.mItemViewType = viewType;
        return holder;
    }

    /**
     * This method internally calls {@link #onBindViewHolder(ViewHolder, int)} to update the
     * {@link ViewHolder} contents with the item at the given position and also sets up some
     * fields to be used by ViewHolder
     *
     * @see #onBindViewHolder(ViewHolder, int)
     */
    public final void bindViewHolder(VH holder, int position) {
        holder.mPosition = position;
        holder.mItemId = position;
        onBindViewHolder(holder, position);
    }

    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return mDelegateManager.onCreateViewHolder(parent, viewType);
    }

    public void onBindViewHolder(VH holder, int position) {
        mDelegateManager.onBindViewHolder(holder, position, holder.mItemViewType);
    }

    @Override
    public int getViewTypeCount() {
        return mDelegateManager.getDelegateCount();
    }

    @Override
    public int getItemViewType(int position) {
        return mDelegateManager.getItemViewType(position);
    }


}
