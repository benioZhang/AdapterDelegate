# AdapterDelegate
Adapter多布局利器，同时适用于RecyclerView及ListView，GridView等AdapterView。
项目修改自[AdapterDelegates](https://github.com/sockeqwe/AdapterDelegates)

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
Delegate example
```Java
public class CatAdapterDelegate extends AdapterDelegate<RecyclerView.ViewHolder> {
    public CatAdapterDelegate() {
    }

    public CatAdapterDelegate(DataProvider dataProvider) {
        super(dataProvider);
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
    public boolean isForPosition(int position) {
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

Adapter example
```Java
public class MainAdapter extends DelegateRecyclerAdapter<RecyclerView.ViewHolder> {
    private List<DisplayableItem> mData;

    GeckoAdapterDelegate mGeckoAdapterDelegate;

    public MainAdapter(List<DisplayableItem> data) {
        this.mData = data;

        DelegateManager<RecyclerView.ViewHolder> manager = getDelegateManager();
        // 手动调用onViewAttachedToWindow，onViewDetachedFromWindow方法
        mGeckoAdapterDelegate = new GeckoAdapterDelegate(this);
        // Delegates
        manager.addDelegate(mGeckoAdapterDelegate)
                .addDelegate(new AdvertisementAdapterDelegate())
                .addDelegate(new CatAdapterDelegate())
                .addDelegate(new DogAdapterDelegate())
                .addDelegate(new SnakeAdapterDelegate());
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
        super.onViewAttachedToWindow(holder);
        // 判断是否是GeckoAdapterDelegate的ViewHolder
        if (mGeckoAdapterDelegate.isForPosition(holder.getAdapterPosition())) {
            mGeckoAdapterDelegate.onViewAttachedToWindow(holder);
        }
    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        if (mGeckoAdapterDelegate.isForPosition(holder.getAdapterPosition())) {
            mGeckoAdapterDelegate.onViewDetachedFromWindow(holder);
        }
    }
}
```

用于AdapterView时方法也是类似，Adapter可继承`DelegateBaseAdapter`，

## TODO
* ~~Gradle Dependency~~
* ~~完善例子~~
* ~~viewType自增~~
* 增加UnitTest

## Thanks
[sockeqwe](https://github.com/sockeqwe?tab=repositories)
