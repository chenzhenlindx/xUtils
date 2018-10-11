package com.czl.databinding.adapters;

import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.signature.MediaStoreSignature;
import com.czl.library.utils.Base64Bitmap;

import java.io.File;

public class ImageViewBindingAdapter {
    /**
     * 将Base64转成bitmap，并设置给ImageView
     *
     * @param view
     * @param base64Code
     */
    @BindingAdapter("base64Code")
    public static void setBase64Code(final ImageView view, final String base64Code) {
        if (!TextUtils.isEmpty(base64Code)) {
            Bitmap b = Base64Bitmap.base64toBitmap(base64Code);
            if (null != b) {
                view.setImageBitmap(b);
            }
        }
    }

    /**
     * 将Base64格式图片，圆形显示
     *
     * @param view
     * @param base64Code base64格式图片
     */
    @BindingAdapter("base64CodeCircle")
    public static void setBase64CodeCircle(final ImageView view, final String base64Code) {
        if (!TextUtils.isEmpty(base64Code)) {
            Bitmap b = Base64Bitmap.base64toBitmap(base64Code);
            if (null != b) {
                view.setImageBitmap(b);
                Glide.with(view)
                        .load(b)
                        .apply(RequestOptions.circleCropTransform())
                        .into(view);
            }
        }
    }

    /**
     * 显示图片内容
     *
     * @param view
     * @param imageUrl      网络地址
     * @param baseUrl       网络BaseUrl
     * @param filePath      文件地址
     * @param resId         资源Id
     * @param errorDrawable
     */
    @BindingAdapter(value = {"imageUrl", "baseUrl", "filePath", "resId", "error"}, requireAll = false)
    public static void setImage(final ImageView view, String imageUrl, final String baseUrl,
                                final String filePath, final Integer resId, final Drawable errorDrawable) {
        RequestOptions options = RequestOptions.centerCropTransform();
        if (null != errorDrawable) {
            options.error(errorDrawable);
        }
        if (!TextUtils.isEmpty(imageUrl)) {
            if (!imageUrl.startsWith("http") && !TextUtils.isEmpty(baseUrl)) {
                imageUrl = baseUrl + imageUrl;
            }
            Glide.with(view.getContext())
                    .load(imageUrl)
                    .apply(options)
                    .into(view);
        } else if (!TextUtils.isEmpty(filePath)) {
            //增加签名
            options.signature(new MediaStoreSignature("image/jpeg", System.currentTimeMillis(), 0));
            Glide.with(view.getContext())
                    .load(new File(filePath))
                    .apply(options)
                    .into(view);
        } else if (null != resId) {
            view.setImageResource(resId);
        }
    }
}
