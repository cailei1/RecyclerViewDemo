package com.cailei.recyclerviewdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author : cailei
 * @date : 2020-04-13 09:13
 * @description :ListView 样式的分割线
 */
public class GridLayoutItemDecoration extends RecyclerView.ItemDecoration {
    private Drawable divider;
    private int mPos;

    public GridLayoutItemDecoration(Context context, int drawResId, int pos) {
        divider = ContextCompat.getDrawable(context, drawResId);
        this.mPos = pos;
    }


    /**
     * 添加10px 分割线的红色
     * 基本操作就是留出分割线位置
     *
     * @param outRect
     * @param view
     * @param parent
     * @param state
     */
    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
//        //留出下边和右边的位置
//        int position = parent.getChildAdapterPosition(view);
//
//        if ((position + 1) % mPos != 0) {
//            outRect.right = divider.getIntrinsicHeight();
//        }
//        Log.e("sss", parent.getChildCount() + "");
//        //每一排的最后一个都不用留出右边的位置 最后一排的都不用留出bottom的位置
//        int allColumn = parent.getAdapter().getItemCount();
//        int lastPos;
//        if(allColumn%mPos==0){
//            lastPos=allColumn/mPos-1;
//        }else{
//            lastPos=allColumn/mPos;
//        }
//        Log.e("sss","positon"+"->"+position+"   "+"lastpos"+"->"+lastPos);
//        if(position<=(lastPos)*mPos){
//            outRect.bottom = divider.getIntrinsicHeight();
//
//        }
////        if (parent.getChildCount() / mPos != allColumn) {
//        //如何保证最后一排不留横线
////        }

        //最后一列不用留最后边的   最后一行都不用留
        int right;
        int bottom;
        right = divider.getIntrinsicWidth();
        bottom = divider.getIntrinsicHeight();
        if (isLastRaw(view, parent)) {
            bottom = 0;
        }
        if (isLastColumn(view, parent)) {
            right = 0;
        }
        outRect.right = right;
        outRect.bottom = bottom;
    }

    private boolean isLastRaw(View view, RecyclerView recyclerView) {
        int currentPosition = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();

        int lastRaw;
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            int spanCount = gridLayoutManager.getSpanCount();
            if (recyclerView.getAdapter().getItemCount() % spanCount == 0) {
                lastRaw = recyclerView.getAdapter().getItemCount() / spanCount - 1;
            } else {
                lastRaw = recyclerView.getAdapter().getItemCount() / spanCount;
            }
            Log.e("sss","lastRaw"+lastRaw+recyclerView.getAdapter().getItemCount());
            return (currentPosition + 1) > lastRaw * spanCount;
        }

        return false;
    }

    private boolean isLastColumn(View view, RecyclerView recyclerView) {
        int currentPosition = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();


        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            int spanCount = gridLayoutManager.getSpanCount();
            Log.e("sss", "currentPosition" + currentPosition + "   " + "spanCount" + spanCount);
            return (currentPosition + 1) % spanCount == 0;
        }

        return false;
    }

    /**
     * 绘制分割线
     *
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        drawVertical(c, parent);
        drawHorizontal(c, parent);
    }

    private void drawHorizontal(Canvas c, RecyclerView parent) {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) childView.getLayoutParams();
            int left = childView.getRight() + layoutParams.rightMargin;
            int top = childView.getTop() - layoutParams.topMargin;
            int right = childView.getRight() + divider.getIntrinsicWidth();
            int bottom = childView.getBottom() + divider.getIntrinsicHeight();
            divider.setBounds(left, top, right, bottom);
            divider.draw(c);
        }
    }

    private void drawVertical(@NonNull Canvas c, @NonNull RecyclerView parent) {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = parent.getChildAt(i);
            int left = childView.getLeft();
            int right = childView.getRight();
            int top = childView.getBottom();
            int bottom = childView.getBottom() + divider.getIntrinsicHeight();
            divider.setBounds(left, top, right, bottom);
            divider.draw(c);
        }
    }

}
