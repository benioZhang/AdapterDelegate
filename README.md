# AdapterDelegate
Adapter多布局利器，同时适用于RecyclerView及ListView，GridView等AdapterView。项目思路来自[AdapterDelegates](https://github.com/sockeqwe/AdapterDelegates)

### Dependency
**Gradle**
```
compile 'com.benio.adapterdelegate:library:1.1.0
```
**Maven**
```
<dependency>
    <groupId>com.benio.adapterdelegate</groupId>
    <artifactId>library</artifactId>
    <version>1.1.0</version>
    <type>pom</type>
</dependency>
```

### 特点
* 数据源由DataProvider提供，与DelegateManager，Delegate分离
* 同时适用于RecyclerView及ListView，GridView等AdapterView
* Delegate，DelegateManager等均为接口，可以自定义拓展

### Talk is cheap. Show me the code
**Using for RecyclerView**
`Delegate` example
```Java
public class CatAdapterDelegate extends AdapterDelegate<RecyclerView.ViewHolder> {

    public CatAdapterDelegate(DataProvider dataProvider, int viewType) {
        super(dataProvider, viewType);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cat, parent, false);
        return new CatViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(int position, RecyclerView.ViewHolder holder) {
        TextView textView = ((CatViewHolder) holder).name;
        Cat cat = (Cat) getItem(position);
        textView.setText(cat.getName());
    }

    @Override
    public boolean isForViewType(int position) {
        return getItem(position) instanceof Cat;
    }

    static class CatViewHolder extends RecyclerView.ViewHolder {

        public TextView name;

        public CatViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
        }
    }
}
```

`Adapter` example
```Java
public class MainAdapter extends AbsDelegateRecyclerAdapter<RecyclerView.ViewHolder, Delegate<RecyclerView.ViewHolder>> {
    private List<DisplayableItem> mData;

    GeckoAdapterDelegate mGeckoAdapterDelegate;

    public MainAdapter(List<DisplayableItem> data) {
        this.mData = data;

        // 可以手动调用onViewAttachedToWindow，onViewDetachedFromWindow等方法
        mGeckoAdapterDelegate = new GeckoAdapterDelegate(this, 3);
        // Delegates
        getDelegateManager().addDelegate(new AdvertisementAdapterDelegate(this, 5))
                .addDelegate(new CatAdapterDelegate(this, 1))
                .addDelegate(new DogAdapterDelegate(this, 2))
                .addDelegate(mGeckoAdapterDelegate)
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

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        mGeckoAdapterDelegate.onViewAttachedToWindow(holder);
        super.onViewAttachedToWindow(holder);
    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        mGeckoAdapterDelegate.onViewDetachedFromWindow(holder);
        super.onViewDetachedFromWindow(holder);
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

        // 这里要注意viewType必须连续，而且从0开始
        // 这个限制是因为ListView的RecycleBin造成的
        getDelegateManager().addDelegate(new AdvertisementListAdapterDelegate(this, 1))
                .addDelegate(new CatListAdapterDelegate(this, 2))
                .addDelegate(new DogListAdapterDelegate(this, 3))
                .addDelegate(new GeckoListAdapterDelegate(this, 4))
                .addDelegate(new SnakeListAdapterDelegate(this, 0));
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

* **使用ListView的时候，要注意Delegate的viewType需要连续且从0开始**
* 两处用法其实是一样的，换的只是Delegate泛型ViewHolder，还有Adapter的父类（毕竟Adapter是不一样的)
ViewHolder可以参考[RecyclerViewHolder](https://github.com/benioZhang/AdapterDelegate/blob/master/app/src/main/java/com/benio/adapterdelegate/sample/model/RecyclerViewHolder.java)和[ListViewHolder](https://github.com/benioZhang/AdapterDelegate/blob/master/app/src/main/java/com/benio/adapterdelegate/sample/model/ListViewHolder.java)<br>
* 不想手动调用`RecyclerView.Adapter的onViewAttachedToWindow`等方法的，可以继承`DelegateRecyclerAdapter`，具体可查看[ReptilesAdapter](https://github.com/benioZhang/AdapterDelegate/blob/master/app%2Fsrc%2Fmain%2Fjava%2Fcom%2Fbenio%2Fadapterdelegate%2Fsample%2Fadapter%2FReptilesAdapter.java)和[GeckoAdapterDelegate](https://github.com/benioZhang/AdapterDelegate/blob/master/app%2Fsrc%2Fmain%2Fjava%2Fcom%2Fbenio%2Fadapterdelegate%2Fsample%2Fdelegate%2Frecyclerview%2FGeckoAdapterDelegate.java)<br/>

## TODO
* ~~Gradle Dependency~~
* ~~完善例子~~

## Thanks
[sockeqwe](https://github.com/sockeqwe?tab=repositories)
