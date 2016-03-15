package com.benio.adapterdelegate.interf;

import android.view.ViewGroup;

import java.util.List;

/**
 * Manager of Delegate. Must call following methods in your adapter:
 * <ul>
 * <li> {@link #onBindViewHolder(VH, int, int)}
 * <li> {@link #onCreateViewHolder(ViewGroup, int)}
 * <li> {@link #getItemViewType(int)}
 * </ul>
 * <p/>
 * Created by benio on 2016/2/12.
 *
 * @param <VH> Type of the ViewHolder
 * @param <D>  Type of {@link Delegate}
 */
public interface DelegateManager<VH, D extends Delegate<VH>> {
    /**
     * Adds a {@link Delegate} to this manager
     *
     * @param delegate Delegate to add
     * @return self
     */
    DelegateManager<VH, D> addDelegate(D delegate);

    /**
     * Removes a {@link Delegate} from this manager
     *
     * @param delegate Delegate to remove
     * @return self
     */
    DelegateManager<VH, D> removeDelegate(D delegate);

    /**
     * Returns the delegate specified by viewType in this manager.
     *
     * @param viewType viewType specified by a delegate
     * @return delegate specified by viewType in this manager.
     */
    D getDelegate(int viewType);

    /**
     * @return a List of delegate.
     */
    List<D> getDelegates();

    /**
     * @return number of delegate.
     */
    int getDelegateCount();

    /**
     * Returns the view type of the item at <code>position</code> for the purposes
     * of view recycling.
     *
     * @param position position to query
     * @return integer value identifying the type of the view needed to represent the item at
     * <code>position</code>.
     */
    int getItemViewType(int position);

    /**
     * Called when a new ViewHolder of the given type to represent an item is needed.
     * <p>
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     * <p>
     * The new ViewHolder will be used to display items of the adapter using
     * {@link #onBindViewHolder(VH, int, int)}. Since it will be re-used to display
     * different items in the data set, it is a good idea to cache references to sub views of
     * the View to avoid unnecessary {@link android.view.View#findViewById(int)} calls.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     * @see #getItemViewType(int)
     * @see #onBindViewHolder(VH, int, int)
     */
    VH onCreateViewHolder(ViewGroup parent, int viewType);

    /**
     * Called to display the data at the specified position. This method should
     * update the contents of the ViewHolder to reflect the item at the given
     * position.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     * @param viewType The viewType of ViewHolder
     */
    void onBindViewHolder(VH holder, int position, int viewType);
}
