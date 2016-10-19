# GridViewPager
ViewPager中的GridView，可以自由定制Item布局，提供充足的自定义参数等

## 使用

### Eclipse

> 下载项目，添加依赖；也可以直接引入`/GridViewPager/blirary/`目录下的`gvp.jar`

### Android Studio (Gradle)

> 库依赖和jar包引用可参考Eclipse使用方式，如果使用Gradle依赖的话请参考下面第一、二步

* 第一步，在Project的`builder.gradle`中添加以下代码

  ```groovy
  allprojects {
  	repositories {
  		...
  		maven { url "https://jitpack.io" }
  	}
  }
  ```

* 第二步，在Module的`builder.gradle`中添加以下代码

  ```groovy
  dependencies {
  	...
  	compile 'com.github.SilentWolf1993:GridViewPager:v1.0'
  }
  ```


* 第三步，在需要显示`GridViewPager`的布局文件中添加如下代码

  * 在布局文件跟布局中添加如下代码（如果不在布局文件中配置参数就可以不添加）

    ```xml
    xmlns:app="http://schemas.android.com/apk/res-auto"
    ```

  * 添加控件布局代码及相关配置参数

    ```xml
    <com.yhy.gvp.widget.GridViewPager
            android:id="@+id/gvp_content"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            app:horizontal_spacing="2dp"
            app:num_columns="4"
            app:padding_left_right="2dp"
            app:padding_top_bottom="2dp"
            app:page_size="6"
            app:scroll_bar_enable="false"
            app:vertical_spacing="2dp" />
    ```

  * 测试数据

    ```java
    //测试数据
    private static final int[] IMG_ARR = {R.drawable.a, R.drawable.b, 
      R.drawable.c,R.drawable.d, R.drawable.e, R.drawable.f, R.drawable.g, 
      R.drawable.h, R.drawable.i, R.drawable.j, R.drawable.k, R.drawable.l, 
      R.drawable.m, R.drawable.n, R.drawable.o, R.drawable.p, R.drawable.q, 
      R.drawable.r, R.drawable.s, R.drawable.t, R.drawable.u, R.drawable.v, 
      R.drawable.w, R.drawable.x, R.drawable.y, R.drawable.z, R.drawable.u, 
      R.drawable.v, R.drawable.aa, R.drawable.bb, R.drawable.cc, R.drawable.dd,
      R.drawable.ee, R.drawable.ff};
    ```

  * 也可以不在这里配置相关参数，在代码中配置给`Config`类（务必在设置适配器前配置）

    ```java
    //向单例模式配置类中设置参数
    //如果在布局文件中页配置过相应参数的话，以这里的参数为准
    Config.getInstance()
          //条目总数，不设置时系统根据适配器getCount的返回值决定
          .setItemCount(IMG_ARR.length)
          //每页显示条目数量
          .setPageSize(10)
          //每页显示列数
          .setNumColumns(4)
          //条目垂直间距，单位dp
          .setVerticalSpacing(4)
          //条目水平间距单位dp
          .setHorizontalSpacing(4)
          //是否显示滚动条，默认为ture，即显示
          .setScrollBarEnable(false)
          //面板内边距，第一个值为上下边距，第二个值为左右边距
          .setPadding(new int[]{4, 4});
    ```

* 第四步，在代码中通过`findViewById`获取到`GridViewPager`控件，设置适配器

  ```java
  gvpContent.setGVPAdapter(new GridViewPager.GVPAdapter() {
  private AbsListView.LayoutParams params = new AbsListView.LayoutParams
    (imgWidthHeight, imgWidthHeight);

    @Override
    public int getCount() {
      return IMG_ARR.length;
    }

    @Override
    public Integer getItem(int position) {
      return IMG_ARR[position];
    }

    @Override
    public long getItemId(int position) {
      return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      ImageView iv = new ImageView(MainActivity.this);
      iv.setLayoutParams(params);
      iv.setImageResource(getItem(position));
      iv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
      iv.setBackgroundColor(Color.parseColor("#ff0000"));
      return iv;
    }
  });
  ```

* 第五步，设置相关事件（点击事件和长按事件）

  ```java
  gvpContent.setOnItemClickListener(new GridViewPager.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
      ToastUtils.toastShort(MainActivity.this, "第" + position + "个条目被点击");
    }
  });

  gvpContent.setOnItemLongClickListener(new GridViewPager.OnItemLongClickListener() {
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long
                                   id) {
      ToastUtils.toastShort(MainActivity.this, "第" + position + "个条目被长按");
      int size = Config.getInstance().getPageSize();
      if (size < Config.getInstance().getItemCount()) {
        //改变数据并刷新适配器
        Config.getInstance().setPageSize(++size);
      }
      gvpContent.notifyDataSetChanged();
      return false;
    }
  });
  ```

------------------

> 到此结束，尽情享用吧，^_^ ···

