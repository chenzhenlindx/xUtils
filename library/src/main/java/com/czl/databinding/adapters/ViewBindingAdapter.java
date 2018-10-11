package com.czl.databinding.adapters;

import android.annotation.SuppressLint;
import android.databinding.BindingAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

public class ViewBindingAdapter {
    private static final int WINDOW_DURATION = 2;

    /**
     * 点击事件去抖动绑定
     *
     * @param view
     * @param listener       点击监听
     * @param windowDuration 去抖延时，单位为秒
     */
    @BindingAdapter(value = {"onClick", "windowDuration"}, requireAll = false)
    public static void onClick(final View view, final View.OnClickListener listener, final Long windowDuration) {
        RxView.clicks(view)
                //两秒钟之内只取一个点击事件，防抖操作
                .throttleFirst(null == windowDuration ? WINDOW_DURATION : windowDuration, TimeUnit.SECONDS)
                .subscribe(o -> listener.onClick(view));
    }

    /**
     * 控制视图是否可见
     *
     * @param view
     * @param visible
     */
    @BindingAdapter("visible")
    public static void setVisible(final View view, boolean visible) {
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    /**
     * 视图高度控制
     *
     * @param view
     * @param height
     */
    @BindingAdapter("layout_height")
    public static void setLayoutHeight(final View view, final int height) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.height = height;
        view.setLayoutParams(params);
    }
}
