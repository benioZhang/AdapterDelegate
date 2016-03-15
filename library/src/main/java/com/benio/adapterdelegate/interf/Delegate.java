package com.benio.adapterdelegate.interf;

import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;

/**
 * A delegate provides method to hook in {@link RecyclerView.Adapter} or
 * {@link android.widget.BaseAdapter} lifecycle.
 * This "hook in" mechanism is provided by {@link DelegateManager} and that is the
 * component you have to use.
 * <p>
 * Created by benio on 2016/1/26.
 *
 * @param <VH> The type of the ViewHolder
 */
public interface Delegate<VH> {

    /**
     * Get the view type integer. Must be unique within every Adapter
     *
     * @return The view type of this ViewHolder.
     */
    int getItemViewType();

    /**
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     * <p>
     * The new ViewHolder will be used to display items of the adapter using
     * {@link #onBindViewHolder(int, VH)}. Since it will be re-used to display
     * different items in the data set, it is a good idea to cache references to sub views of
     * the View to avoid unnecessary {@link android.view.View#findViewById(int)} calls.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @return A new ViewHolder that holds a View of the given view type.
     * @see #getItemViewType()
     * @see #onBindViewHolder(int, VH)
     */
    VH onCreateViewHolder(ViewGroup parent);

    /**
     * Called to display the data at the specified position. This method should
     * update the contents of the ViewHolder to reflect the item at the given
     * position.
     * <p>
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    void onBindViewHolder(int position, VH holder);

    /**
     * Called to determine whether this Delegate is the responsible for the position
     *
     * @param position The position in the adapter
     * @return true, if this item is responsible,  otherwise false
     */
    boolean isForViewType(int position);
}
