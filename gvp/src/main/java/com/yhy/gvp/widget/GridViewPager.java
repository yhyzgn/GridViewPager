package com.yhy.gvp.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yhy.gvp.R;
import com.yhy.gvp.adapter.GVPAdapter;

/**
 * author : 颜洪毅
 * e-mail : yhyzgn@gmail.com
 * time   : 2017-09-19 11:39
 * version: 1.0.0
 * desc   : 整个控件
 */
public class GridViewPager extends ViewPager implements GVPAdapter.OnDataChangedListener {
    // 条目总数
    private int mItemCount;
    // 每页显示条目数量
    private int mPageSize = 4;
    // 每页列数
    private int mNumColumns = 4;
    // 总页数
    private int mPageCount;
    // ViewPager的适配器对象
    private PageAdapter mPageAdapter;
    // 适配器
    private GVPAdapter mAdapter;

    public GridViewPager(Context context) {
        this(context, null);
    }

    public GridViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 初始化
        init(context, attrs);
    }

    /**
     * 初始化
     *
     * @param context 上下文对象
     * @param attrs   属性集
     */
    private void init(Context context, AttributeSet attrs) {
        // 如果attrs不为空，就从xml布局文件中获取自定义attrs参数
        if (null != attrs) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.GridViewPager);
            mPageSize = ta.getInteger(R.styleable.GridViewPager_page_size, 4);
            mNumColumns = ta.getInteger(R.styleable.GridViewPager_num_columns, 4);
            ta.recycle();
        }
    }

    /**
     * 设置适配器
     *
     * @param adapter 适配器
     */
    public void setGVPAdapter(GVPAdapter adapter) {
        if (null == adapter) {
            throw new IllegalArgumentException("适配器不能为空");
        }
        mAdapter = adapter;

        // 从适配器中获取条目总数
        mItemCount = adapter.getCount();

        if (mItemCount <= 0) {
            throw new RuntimeException("条目总数必须大于0");
        }

        // 给ViewPager设置适配器
        mPageAdapter = new PageAdapter();
        setAdapter(mPageAdapter);

        // 绑定
        mAdapter.bindGridViewPager(this);
    }

    /**
     * 刷新适配器数据
     * <p>
     * {@link GVPAdapter.OnDataChangedListener#onDataChanged()}
     */
    @Deprecated
    public void notifyDataSetChanged() {
        onDataChanged();
    }

    /**
     * 设置每页大小
     *
     * @param pageSize 每页大小
     */
    public void setPageSize(int pageSize) {
        this.mPageSize = pageSize;
    }

    /**
     * 获取每页大小
     *
     * @return 每页大小
     */
    public int getPageSize() {
        return mPageSize;
    }

    /**
     * 设置每页列数
     *
     * @param numColumns 每页列数
     */
    public void setNumColumns(int numColumns) {
        this.mNumColumns = numColumns;
    }

    /**
     * 获取每页列数
     *
     * @return 每页列数
     */
    public int getNumColumns() {
        return mNumColumns;
    }

    /**
     * 获取总页数
     *
     * @return 总页数
     */
    public int getPageCount() {
        return mPageCount;
    }

    @Override
    public void onDataChanged() {
        if (null != mPageAdapter) {
            mPageAdapter.notifyDataSetChanged();
        }
    }

    /**
     * ViewPager的适配器
     */
    private class PageAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            // 计算ViewPager的页数
            mPageCount = mItemCount % mPageSize == 0 ? mItemCount / mPageSize : mItemCount / mPageSize + 1;

            // 直接返回页面数量
            return mPageCount;
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return view == o;
        }

        @Override
        public View instantiateItem(ViewGroup container, final int position) {
            View view = LayoutInflater.from(container.getContext()).inflate(R.layout.widget_recycler_view_pager, null);
            RecyclerView rvPager = view.findViewById(R.id.rv_rvg_pager);
            // 设置布局为GridView布局
            rvPager.setLayoutManager(new GridLayoutManager(container.getContext(), mNumColumns));

            // 设置每个RecyclerView的适配器
            setGVAdapterOrRefresh(rvPager, position);

            // 显示该页面
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getItemPosition(Object object) {
            // 无论如何都刷新
            return POSITION_NONE;
        }

        /**
         * 给RecyclerView设置适配器或者刷新数据
         *
         * @param rv       要设置适配器的RecyclerView
         * @param position 页码
         */
        private void setGVAdapterOrRefresh(RecyclerView rv, final int position) {
            RecyclerView.Adapter adapter = rv.getAdapter();
            // 如果已经设置过适配器，直接刷新即可
            if (null != adapter) {
                adapter.notifyDataSetChanged();
            } else {
                // 否则设置新的适配器
                adapter = new GVAdapter(mAdapter, position);
                rv.setAdapter(adapter);
                // 设置item间分割线
                RecyclerView.ItemDecoration itemDecoration = ((GVAdapter) adapter).getItemDecoration();
                if (null != itemDecoration) {
                    rv.addItemDecoration(itemDecoration);
                }
            }
        }
    }

    /**
     * 每个RecyclerView的适配器
     */
    private class GVAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private GVPAdapter<T> mAdapter;
        private int mIndex;

        /**
         * 每个适配器构造函数
         *
         * @param adapter 具体的自定义适配器
         * @param index   页码
         */
        GVAdapter(GVPAdapter<T> adapter, int index) {
            this.mAdapter = adapter;
            this.mIndex = index;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(mAdapter.getLayoutId(), null);
            return new Holder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            // 计算item索引
            int index = mIndex * mPageSize + position;
            // 设置索引值到tag
            holder.itemView.setTag(index);
            // 绑定item, 使用索引值
            mAdapter.bindItem(holder.itemView, index, mAdapter.getData(index));
        }

        @Override
        public int getItemCount() {
            //计算每个RecyclerView中条目数量
            int count = mItemCount;
            if (mIndex < mPageCount - 1) {
                //不是最后一页，按config中pageSize显示
                count = mPageSize;
            } else {
                //用总条目数量-已经显示的条目数量
                //已经显示的条目数量=每页显示条目数量*已经显示的页数
                count -= mPageSize * (mPageCount - 1);
            }
            return count;
        }

        /**
         * 从自定义适配器中获取item分割线
         *
         * @return item分割线
         */
        public RecyclerView.ItemDecoration getItemDecoration() {
            return mAdapter.getItemDecoration();
        }

        /**
         * ViewHolder
         */
        private class Holder extends RecyclerView.ViewHolder {

            public Holder(View itemView) {
                super(itemView);
            }
        }
    }
}
