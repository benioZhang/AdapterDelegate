package com.benio.adapterdelegate;

import android.support.v4.util.SparseArrayCompat;
import android.view.ViewGroup;

public class AdapterDelegateManager<VH, AD extends AdapterDelegate<VH>> extends AbsAdapterDelegateManager<VH, AD> {

    public static final int INITIAL_CAPACITY = 4;

    private SparseArrayCompat<AD> mDelegates;
    private AD mFallbackDelegate;

    public AdapterDelegateManager() {
        this(INITIAL_CAPACITY);// default capacity of SparseArrayCompat is 10.
    }

    public AdapterDelegateManager(int initialCapacity) {
        mDelegates = new SparseArrayCompat<AD>(initialCapacity);
    }

    public AD getDelegateAt(int index) {
        return index >= 0 && index < mDelegates.size() ? mDelegates.valueAt(index) : null;
    }

    public AD getDelegate(int viewType) {
        return getDelegate(viewType, null);
    }

    public AD getDelegate(int viewType, AD defaultValue) {
        if (mFallbackDelegate != null && mFallbackDelegate.getItemViewType() == viewType) {
            return mFallbackDelegate;
        }
        return mDelegates.get(viewType, defaultValue);
    }

    /**
     * delegate的优先级取决于其view type
     * 这是由SparseArrayCompat决定的
     *
     * @param delegate
     * @return
     */
    public AdapterDelegateManager<VH, AD> addDelegate(AD delegate) {
        return addDelegate(delegate, false);
    }

    public AdapterDelegateManager<VH, AD> addDelegate(AD delegate, boolean allowReplacingDelegate) {
        if (delegate == null) {
            throw new NullPointerException("AdapterDelegate is null!");
        }

        int viewType = delegate.getItemViewType();
        if (mFallbackDelegate != null && mFallbackDelegate.getItemViewType() == viewType) {
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
    public int getDelegateCount() {
        return getDelegateCount(false);
    }

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

        throw new IllegalArgumentException(
                "No AdapterDelegate added that matches position = " + position);
    }

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
                        "Conflict: The given fallback - delegate has the same ViewType integer (value = "
                                + fallbackViewType + ")  as an already assigned AdapterDelegate "
                                + delegate.getClass().getName());
            }
        }
        this.mFallbackDelegate = fallbackDelegate;
        return this;
    }
}
