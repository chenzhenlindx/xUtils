package com.czl.databinding.adapters;

import android.databinding.BindingAdapter;
import android.text.TextUtils;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TextViewBindingAdapter {
    private static final String DEFAULT_FORMAT = "yyyy-MM-dd";

    /**
     * @param view
     * @param time   Long型的时间
     * @param format 格式化的格式
     */
    @BindingAdapter(value = {"time", "format"}, requireAll = false)
    public static void setTimeFormat(final TextView view, final Long time, String format) {
        if (null == time) {
            return;
        }
        if (TextUtils.isEmpty(format)) {
            format = DEFAULT_FORMAT;
        }
        DateFormat dateFormat = new SimpleDateFormat(format, Locale.getDefault());
        String t = dateFormat.format(new Date(time));
        view.setText(t);
    }
}
