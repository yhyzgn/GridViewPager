package com.yhy.gvp.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

public class TotalDisplayGridView extends GridView {
    public TotalDisplayGridView(Context context) {
        super(context);
    }

    public TotalDisplayGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TotalDisplayGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
//        super.onMeasure(widthMeasureSpec, expandSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
