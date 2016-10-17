package com.yhy.gvp.adapter;

import android.view.View;
import android.view.ViewGroup;

/**
 * GridViewPager的适配器，对外用户接口
 * Created by Administrator on 2016/10/17.
 */
public interface GVPAdapter {
    int getCount();

    Object getItem(int position);

    long getItemId(int position);

    View getView(int position, View convertView, ViewGroup parent);
}