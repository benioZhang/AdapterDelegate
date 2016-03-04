package com.benio.adapterdelegate.interf;

import android.view.ViewGroup;

import java.util.List;

/**
 * Created by benio on 2016/2/12.
 */
public interface DelegateManager<VH, AD extends AdapterDelegate<VH>> {

    DelegateManager<VH, AD> addDelegate(AD delegate);

    DelegateManager<VH, AD> removeDelegate(AD delegate);

    AD getDelegate(int viewType);

    List<AD> getDelegates();

    int getDelegateCount();

    int getItemViewType(int position);

    VH onCreateViewHolder(ViewGroup parent, int viewType);

    void onBindViewHolder(VH viewHolder, int position, int viewType);
}
