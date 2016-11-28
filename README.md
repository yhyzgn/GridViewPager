# GridViewPager
ViewPager中的GridView，可以自由定制Item布局，提供充足的自定义参数等

## 使用

### Eclipse

> 下载项目，添加依赖；也可以找到`/GridViewPager/gvp/`目录下的`gvp.aar`，解压引用里边的`classes.jar`库

### Android Studio (Gradle)

> 库依赖和aar包引用可参考Eclipse使用方式，如果使用Gradle依赖的话请参考下面第一、二步

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
  	compile 'com.github.SilentWolf1993:GridViewPager:v1.1'
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
    public void onItemClick(AdapterView<?> parent, View view, int position) {
      ToastUtils.toastShort(MainActivity.this, "第" + position + "个条目被点击");
    }
  });

  gvpContent.setOnItemLongClickListener(new GridViewPager.OnItemLongClickListener() {
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position) {
      ToastUtils.toastShort(MainActivity.this, "第" + position + "个条目被长按");
      //以下操作只是为了展示notifyDataSetChanged()的用法
      int size = 4;
      if (size < IMG_ARR.length) {
        //改变数据并刷新适配器
        //...
      }
      gvpContent.notifyDataSetChanged();
      return false;
    }
  });
  ```

------------------

> 到此结束，尽情享用吧，^_^ ···

