package com.yhy.gridviewpager;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yhy.gvp.adapter.GVPAdapter;
import com.yhy.gvp.listener.OnItemClickListener;
import com.yhy.gvp.listener.OnItemLongClickListener;
import com.yhy.gvp.widget.GridViewPager;
import com.yhy.widget.core.recycler.div.RvDivider;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private static final int[] IMG_ARR = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d,
            R.drawable.e, R.drawable.f, R.drawable.g, R.drawable.h, R.drawable.i, R.drawable.j,
            R.drawable.k, R.drawable.l, R.drawable.m, R.drawable.n, R.drawable.o, R.drawable.p,
            R.drawable.q, R.drawable.r, R.drawable.s, R.drawable.t, R.drawable.u, R.drawable.v,
            R.drawable.w, R.drawable.x, R.drawable.y, R.drawable.z, R.drawable.u, R.drawable.v,
            R.drawable.aa, R.drawable.bb, R.drawable.cc, R.drawable.dd, R.drawable.ee, R.drawable.ff};

    private final List<ItemData> mDataList = new ArrayList<>();

    private GridViewPager gvpContentA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        gvpContentA = (GridViewPager) findViewById(R.id.gvp_content_a);

        for (int i = 0; i < IMG_ARR.length; i++) {
            mDataList.add(new ItemData(IMG_ARR[i], "Item " + i));
        }

        // 创建适配器
        final MyAdapter adapter = new MyAdapter(R.layout.layout_item, mDataList);

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
                gvpContentA.notifyDataSetChanged();
                return false;
            }
        });

        // 设置分割线
        adapter.setItemDecoration(new RvDivider.Builder(this).color(Color.TRANSPARENT).widthDp(10).type(RvDivider.DividerType.TYPE_WITH_START_END).build());
        // 设置适配器
        gvpContentA.setGVPAdapter(adapter);
    }

    /**
     * 自定义适配器
     */
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

    public void toast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
