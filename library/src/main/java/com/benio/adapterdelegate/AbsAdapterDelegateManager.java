package com.benio.adapterdelegate;

import android.view.ViewGroup;

/**
 * Created by benio on 2016/2/12.
 */
public abstract class AbsAdapterDelegateManager<VH, AD extends AdapterDelegate<VH>> {

    public abstract AdapterDelegateManager<VH, AD> addDelegate(AD delegate);

    public abstract AD getDelegate(int viewType);

    public abstract AdapterDelegateManager<VH, AD> removeDelegate(int viewType);

    public abstract AdapterDelegateManager<VH, AD> removeDelegate(AD delegate);

    public abstract int getDelegateCount();

    public abstract int getItemViewType(int position);

    public abstract VH onCreateViewHolder(ViewGroup parent, int viewType);

    public abstract void onBindViewHolder(VH viewHolder, int position, int viewType);
}
