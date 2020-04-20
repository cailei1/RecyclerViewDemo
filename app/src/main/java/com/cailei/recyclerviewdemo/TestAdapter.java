package com.cailei.recyclerviewdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cailei.recyclerviewdemo.commonAdapter.RecyclerCommonAdapter;
import com.cailei.recyclerviewdemo.commonAdapter.ViewHolder;

import java.util.List;

/**
 * @author : cailei
 * @date : 2020-03-28 19:17
 * @description :
 */
public class TestAdapter extends RecyclerCommonAdapter<String> {


    public TestAdapter(List<String> data, Context context) {
        super(context, data, R.layout.item_use);
    }


    @Override
    protected void convert(ViewHolder holder, String data, int position) {
        TextView textView = holder.getView(R.id.text);
    }
}
