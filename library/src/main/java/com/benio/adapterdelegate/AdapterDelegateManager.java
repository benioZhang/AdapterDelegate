package com.benio.adapterdelegate;

import android.support.v4.util.SparseArrayCompat;
import android.view.ViewGroup;

import com.benio.adapterdelegate.interf.AdapterDelegate;
import com.benio.adapterdelegate.interf.DelegateManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AdapterDelegateManager<VH, AD extends AdapterDelegate<VH>> implements DelegateManager<VH, AD> {

    public static final int INITIAL_CAPACITY = 4;
    /**
     * delegate的优先级取决于其view type大小
     * 这是由SparseArrayCompat决定的
     */
    private SparseArrayCompat<AD> mDelegates;
    private AD mFallbackDelegate;

    public AdapterDelegateManager() {
        this(INITIAL_CAPACITY);// Default capacity of SparseArrayCompat is 10.
    }

    public AdapterDelegateManager(int initialCapacity) {
        mDelegates = new SparseArrayCompat<AD>(initialCapacity);
    }

    /**
     * @return an unmodifiable List of delegate exclude fallback
     */
    @Override
    public List<AD> getDelegates() {
        return getDelegates(false);
    }

    /**
     * @param includeFallback whether to include fallback in return value.
     * @return an unmodifiable List of delegate.
     */
    public List<AD> getDelegates(boolean includeFallback) {
        List<AD> result = new ArrayList<>();
        int delegatesCount = getDelegateCount();
        for (int i = 0; i < delegatesCount; i++) {
            AD delegate = getDelegateAt(i);
            result.add(delegate);
        }
        if (includeFallback) {
            result.add(mFallbackDelegate);
        }
        return Collections.unmodifiableList(result);
    }

    public AD getDelegateAt(int index) {
        return index >= 0 && index < mDelegates.size() ? mDelegates.valueAt(index) : null;
    }

    @Override
    public AD getDelegate(int viewType) {
        return getDelegate(viewType, null);
    }

    public AD getDelegate(int viewType, AD defaultValue) {
        final AD fallback = mFallbackDelegate;
        if (fallback != null && fallback.getItemViewType() == viewType) {
            return fallback;
        }
        return mDelegates.get(viewType, defaultValue);
    }

    @Override
    public AdapterDelegateManager<VH, AD> addDelegate(AD delegate) {
        return addDelegate(delegate, false);
    }

    public AdapterDelegateManager<VH, AD> addDelegate(AD delegate, boolean allowReplacingDelegate) {
        if (delegate == null) {
            throw new NullPointerException("AdapterDelegate is null!");
        }

        int viewType = delegate.getItemViewType();
        final AdapterDelegate<VH> fallback = mFallbackDelegate;
        if (fallback != null && fallback.getItemViewType() == viewType) {
            throw new IllegalArgumentException(
                    "Conflict: the passed AdapterDelegate has the same view type integer (value = " + viewType
                            + ") as the fallback AdapterDelegate");
        }

        if (!allowReplacingDelegate && getDelegate(viewType) != null) {
            throw new IllegalArgumentException(
                    "An AdapterDelegate is already registered for the viewType = " + viewType
                            + ". Already registered AdapterDelegate is " + getDelegate(viewType).getClass().getName());
        }

        mDelegates.put(viewType, delegate);
        return this;
    }

    @Override
    public AdapterDelegateManager<VH, AD> removeDelegate(AD delegate) {
        if (delegate == null) {
            throw new NullPointerException("AdapterDelegate is null");
        }
        int viewType = delegate.getItemViewType();
        AdapterDelegate<VH> queried = getDelegate(viewType);
        if (queried != null && queried == delegate) {
            if (queried == mFallbackDelegate) {
                mFallbackDelegate = null;
            } else {
                removeDelegate(viewType);
            }
        }
        return this;
    }

    public AdapterDelegateManager<VH, AD> removeDelegate(int viewType) {
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
     * @return number of delegate exclude fallback
     */
    @Override
    public int getDelegateCount() {
        return getDelegateCount(false);
    }

    @Override
    public int getItemViewType(int position) {
        int delegatesCount = getDelegateCount();
        for (int i = 0; i < delegatesCount; i++) {
            AdapterDelegate<VH> delegate = getDelegateAt(i);
            if (delegate.isForViewType(position)) {
                return delegate.getItemViewType();
            }
        }

        if (mFallbackDelegate != null) {
            return mFallbackDelegate.getItemViewType();
        }

        throw new IllegalArgumentException("No AdapterDelegate added that matches position = " + position);
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        AdapterDelegate<VH> delegate = getDelegate(viewType);
        if (delegate == null) {
            throw new NullPointerException("No AdapterDelegate added for ViewType " + viewType);
        }

        VH vh = delegate.onCreateViewHolder(parent);
        if (vh == null) {
            throw new NullPointerException(
                    "ViewHolder returned from AdapterDelegate " + delegate + " for ViewType =" + viewType
                            + " is null!");
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(VH viewHolder, int position, int viewType) {
        AdapterDelegate<VH> delegate = getDelegate(viewType);
        if (delegate == null) {
            throw new NullPointerException("No AdapterDelegate added for ViewType " + viewType);
        }

        delegate.onBindViewHolder(position, viewHolder);
    }

    public AD getFallbackDelegate() {
        return mFallbackDelegate;
    }

    public AdapterDelegateManager<VH, AD> setFallbackDelegate(AD fallbackDelegate) {
        this.mFallbackDelegate = null;// reset fallback delegate
        if (fallbackDelegate != null) {
            // Set a new fallback delegate
            int fallbackViewType = fallbackDelegate.getItemViewType();
            AdapterDelegate<VH> delegate = getDelegate(fallbackViewType);
            if (delegate != null) {
                throw new IllegalArgumentException(
                        "Conflict: The given fallback delegate has the same ViewType integer (value = "
                                + fallbackViewType + ")  as an already assigned AdapterDelegate "
                                + delegate.getClass().getName());
            }
        }
        this.mFallbackDelegate = fallbackDelegate;
        return this;
    }
}
