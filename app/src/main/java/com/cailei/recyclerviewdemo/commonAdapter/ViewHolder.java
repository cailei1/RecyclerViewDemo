package com.cailei.recyclerviewdemo.commonAdapter;

import android.content.Context;
import android.icu.util.ValueIterator;
import android.util.SparseArray;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.HashMap;

/**
 * @author : cailei
 * @date : 2020-04-16 21:45
 * @description : 通用viewHolder
 */
public class ViewHolder extends RecyclerView.ViewHolder {

    public SparseArray<View> mViewMap = new SparseArray<>();

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    /**
     * 外部调用这个方法来获取view 但是依然会有重复调用，所以需要把view给缓存起来，从缓存中拿
     *
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = mViewMap.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            mViewMap.put(viewId, view);
        }
        return (T) view;
    }


    public ViewHolder setText(int viewId, CharSequence text) {
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }


    public ViewHolder setImageResource(int viewId, int resourceId) {
        ImageView imageView = getView(viewId);
        imageView.setImageResource(resourceId);
        return this;
    }

    public ViewHolder setItemClickListener(int viewId, View.OnClickListener onClickListener) {
        getView(viewId).setOnClickListener(onClickListener);
        return this;
    }

    //图片处理问题 路径问题 用到第三方的图片框架 imageLoader,Glide,picasso

    //第三方框架比较多，不知道用哪个
    //自己定义一套规范 HodlerImageLoader 让外面的人自己去实现使用什么框架来显示图片
    public ViewHolder setImagePath(Context context, String path, int viewId) {
        ImageView view = getView(viewId);
        Glide.with(context).load(path).into(view);
        return this;
    }


    public ViewHolder setImagePath(int viewId, String path, HodlerImageLoader imageLoader) {
        ImageView view = getView(viewId);
        imageLoader.loadImage(path, view);
        return this;
    }


    /**
     * 图片的加载
     */
    public static abstract class HodlerImageLoader {
        private String mPath;

        public HodlerImageLoader(String path) {
            this.mPath = path;
        }

        public String getmPath() {
            return mPath;
        }

        /**
         * 具体的实现图片加载的方法
         *
         * @param path
         * @param view
         */
        protected abstract void loadImage(String path, ImageView view);
    }


}
