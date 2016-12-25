package com.yhy.gvp.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/10/16.
 */
public class ToastUtils {
    private ToastUtils() {
    }

    public static void toastShort(Context ctx, String text) {
        Toast.makeText(ctx.getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }

    public static void toastLong(Context ctx, String text) {
        Toast.makeText(ctx.getApplicationContext(), text, Toast.LENGTH_LONG).show();
    }

    public static void toastShort(Context ctx, int resId) {
        Toast.makeText(ctx.getApplicationContext(), ctx.getResources().getString(resId), Toast
                .LENGTH_SHORT).show();
    }

    public static void toastLong(Context ctx, int resId) {
        Toast.makeText(ctx.getApplicationContext(), ctx.getResources().getString(resId), Toast
                .LENGTH_LONG).show();
    }
}
