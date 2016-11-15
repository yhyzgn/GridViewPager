package com.yhy.gvp.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.yhy.gvp.R;
import com.yhy.gvp.global.Config;
import com.yhy.gvp.listener.OnItemClickListener;
import com.yhy.gvp.listener.OnItemLongClickListener;
import com.yhy.gvp.utils.DensityUtils;
import com.yhy.gvp.utils.ToastUtils;

/**
 * Created by Administrator on 2016/10/16.
 */

public class GridViewPager extends ViewPager {
    private static final String TAG = "GridViewPager";
    //GridView布局参数
    private AbsListView.LayoutParams mParams;
    //总页数
    private int mPageCount;
    //ViewPager的适配器对象
    private PageAdapter mPageAdapter;
    //点击事件
    private OnItemClickListener mItemClickListener;
    //长按事件
    private OnItemLongClickListener mItemLongClickListener;

    public GridViewPager(Context context) {
        this(context, null);
    }

    public GridViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    /**
     * 初始化
     */
    private void init(Context context, AttributeSet attrs) {
        mParams = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, AbsListView
                .LayoutParams.MATCH_PARENT);
        //给Config设置默认参数，避免其他地方调用时发生错误
        Config.getInstance().setPageSize(4).setNumColumns(4).setVerticalSpacing
                (2).setHorizontalSpacing(2);
        //如果attrs不为空，就从xml布局文件中获取自定义attrs参数
        if (null != attrs) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.GridViewPager);
            int pageSize = ta.getInteger(R.styleable.GridViewPager_page_size, 4);
            int numColumns = ta.getInteger(R.styleable.GridViewPager_num_columns, 4);
            int verticalSpacing = ta.getDimensionPixelSize(R.styleable
                    .GridViewPager_vertical_spacing, 2);
            int horizontalSpacing = ta.getDimensionPixelSize(R.styleable
                    .GridViewPager_horizontal_spacing, 2);
            boolean scrollBarEnable = ta.getBoolean(R.styleable.GridViewPager_scroll_bar_enable,
                    true);
            int paddingTopBottom = ta.getDimensionPixelSize(R.styleable
                    .GridViewPager_padding_top_bottom, 0);
            int paddingLeftRight = ta.getDimensionPixelSize(R.styleable
                    .GridViewPager_padding_left_right, 0);
            ta.recycle();
            //在Config中设置相应参数
            //由于getDimensionPixelSize返回的是px，而后面设置时统一按dp设置，所以这里需要将px转换为dp
            Config.getInstance().setPageSize(pageSize).setNumColumns(numColumns)
                    .setVerticalSpacing((int) DensityUtils.px2dp(context, verticalSpacing))
                    .setHorizontalSpacing((int) DensityUtils.px2dp(context, horizontalSpacing))
                    .setScrollBarEnable(scrollBarEnable)
                    .setPadding(new int[]{(int) DensityUtils.px2dp(context, paddingTopBottom),
                            (int) DensityUtils.px2dp(context, paddingLeftRight)});
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
        //如果Config中的总条目数与GVPAdapter中的getCount返回结果不相等，就以getCount返回的结果为准
        if (Config.getInstance().getItemCount() != adapter.getCount()) {
            Config.getInstance().setItemCount(adapter.getCount());
        }
        if (Config.getInstance().getItemCount() <= 0) {
            ToastUtils.toastShort(getContext(), R.string.item_count_error);
            return;
        }

        //给ViewPager设置适配器
        mPageAdapter = new PageAdapter(adapter);
        setAdapter(mPageAdapter);
    }

    /**
     * 设置条目点击事件
     *
     * @param listener 条目点击事件监听器
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }

    /**
     * 设置条目长按事件监听器
     *
     * @param listener 条目长按事件监听器
     */
    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        this.mItemLongClickListener = listener;
    }

    /**
     * 刷新适配器数据
     */
    public void notifyDataSetChanged() {
        if (null != mPageAdapter) {
            mPageAdapter.notifyDataSetChanged();
        }
    }

    //获取总页数
    public int getPageCount() {
        return mPageCount;
    }

    /**
     * ViewPager的适配器
     */
    private class PageAdapter extends PagerAdapter {
        private GVPAdapter mAdapter;

        public PageAdapter(GVPAdapter adapter) {
            this.mAdapter = adapter;
        }

        @Override
        public int getCount() {
            int itemCount = Config.getInstance().getItemCount();
            int pageSize = Config.getInstance().getPageSize();
            //计算ViewPager的页数
            mPageCount = itemCount % pageSize == 0 ? itemCount / pageSize : itemCount /
                    pageSize + 1;
            Log.d(TAG, "共" + mPageCount + "页");
            return mPageCount;
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return view == o;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            GridView gv = new GridView(getContext());
            //设置布局参数
            gv.setLayoutParams(mParams);
            //如果禁用了ScrollBar，就设置禁用，否则就保持默认
            if (!Config.getInstance().isScrollBarEnable()) {
                gv.setVerticalScrollBarEnabled(Config.getInstance().isScrollBarEnable());
            }
            //设置每行条目数量
            gv.setNumColumns(Config.getInstance().getNumColumns());
            //设置每个GridView内边距
            int[] padding = Config.getInstance().getPadding();
            if (null != padding) {
                gv.setPadding((int) DensityUtils.dp2px(getContext(), padding[1]),
                        (int) DensityUtils.dp2px(getContext(), padding[0]),
                        (int) DensityUtils.dp2px(getContext(), padding[1]),
                        (int) DensityUtils.dp2px(getContext(), padding[0]));
            }
            //设置垂直间距
            gv.setVerticalSpacing((int) DensityUtils.dp2px(getContext(), Config.getInstance()
                    .getVerticalSpacing()));
            //设置水平间距(设置padding后该值可能无效)
            gv.setHorizontalSpacing((int) DensityUtils.dp2px(getContext(), Config.getInstance()
                    .getHorizontalSpacing()));
            //将GrindView添加到container中
            container.addView(gv);
            //设置Tag，在getItemPosition中使用
            gv.setTag(position);
            //设置每个GridView的适配器
            setGVAdapterOrRefresh(gv, position);

            gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int index, long id) {
                    if (null != mItemClickListener) {
                        index += Config.getInstance().getPageSize() * position;
                        mItemClickListener.onItemClick(parent, view, index);
                    }
                }
            });

            gv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int index,
                                               long id) {
                    if (null != mItemLongClickListener) {
                        index += Config.getInstance().getPageSize() * position;
                        return mItemLongClickListener.onItemLongClick(parent, view, index);
                    }
                    return false;
                }
            });
            //返回相应的GridView
            return gv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getItemPosition(Object object) {
//            if (object instanceof GridView) {
//                int position = (int) ((GridView) object).getTag();
//                if (getCurrentItem() == position) {
//                    //是当前页，刷新
//                    return POSITION_NONE;
//                }
//                //否则不刷新
//                return POSITION_UNCHANGED;
//            }
//            return super.getItemPosition(object);
            //无论如何都刷新
            return POSITION_NONE;
        }

        /**
         * 给GridView设置适配器或者刷新数据
         *
         * @param gv       要设置适配器的GridView
         * @param position 第position个GridView
         */
        private void setGVAdapterOrRefresh(GridView gv, final int position) {
            if (gv.getAdapter() instanceof BaseAdapter) {
                BaseAdapter adapter = (BaseAdapter) gv.getAdapter();
                //如果已经设置过适配器，直接刷新即可
                if (null != adapter) {
                    adapter.notifyDataSetChanged();
                    return;
                }
            }
            //否则设置新的适配器
            gv.setAdapter(new GVAdapter(mAdapter, position));
        }
    }

    /**
     * 每个GridView的适配器
     */
    private class GVAdapter extends BaseAdapter {
        private GVPAdapter mAdapter;
        private int mIndex;

        public GVAdapter(GVPAdapter adapter, int gvIndex) {
            this.mAdapter = adapter;
            this.mIndex = gvIndex;
        }

        @Override
        public int getCount() {
            //计算每个GridView中条目数量
            int count = Config.getInstance().getItemCount();
            if (mIndex < mPageCount - 1) {
                //不是最后一页，按config中pageSize显示
                count = Config.getInstance().getPageSize();
            } else {
                //用总条目数量-已经显示的条目数量
                //已经显示的条目数量=每页显示条目数量*已经显示的页数
                count -= Config.getInstance().getPageSize() * (mPageCount - 1);
            }
            Log.d(TAG, "第" + mIndex + "页有" + count + "条数据");
            return count;
        }

        @Override
        public Object getItem(int index) {
            return mAdapter.getItem(mIndex * Config.getInstance().getPageSize() + index);
        }

        @Override
        public long getItemId(int index) {
            return mAdapter.getItemId(mIndex * Config.getInstance().getPageSize() +
                    index);
        }

        @Override
        public View getView(int index, View convertView, ViewGroup parent) {
            return mAdapter.getView(mIndex * Config.getInstance().getPageSize() +
                    index, convertView, parent);

        }
    }

    /**
     * GridViewPager的适配器，对外用户接口
     */
    public interface GVPAdapter {
        int getCount();

        Object getItem(int position);

        long getItemId(int position);

        View getView(int position, View convertView, ViewGroup parent);
    }
}
