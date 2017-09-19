# GridViewPager
![GridViewPager](https://img.shields.io/badge/GridViewPager-1.1.0-brightgreen.svg) `ViewPager`结合`RecyclerView`，轻松实现类似表情面板的控件。可自由定制`Item`布局，提供充足的自定义参数等。也处理了条目点击事件和条目长按事件。效果如下：

![ScreenShots](imgs/screenshots.gif)

Demo下载地址：<a href="https://fir.im/gvpmg2w">GridViewPager</a> ，或者扫描以下二维码安装：

![GridViewPager](imgs/download.png)



--------------------------------------------------

## 使用方法

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
      android:layout_height="wrap_content"
      android:layout_marginTop="120dp"
      app:num_columns="4"
      app:page_size="8" />
    ```

  * 测试数据

    ```java
    // 测试图片
    private static final int[] IMG_ARR = {R.drawable.a, R.drawable.b, 
      R.drawable.c,R.drawable.d, R.drawable.e, R.drawable.f, R.drawable.g, 
      R.drawable.h, R.drawable.i, R.drawable.j, R.drawable.k, R.drawable.l, 
      R.drawable.m, R.drawable.n, R.drawable.o, R.drawable.p, R.drawable.q, 
      R.drawable.r, R.drawable.s, R.drawable.t, R.drawable.u, R.drawable.v, 
      R.drawable.w, R.drawable.x, R.drawable.y, R.drawable.z, R.drawable.u, 
      R.drawable.v, R.drawable.aa, R.drawable.bb, R.drawable.cc, R.drawable.dd,
      R.drawable.ee, R.drawable.ff};

    // 数据集合
    private final List<ItemData> mDataList = new ArrayList<>();

    // ...
    for (int i = 0; i < IMG_ARR.length; i++) {
      mDataList.add(new ItemData(IMG_ARR[i], "Item " + i));
    }
    // ...
    ```

  * 第四步，创建`itemView`的布局

    ```xml
    <?xml version="1.0" encoding="utf-8"?>
    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:background="#fff"
        android:orientation="vertical">

        <ImageView
            android:id="@+id/iv_img"
            android:layout_width="match_parent"
            android:layout_height="96dp"
            android:scaleType="centerCrop" />

        <TextView
            android:id="@+id/tv_name"
            android:layout_width="match_parent"
            android:layout_height="32dp"
            android:layout_marginTop="4dp"
            android:gravity="center"
            android:textSize="14sp" />
    </LinearLayout>
    ```

  * 第五步，在代码中通过`findViewById`获取到`GridViewPager`控件，设置适配器

    ```java
    // 定义适配器
    private class MyAdapter extends GVPAdapter<ItemData> {

            public MyAdapter(int layoutId, List<ItemData> dataList) {
                super(layoutId, dataList);
            }

            @Override
            public void bind(View item, int position, ItemData data) {
                // 在这里绑定每个itemView的相关数据
                ImageView ivImg = (ImageView) item.findViewById(R.id.iv_img);
                TextView tvName = (TextView) item.findViewById(R.id.tv_name);

                ivImg.setImageResource(data.imgId);
                tvName.setText(data.name);
            }
        }

    // 创建适配器
    final MyAdapter adapter = new MyAdapter(R.layout.layout_item, mDataList);
    // 设置适配器
    gvpContentA.setGVPAdapter(adapter);
    ```

  * 第六步，设置相关事件（点击事件和长按事件）

    ```java
    // 设置相关事件
    adapter.setOnItemClickListener(new OnItemClickListener<ItemData>() {

      @Override
      public void onItemClick(View view, int position, ItemData data) {
        toast("点击了 " + data.name);
      }
    });

    adapter.setOnItemLongClickListener(new OnItemLongClickListener<ItemData>() {

      @Override
      public boolean onItemLongClick(View view, int position, ItemData data) {
        toast("长按了 " + data.name + ", 每页增加一条数据");
        gvpContentA.setPageSize(gvpContentA.getPageSize() + 1);
        // 刷新
        gvpContentA.notifyDataSetChanged();
        return false;
      }
    });
    ```

--------------------------------------------------------------

### 自定义属性说明

| 属性名         | 默认值  | 属性说明   |
| ----------- | ---- | ------ |
| page_size   | 4    | 每页显示数量 |
| num_columns | 4    | 每页显示列数 |

------------------------------------------------------------------

就这样吧，不喜勿喷哦。。:joy::joy: