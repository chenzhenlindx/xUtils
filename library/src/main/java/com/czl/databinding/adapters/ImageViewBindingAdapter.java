package com.czl.databinding.adapters;

import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
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
    @BindingAdapter(value = {"base64Code", "error"}, requireAll = false)
    public static void setBase64Code(final ImageView view, final String base64Code, final Drawable errorDrawable) {
        if (!TextUtils.isEmpty(base64Code)) {
            Bitmap b = Base64Bitmap.base64toBitmap(base64Code);
            if (null != b) {
                view.setImageBitmap(b);
            }
        }
        if (null != errorDrawable) {
            view.setImageDrawable(errorDrawable);
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
    @BindingAdapter(value = {"imageUrl", "baseUrl", "filePath", "resId", "placeholder", "error"}, requireAll = false)
    public static void setImage(final ImageView view, String imageUrl, final String baseUrl,
                                final String filePath, final Integer resId,
                                final Drawable placeholderDrawable, final Drawable errorDrawable) {
        RequestOptions options = new RequestOptions();
        if (null != placeholderDrawable) {
            options.placeholder(placeholderDrawable);
        }
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
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            view.setImageDrawable(errorDrawable);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            return false;
                        }
                    })
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
