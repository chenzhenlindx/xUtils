package com.czl.databinding.adapters;

import android.widget.AdapterView;

import com.jakewharton.rxbinding2.widget.RxAdapterView;

import java.util.concurrent.TimeUnit;

import androidx.databinding.BindingAdapter;
import io.reactivex.functions.Consumer;

public class AdapterViewBindingAdapter {
    @BindingAdapter(value = {"onItemClick", "windowDuration"}, requireAll = false)
    public static void setSelectedItemPosition(AdapterView view, AdapterView.OnItemClickListener listener, Integer windowDuration) {
        RxAdapterView.itemClicks(view)
                //两秒钟之内只取一个点击事件，防抖操作
                .throttleFirst(null == windowDuration ? ViewBindingAdapter.WINDOW_DURATION : windowDuration, TimeUnit.SECONDS)
                .subscribe((Consumer<Integer>) integer -> listener.onItemClick(view, null, integer, 0L));
    }
}
