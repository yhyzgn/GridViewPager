package com.yhy.gvp.global;

/**
 * Created by Administrator on 2016/10/16.
 */

public class Config {
    private static Config config;
    private int itemCount;
    private int pageSize;
    private int numColumns;
    private int verticalSpacing;
    private int horizontalSpacing;
    private boolean scrollBarEnable;
    private int[] padding;//第一个元素是上下边距，第二个元素是左右边距

    private Config() {
    }

    public static synchronized Config getInstance() {
        if (null == config) {
            config = new Config();
        }
        return config;
    }

    public Config setItemCount(int itemCount) {
        this.itemCount = itemCount;
        return this;
    }

    public int getItemCount() {
        return itemCount;
    }

    public Config setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public int getPageSize() {
        return pageSize;
    }

    public Config setNumColumns(int numColumns) {
        if (pageSize > 0 && pageSize < numColumns) {
            //当每页条目数量比GridView列数多时，就以每页条目数量为准
            numColumns = pageSize;
        }
        this.numColumns = numColumns;
        return this;
    }

    public int getNumColumns() {
        return numColumns;
    }

    public Config setVerticalSpacing(int verticalSpacingDp) {
        this.verticalSpacing = verticalSpacingDp;
        return this;
    }

    public int getVerticalSpacing() {
        return verticalSpacing;
    }

    public Config setHorizontalSpacing(int horizontalSpacingDp) {
        this.horizontalSpacing = horizontalSpacingDp;
        return this;
    }

    public int getHorizontalSpacing() {
        return horizontalSpacing;
    }

    /**
     * 设置边距
     *
     * @param padding 边距数组（第一个元素是上下边距，第二个元素是左右边距）
     * @return
     */
    public Config setPadding(int[] padding) {
        this.padding = padding;
        return this;
    }

    public int[] getPadding() {
        return padding;
    }

    public Config setScrollBarEnable(boolean scrollBarEnable) {
        this.scrollBarEnable = scrollBarEnable;
        return this;
    }

    public boolean isScrollBarEnable() {
        return scrollBarEnable;
    }
}
