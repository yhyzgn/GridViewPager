package com.yhy.demo;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.yhy.gvp.global.Config;
import com.yhy.gvp.utils.ScreenUtils;
import com.yhy.gvp.utils.ToastUtils;
import com.yhy.gvp.widget.GridViewPager;

public class MainActivity extends Activity {
    private static final int[] IMG_ARR = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d,
            R.drawable.e, R.drawable.f, R.drawable.g, R.drawable.h, R.drawable.i, R.drawable.j, R
            .drawable.k, R.drawable.l, R.drawable.m, R.drawable.n, R.drawable.o, R.drawable.p, R
            .drawable.q, R.drawable.r, R.drawable.s, R.drawable.t, R.drawable.u, R.drawable.v, R
            .drawable.w, R.drawable.x, R.drawable.y, R.drawable.z, R.drawable.u, R.drawable.v, R
            .drawable.aa, R.drawable.bb, R.drawable.cc, R.drawable.dd, R.drawable.ee, R.drawable
            .ff};
    private static int imgWidthHeight;

    private GridViewPager gvpContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        gvpContent = (GridViewPager) findViewById(R.id.gvp_content);

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

        //计算图片宽高（这里宽高一致）
        imgWidthHeight = ScreenUtils.getScreenWidth(this) / Config.getInstance().getNumColumns();

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
    }
}
