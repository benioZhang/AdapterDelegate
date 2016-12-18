package com.benio.adapterdelegate;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.benio.adapterdelegate.interf.DelegateManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A pool to cache view type of {@link RecyclerView.Adapter}
 * Created by benio on 2016/12/3.
 */
public class ViewTypePool {
    private static final String TAG = "ViewTypePool";
    private static final boolean DEBUG = true;

    public static final int NO_CACHE = DelegateManager.INVALID_TYPE;
    private static final int CHANGE = 0;
    private static final int REMOVE = 1;
    private static final int INSERT = 2;
    private static final int MOVE = 3;

    private CacheList mCache;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.AdapterDataObserver mDataObserver = new RecyclerView.AdapterDataObserver() {

        @Override
        public void onChanged() {
            onItemRangeChanged(0, mCache.size());
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            processUpdate(CHANGE, positionStart, itemCount);
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            processUpdate(INSERT, positionStart, itemCount);
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            processUpdate(REMOVE, positionStart, itemCount);
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            processUpdate(MOVE, fromPosition, toPosition);
        }
    };

    private static class CacheList extends ArrayList<Integer> {
        public CacheList(int capacity) {
            super(capacity);
        }

        /**
         * Make it public and call from outside class
         */
        @Override
        public void removeRange(int fromIndex, int toIndex) {
            super.removeRange(fromIndex, toIndex);
        }
    }

    /**
     * Constructs a new {@code ViewTypeCache} instance with {@code 10} initial capacity.
     */
    public ViewTypePool() {
        this(10);
    }

    /**
     * Constructs a new instance of {@code ViewTypeCache} with the specified
     * initial capacity.
     *
     * @param initialCapacity the initial capacity of this {@code ViewTypeCache}.
     */
    public ViewTypePool(int initialCapacity) {
        mCache = new CacheList(initialCapacity);
    }

    private String cmdToString(int cmd) {
        String str = null;
        if (cmd == CHANGE) {
            str = "CHANGE";
        } else if (cmd == REMOVE) {
            str = "REMOVE";
        } else if (cmd == INSERT) {
            str = "INSERT";
        } else if (cmd == MOVE) {
            str = "MOVE";
        } else {
            str = "UNKNOWN";
        }
        return str;
    }

    private void processUpdate(int cmd, int positionStart, int itemCount) {
        final int cacheSize = mCache.size();
        final int start = positionStart >= 0 ? positionStart : 0;
        final int end = positionStart + itemCount >= cacheSize ? cacheSize : positionStart + itemCount;
        if (DEBUG) {
            Log.d(TAG, "cmd = [" + cmdToString(cmd) + "], positionStart = [" + positionStart + "], itemCount = [" + itemCount + "], "
                    + "cacheSize = [" + cacheSize + "], start = [" + start + "], end = [" + end + "]");
        }
        if (cmd == CHANGE) {
            // [1, 2, 0, 3, 1] -> [1, NO_CACHE, NO_CACHE, 3, 1]
            for (int i = start; i < end; i++) {
                mCache.set(i, NO_CACHE);
            }
        } else if (cmd == REMOVE) {
            // [1, 2, 0, 3, 1] -> [1, 3, 1]
            if (start < end) {
                mCache.removeRange(start, end);
            }
        } else if (cmd == INSERT) {
            // [1, 2, 0, 3, 1] -> [1, 2, NO_CACHE, NO_CACHE, 0, 3, 1]
            CacheList insertList = new CacheList(itemCount);
            for (int i = 0; i < itemCount; i++) {
                insertList.add(NO_CACHE);
            }
            if (start < cacheSize) {
                mCache.addAll(start, insertList);
            } else {
                mCache.addAll(insertList);
            }
        } else if (cmd == MOVE) {
            final int fromPosition = positionStart;
            final int toPosition = itemCount;
            if (fromPosition < 0 || fromPosition >= cacheSize) {
                throw new IllegalArgumentException("Invalid position: " + fromPosition);
            }
            if (toPosition < 0 || toPosition >= cacheSize) {
                throw new IllegalArgumentException("Invalid position: " + toPosition);
            }
            if (fromPosition == toPosition) {
                return;
            }
            if (fromPosition < toPosition) {
                // [1, 2, 0, 3, 1] -> [1, 0, 3, 1, 2]
                int value = mCache.get(fromPosition);
                for (int i = fromPosition; i < toPosition; i++) {
                    mCache.set(i, mCache.get(i + 1));
                }
                mCache.set(toPosition, value);
            } else {
                // [1, 2, 0, 3, 1] -> [1, 1, 2, 0, 3]
                int value = mCache.get(fromPosition);
                for (int i = fromPosition; i > toPosition; i--) {
                    mCache.set(i, mCache.get(i - 1));
                }
                mCache.set(toPosition, value);
            }
        } else {
            throw new IllegalArgumentException("Unknown cmd :" + cmd);
        }
        Log.d(TAG, "processUpdate: " + mCache);
    }

    /**
     * Return the view type of the item at <code>position</code>.
     * If there is no cache at <code>position</code>, {@code NO_CACHE} will be return.
     *
     * @param position position to query
     * @return view type of the specified position cache in the cache pool.
     */
    public int get(int position) {
        int viewType = position >= 0 && mCache.size() > position ? mCache.get(position) : NO_CACHE;
        if (DEBUG && viewType != NO_CACHE) {
            Log.d(TAG, "cache hit: position = [" + position + "], viewType = [" + viewType + "]");
        }
        return viewType;
    }

    /**
     * Put the view type of the item at <code>position</code> into the cache pool.
     *
     * @param position The position of the item.
     * @param viewType The view type of the item.
     */
    public void put(int position, int viewType) {
        List<Integer> cache = mCache;
        int cacheSize = cache.size();
        if (cacheSize > position) {
            if (DEBUG) {
                Log.d(TAG, "cache miss: position = [" + position + "], viewType change: " + cache.get(position) + " -> " + viewType);
            }
            cache.set(position, viewType);
        } else if (cacheSize == position) {
            if (DEBUG) {
                Log.d(TAG, "cache miss: position = [" + position + "], viewType = [" + viewType + "]");
            }
            cache.add(viewType);
        } else {
            // throw ?
            if (DEBUG) {
                Log.e(TAG, "error position: " + position + "and viewType: " + viewType);
            }
        }
    }

    /**
     * Clear all view type of this cache pool.
     */
    public void clear() {
        Collections.fill(mCache, NO_CACHE);
    }

    /**
     * @return The current size of this cache pool.
     */
    public int size() {
        return mCache.size();
    }

    /**
     * Attaches the ViewTypeCache to the provided RecyclerView.Adapter. If ViewTypeCache is already
     * attached to a RecyclerView.Adapter, it will first detach from the previous one.
     *
     * @param adapter The RecyclerView.Adapter instance to which you want to add this cache.
     */
    public void attachToAdapter(RecyclerView.Adapter adapter) {
        if (mAdapter == adapter) {
            return; // nothing to do
        }
        detachFromAdapter();
        mAdapter = adapter;
        if (adapter != null) {
            adapter.registerAdapterDataObserver(mDataObserver);
        }
    }

    /**
     * Detaches the ViewTypeCache from the provided RecyclerView.Adapter.
     */
    public void detachFromAdapter() {
        if (mAdapter != null) {
            mAdapter.unregisterAdapterDataObserver(mDataObserver);
            mCache.clear();
        }
    }
}
