package com.yhy.gvp.listener;

import android.view.View;
import android.widget.AdapterView;

/**
 * 条目点击事件监听器
 * Created by Administrator on 2016/10/17.
 */
public interface OnItemClickListener {
    void onItemClick(AdapterView<?> parent, View view, int position);
}
