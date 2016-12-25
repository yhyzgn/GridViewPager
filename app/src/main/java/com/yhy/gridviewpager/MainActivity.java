package com.yhy.gridviewpager;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.yhy.gvp.adapter.GVPAdapter;
import com.yhy.gvp.listener.OnItemClickListener;
import com.yhy.gvp.listener.OnItemLongClickListener;
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

    private GridViewPager gvpContentA;
    private GridViewPager gvpContentB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        gvpContentA = (GridViewPager) findViewById(R.id.gvp_content_a);
        gvpContentB = (GridViewPager) findViewById(R.id.gvp_content_b);

        //计算图片宽高（这里宽高一致）
        imgWidthHeight = ScreenUtils.getScreenWidth(this) / gvpContentA.getNumColumns();

        MyAdapter adapter = new MyAdapter();

        gvpContentA.setGVPAdapter(adapter);

        gvpContentA.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position) {
                ToastUtils.toastShort(MainActivity.this, "第" + position + "个条目被点击");
            }
        });

        gvpContentA.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position) {
                ToastUtils.toastShort(MainActivity.this, "第" + position + "个条目被长按");
                //...

                //刷新
                gvpContentA.notifyDataSetChanged();
                return false;
            }
        });

        gvpContentB.setGVPAdapter(adapter);
    }

    private class MyAdapter implements GVPAdapter {
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
    }
}
