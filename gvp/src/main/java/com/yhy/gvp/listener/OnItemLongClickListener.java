package com.yhy.gvp.listener;

import android.view.View;
import android.widget.AdapterView;

/**
 * author : 颜洪毅
 * e-mail : yhyzgn@gmail.com
 * time   : 2017-09-19 13:34
 * version: 1.0.0
 * desc   : 条目长按事件监听器
 */
public interface OnItemLongClickListener<T> {

    /**
     * 条目长按事件
     *
     * @param view     itmeView
     * @param position 索引
     * @param data     一条数据
     * @return 是否往下传递事件, 默认false(不传递)
     */
    boolean onItemLongClick(View view, int position, T data);
}
