package com.yhy.gvp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yhy.gvp.listener.OnItemClickListener;
import com.yhy.gvp.listener.OnItemLongClickListener;

import java.util.List;

/**
 * author : 颜洪毅
 * e-mail : yhyzgn@gmail.com
 * time   : 2017-09-19 11:39
 * version: 1.0.0
 * desc   : GridViewPager的适配器，对外用户接口
 *
 * @param <T> 每个item数据类型
 */
public abstract class GVPAdapter<T> {

    // 布局文件资源id
    private int mLayoutId;
    // 数据集合
    private List<T> mDataList;
    // 分割线
    private RecyclerView.ItemDecoration mItemDecoration;
    // item点击事件
    private OnItemClickListener<T> mOnItemClickListener;
    // item长按事件
    private OnItemLongClickListener<T> mOnItemLongClickListener;

    public GVPAdapter(int layoutId, List<T> dataList) {
        this.mLayoutId = layoutId;
        this.mDataList = dataList;
    }

    /**
     * 获取布局资源id
     *
     * @return 布局资源id
     */
    public final int getLayoutId() {
        return mLayoutId;
    }

    /**
     * 获取item数量
     *
     * @return item数量
     */
    public int getCount() {
        return null == mDataList ? 0 : mDataList.size();
    }

    /**
     * 获取每一项数据
     *
     * @param position 索引
     * @return 每一项数据
     */
    public final T getData(int position) {
        return mDataList.get(position);
    }

    /**
     * 绑定item
     *
     * @param item     itemView
     * @param position 索引
     * @param data     一条数据
     */
    public final void bindItem(final View item, final int position, final T data) {
        // 设置item点击事件
        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != mOnItemClickListener) {
                    mOnItemClickListener.onItemClick(item, position, data);
                }
            }
        });

        // 设置item长按事件
        item.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (null != mOnItemLongClickListener) {
                    return mOnItemLongClickListener.onItemLongClick(item, position, data);
                }
                return false;
            }
        });

        // 调用抽象方法执行子类的item绑定逻辑
        bind(item, position, data);
    }

    /**
     * 绑定每个item的具体方法, 子类必须实现
     *
     * @param item     itemView
     * @param position 索引
     * @param data     一条数据
     */
    public abstract void bind(View item, int position, T data);

    /**
     * 设置分割线
     *
     * @param itemDecoration 分割线
     */
    public void setItemDecoration(RecyclerView.ItemDecoration itemDecoration) {
        mItemDecoration = itemDecoration;
    }

    /**
     * 获取分割线
     *
     * @return 分割线
     */
    public RecyclerView.ItemDecoration getItemDecoration() {
        return mItemDecoration;
    }

    /**
     * 设置item点击事件
     *
     * @param listener item点击事件
     */
    public void setOnItemClickListener(OnItemClickListener<T> listener) {
        mOnItemClickListener = listener;
    }

    /**
     * 设置item长按事件
     *
     * @param listener item长按事件
     */
    public void setOnItemLongClickListener(OnItemLongClickListener<T> listener) {
        mOnItemLongClickListener = listener;
    }
}