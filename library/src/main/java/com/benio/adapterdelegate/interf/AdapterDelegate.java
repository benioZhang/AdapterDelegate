package com.benio.adapterdelegate.interf;

import android.view.ViewGroup;

/**
 * Created by benio on 2016/1/26.
 */
public interface AdapterDelegate<VH> {

    int getItemViewType();

    VH onCreateViewHolder(ViewGroup parent);

    void onBindViewHolder(int position, VH holder);

    boolean isForViewType(int position);
}
