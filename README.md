# GridViewPager
![GridViewPager](https://img.shields.io/badge/GridViewPager-1.0.2-brightgreen.svg) ViewPager结合GridView，轻松实现类似表情面板的控件。可自由定制Item布局，提供充足的自定义参数等。效果如下：

![ScreenShots](screenshots/screenshots.gif)

Demo下载地址：<a href="https://fir.im/gvpmg2w">GridViewPager</a> ，或者扫描以下二维码安装：

![GridViewPager](data:image/png;base64,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)



--------------------------------------------------

## 使用方法

### Eclipse

> 添加项目依赖；也可以下载`/GridViewPager/gvp/`目录下的<a href="gvp/gvp.aar">gvp.aar</a>，解压引用里边的`classes.jar`库

### Android Studio (Gradle)

> 库依赖和aar包引用可参考Eclipse使用方式，如果使用Gradle依赖的话请参考下面第一、二步

* 第一步，在Module的`builder.gradle`中添加依赖（把`latestVersion`替换成第一行徽章中的版本号）

  ```groovy
  dependencies {
  	compile 'com.yhy:gvp:latestVersion'
  }
  ```

* 第二步，在需要显示`GridViewPager`的布局文件中添加如下代码

  * 在布局文件跟布局中添加如下代码（如果不在布局文件中配置参数就可以不添加）

    ```xml
    xmlns:app="http://schemas.android.com/apk/res-auto"
    ```

  * 添加控件布局代码及相关配置参数

    ```xml
    	<!-- 自定义属性 -->
    	<com.yhy.gvp.widget.GridViewPager
            android:id="@+id/gvp_content_a"
            android:layout_width="match_parent"
            android:layout_height="260dp"
            app:horizontal_spacing="2dp"
            app:num_columns="4"
            app:page_size="6"
            app:scroll_bar_enable="false"
            app:vertical_spacing="2dp"/>

    	<!-- 使用默认属性 -->
        <com.yhy.gvp.widget.GridViewPager
            android:id="@+id/gvp_content_b"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"/>
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

--------------------------------------------------------------



### 自定义属性说明

| 属性名                | 默认值   | 属性说明             |
| ------------------ | ----- | ---------------- |
| page_size          | 4     | 每页显示数量           |
| num_columns        | 4     | 每页显示列数           |
| vertical_spacing   | 0.5dp | 条目之间垂直方向的间距      |
| horizontal_spacing | 0.5dp | 条目之间水平方向的间距      |
| scroll_bar_enable  | false | 是否显示GridView的滚动条 |

------------------------------------------------------------------

就这样吧，不喜勿碰。。^_^ ···

