package com.cailei.recyclerviewdemo.commonAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * @author : cailei
 * @date : 2020-04-16 21:44
 * @description :通用adapter
 */
public abstract class RecyclerCommonAdapter<T> extends RecyclerView.Adapter<ViewHolder> {

    private List<T> mDatas;

    private int layoutId;

    private Context mContext;

    private LayoutInflater mInflater;

    public RecyclerCommonAdapter(Context context, List<T> datas, int layoutId) {
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
        this.mDatas = datas;
        this.layoutId = layoutId;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(layoutId, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        convert(holder, mDatas.get(position), position);
    }

    protected abstract void convert(ViewHolder holder, T data, int position);

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}
