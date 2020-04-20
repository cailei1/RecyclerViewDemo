package com.cailei.recyclerviewdemo.commonAdapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * @author : cailei
 * @date : 2020-04-19 22:04
 * @description :具体的imageLoader实现方法 可以是glide imageLoader piascdfasdf
 */
public class GlideImageHolder extends ViewHolder.HodlerImageLoader {

    public GlideImageHolder(String path) {
        super(path);
    }

    @Override
    protected void loadImage(String path, ImageView view) {
        Glide.with(view.getContext()).load(getmPath()).into(view);
    }
}
