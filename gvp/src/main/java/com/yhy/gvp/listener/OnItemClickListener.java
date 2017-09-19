package com.yhy.gvp.listener;

import android.view.View;
import android.widget.AdapterView;

/**
 * author : 颜洪毅
 * e-mail : yhyzgn@gmail.com
 * time   : 2017-09-19 13:34
 * version: 1.0.0
 * desc   : 条目点击事件监听器
 */
public interface OnItemClickListener<T> {

    /**
     * 条目点击事件
     *
     * @param view     itmeView
     * @param position 索引
     * @param data     一条数据
     */
    void onItemClick(View view, int position, T data);
}
