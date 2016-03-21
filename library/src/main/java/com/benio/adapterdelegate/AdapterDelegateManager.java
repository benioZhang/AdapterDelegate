package com.benio.adapterdelegate;

import android.support.v4.util.SparseArrayCompat;
import android.view.ViewGroup;

import com.benio.adapterdelegate.interf.Delegate;
import com.benio.adapterdelegate.interf.DelegateManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * An implementation of DelegateManager
 *
 * @param <VH> The type of the ViewHolder
 * @param <D>  The type of Delegate
 */
public class AdapterDelegateManager<VH, D extends Delegate<VH>> implements DelegateManager<VH, D> {
    /**
     * Default capacity of {@link AdapterDelegateManager}
     */
    public static final int INITIAL_CAPACITY = 4;
    /**
     * A map for viewType to Delegate
     */
    private SparseArrayCompat<D> mDelegates;    //delegate的优先级取决于其view type大小 这是由SparseArrayCompat决定的
    private D mFallbackDelegate;

    /**
     * Creates a new AdapterDelegateManager with default capacity.
     */
    public AdapterDelegateManager() {
        this(INITIAL_CAPACITY);// Default capacity of SparseArrayCompat is 10.
    }

    /**
     * Creates a new AdapterDelegateManager with specified capacity.
     *
     * @param initialCapacity capacity of AdapterDelegateManager
     */
    public AdapterDelegateManager(int initialCapacity) {
        mDelegates = new SparseArrayCompat<D>(initialCapacity);
    }

    /**
     * @return an unmodifiable List of delegate exclude fallback
     */
    @Override
    public List<D> getDelegates() {
        return getDelegates(false);
    }

    /**
     * @param includeFallback whether to include fallback in return value.
     * @return an unmodifiable List of delegate.
     */
    public List<D> getDelegates(boolean includeFallback) {
        List<D> result = new ArrayList<>();
        int delegatesCount = getDelegateCount(false);
        for (int i = 0; i < delegatesCount; i++) {
            D delegate = getDelegateAt(i);
            result.add(delegate);
        }
        if (includeFallback && mFallbackDelegate != null) {
            result.add(mFallbackDelegate);
        }
        return Collections.unmodifiableList(result);
    }

    /**
     * @param index index of Delegate
     * @return Delegate stored in this manager
     */
    public D getDelegateAt(int index) {
        return index >= 0 && index < mDelegates.size() ? mDelegates.valueAt(index) : null;
    }

    @Override
    public D getDelegate(int viewType) {
        return getDelegate(viewType, null);
    }

    /**
     * Returns the delegate specified by viewType in this manager or the specified Delegate
     * if not found.
     *
     * @param viewType     viewType specified by a delegate
     * @param defaultValue specified Delegate if not found
     * @return delegate specified by viewType in this manager.
     */
    public D getDelegate(int viewType, D defaultValue) {
        final D fallback = mFallbackDelegate;
        if (fallback != null && fallback.getItemViewType() == viewType) {
            return fallback;
        }
        return mDelegates.get(viewType, defaultValue);
    }

    /**
     * Adds a Delegate. Internally calls {@link #addDelegate(Delegate, boolean)} with false as parameter.
     *
     * @param delegate Delegate to add
     * @return self
     * @throws IllegalArgumentException if a Delegate is already added (registered)
     *                                  with the same ViewType {@link Delegate#getItemViewType()}.
     * @see #addDelegate(Delegate, boolean)
     */
    @Override
    public AdapterDelegateManager<VH, D> addDelegate(D delegate) {
        return addDelegate(delegate, false);
    }

    /**
     * Adds a Delegate.
     *
     * @param delegate               Delegate to add
     * @param allowReplacingDelegate if true, you allow to replacing the given delegate any previous
     *                               delegate for the same view type. if false, you disallow and a {@link IllegalArgumentException}
     *                               will be thrown if you try to replace an already registered Delegate for the
     *                               same view type.
     * @return self
     * @throws IllegalArgumentException if <b>allowReplacingDelegate</b>  is false and a Delegate is already added (registered)
     *                                  with the same ViewType {@link Delegate#getItemViewType()}.
     * @throws IllegalArgumentException if the {@link Delegate#getItemViewType()} is the same
     *                                  as fallback Delegate one.
     * @see #setFallbackDelegate(Delegate)
     */
    public AdapterDelegateManager<VH, D> addDelegate(D delegate, boolean allowReplacingDelegate) {
        if (delegate == null) {
            throw new NullPointerException("Delegate is null!");
        }

        int viewType = delegate.getItemViewType();
        final Delegate<VH> fallback = mFallbackDelegate;
        if (fallback != null && fallback.getItemViewType() == viewType) {
            throw new IllegalArgumentException(
                    "Conflict: the passed Delegate has the same view type integer (value = " + viewType
                            + ") as the fallback Delegate");
        }

        if (!allowReplacingDelegate && getDelegate(viewType) != null) {
            throw new IllegalArgumentException(
                    "An Delegate is already registered for the viewType = " + viewType
                            + ". Already registered Delegate is " + getDelegate(viewType).getClass().getName());
        }

        mDelegates.put(viewType, delegate);
        return this;
    }

    /**
     * Removes a previously registered Delegate if and only if the passed Delegate is registered
     * (checks the reference of the object). This will not remove any other Delegate for the same
     * viewType (if there is any).
     *
     * @param delegate Delegate to remove
     * @return self
     */
    @Override
    public AdapterDelegateManager<VH, D> removeDelegate(D delegate) {
        if (null == delegate) {
            throw new NullPointerException("Delegate is null");
        }
        int viewType = delegate.getItemViewType();
        Delegate<VH> queried = getDelegate(viewType);
        if (queried != null && queried == delegate) {
            if (queried == mFallbackDelegate) {
                mFallbackDelegate = null;
            } else {
                removeDelegate(viewType);
            }
        }
        return this;
    }

    /**
     * Removes Delegate for the given viewType.
     *
     * @param viewType viewType of Delegate
     * @return self
     */
    public AdapterDelegateManager<VH, D> removeDelegate(int viewType) {
        mDelegates.remove(viewType);
        return this;
    }

    /**
     * @param includeFallback whether to include fallback in return value.
     * @return number of delegate
     */
    public int getDelegateCount(boolean includeFallback) {
        int size = mDelegates.size();
        if (includeFallback && mFallbackDelegate != null) {
            size++;
        }
        return size;
    }

    /**
     * @return number of delegate include fallback
     */
    @Override
    public int getDelegateCount() {
        return getDelegateCount(true);
    }

    @Override
    public int getItemViewType(int position) {
        //Internally scans all the registered Delegate and picks the right one to return the ViewType.
        int delegatesCount = getDelegateCount(false);
        for (int i = 0; i < delegatesCount; i++) {
            Delegate<VH> delegate = getDelegateAt(i);
            if (delegate.isForViewType(position)) {
                return delegate.getItemViewType();
            }
        }

        if (mFallbackDelegate != null) {
            return mFallbackDelegate.getItemViewType();
        }

        throw new IllegalArgumentException("No Delegate added that matches position = " + position);
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        Delegate<VH> delegate = getDelegate(viewType);
        if (delegate == null) {
            throw new NullPointerException("No Delegate added for ViewType " + viewType);
        }

        VH vh = delegate.onCreateViewHolder(parent);
        if (vh == null) {
            throw new NullPointerException(
                    "ViewHolder returned from Delegate " + delegate + " for ViewType =" + viewType
                            + " is null!");
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(VH viewHolder, int position, int viewType) {
        Delegate<VH> delegate = getDelegate(viewType);
        if (delegate == null) {
            throw new NullPointerException("No Delegate added for ViewType " + viewType);
        }

        delegate.onBindViewHolder(position, viewHolder);
    }

    public D getFallbackDelegate() {
        return mFallbackDelegate;
    }

    /**
     * Set a fallback delegate that should be used if no {@link Delegate} has been found that
     * can handle a certain view type.
     * <br>
     * As a fallback delegate,{@link Delegate#isForViewType(int)} will be ignored
     *
     * @param fallbackDelegate The Delegate that should be used as fallback if no other
     *                         Delegate has handled a certain view type. <code>null</code> you can set this to null if
     *                         you want to remove a previously set fallback Delegate
     * @throws IllegalArgumentException If passed Fallback
     */
    public AdapterDelegateManager<VH, D> setFallbackDelegate(D fallbackDelegate) {
        this.mFallbackDelegate = null;// reset fallback delegate
        if (fallbackDelegate != null) {
            // Set a new fallback delegate
            int fallbackViewType = fallbackDelegate.getItemViewType();
            Delegate<VH> delegate = getDelegate(fallbackViewType);
            if (delegate != null) {
                throw new IllegalArgumentException(
                        "Conflict: The given fallback delegate has the same ViewType integer (value = "
                                + fallbackViewType + ")  as an already assigned Delegate "
                                + delegate.getClass().getName());
            }
        }
        this.mFallbackDelegate = fallbackDelegate;
        return this;
    }
}
