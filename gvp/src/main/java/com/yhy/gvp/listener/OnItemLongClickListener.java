package com.yhy.gvp.listener;

import android.view.View;
import android.widget.AdapterView;

/**
 * 条目长按事件监听器
 * Created by Administrator on 2016/10/17.
 */
public interface OnItemLongClickListener {
    boolean onItemLongClick(AdapterView<?> parent, View view, int position);
}
