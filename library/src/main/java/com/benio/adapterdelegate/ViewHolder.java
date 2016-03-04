package com.benio.adapterdelegate;

import android.view.View;

public class ViewHolder {
    public static final int NO_POSITION = -1;
    public static final long NO_ID = -1;
    public static final int INVALID_TYPE = -1;

    public final View itemView;

    int mItemViewType = INVALID_TYPE;
    int mPosition = NO_POSITION;
    long mItemId = NO_ID;

    public ViewHolder(View itemView) {
        this.itemView = itemView;
    }

    /**
     * @return The the item's id if adapter has stable ids, {@link ViewHolder#NO_ID}
     * otherwise
     */
    public final long getItemId() {
        return mItemId;
    }

    /**
     * @return The position of this ViewHolder.
     */
    public final int getPosition() {
        return mPosition;
    }

    /**
     * @return The view type of this ViewHolder.
     */
    public final int getItemViewType() {
        return mItemViewType;
    }
}