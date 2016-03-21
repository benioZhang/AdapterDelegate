# AdapterDelegate
更好用的AdapterDelegate.For RecyclerView or ListView Adapter，原文[AdapterDelegates](https://github.com/sockeqwe/AdapterDelegates)

# Gradle Dependency
```compile 'com.benio.adapterdelegate:library:1.0.0'```

## 改良
* 数据源与`DelegateManager`,`Delegate`分离,由`DataProvider`提供数据
* 同时适用于RecyclerView，ListView，GridView等
* `Delegate`,`DelegateManager`等均为接口，可以自定义拓展

##Talk is cheap. Show me the code
#### Using for RecyclerView
`Delegate` example
```Java
public class CatAdapterDelegate extends AdapterDelegate<RecyclerViewHolder> {

    public CatAdapterDelegate(DataProvider dataProvider, int viewType) {
        super(dataProvider, viewType);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cat, parent, false);
        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(int position, RecyclerViewHolder holder) {
        TextView textView = holder.getView(R.id.name);
        Cat cat = (Cat) getItem(position);
        textView.setText(cat.getName());
    }

    @Override
    public boolean isForViewType(int position) {
        return getItem(position) instanceof Cat;
    }
}
```

`Adapter` example
```Java
public class MainAdapter extends AbsDelegateRecyclerAdapter<RecyclerViewHolder, Delegate<RecyclerViewHolder>> {
    private List<DisplayableItem> mData;

    public MainAdapter(List<DisplayableItem> data) {
        this.mData = data;

        // Delegates
        getDelegateManager().addDelegate(new AdvertisementAdapterDelegate(this, 5))
                .addDelegate(new CatAdapterDelegate(this, 1))
                .addDelegate(new DogAdapterDelegate(this, 2))
                .addDelegate(new GeckoAdapterDelegate(this, 3))
                .addDelegate(new SnakeAdapterDelegate(this, 4));
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
```


#### Using for ListView
`Delegate` example
```Java
public class CatListAdapterDelegate extends AdapterDelegate<ListViewHolder> {

    public CatListAdapterDelegate(DataProvider dataProvider, int viewType) {
        super(dataProvider, viewType);
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cat, parent, false);
        return new ListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(int position, ListViewHolder holder) {
        TextView textView = holder.getView(R.id.name);
        Cat cat = (Cat) getItem(position);
        textView.setText(cat.getName());
    }

    @Override
    public boolean isForViewType(int position) {
        return getItem(position) instanceof Cat;
    }
}
```

`Adapter` example
```Java
public class MainListAdapter extends AbsDelegateBaseAdapter<ListViewHolder, Delegate<ListViewHolder>> {
    private List<DisplayableItem> mData;

    public MainListAdapter(List<DisplayableItem> data) {
        this.mData = data;

        // Delegates
        getDelegateManager().addDelegate(new AdvertisementListAdapterDelegate(this, 1))
                .addDelegate(new CatListAdapterDelegate(this, 2))
                .addDelegate(new DogListAdapterDelegate(this, 3))
                .addDelegate(new GeckoListAdapterDelegate(this, 4))
                .addDelegate(new SnakeListAdapterDelegate(this, 5));
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
```
ViewHolder可以参考[RecyclerViewHolder](https://github.com/benioZhang/AdapterDelegate/blob/master/app/src/main/java/com/benio/adapterdelegate/sample/model/RecyclerViewHolder.java)
[ListViewHolder](https://github.com/benioZhang/AdapterDelegate/blob/master/app/src/main/java/com/benio/adapterdelegate/sample/model/ListViewHolder.java)<br>
~~两处用法其实是一样的，换的只是Delegate泛型ViewHolder，还有Adapter的父类（毕竟Adapter是不一样的)<br>

## TODO
* ~~Gradle Dependency~~
* 完善例子

## Thanks
[sockeqwe](https://github.com/sockeqwe?tab=repositories)
