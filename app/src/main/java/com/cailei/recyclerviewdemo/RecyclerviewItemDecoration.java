package com.cailei.recyclerviewdemo;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author : cailei
 * @date : 2020-04-12 21:01
 * @description : 添加分割线
 */
public class RecyclerviewItemDecoration extends RecyclerView.ItemDecoration {


    private Paint mPaint;

    public RecyclerviewItemDecoration(){
        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);

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
        //代表每个底部留出10px 来绘制分割线 最后一个位置不需要分割线
//        outRect.bottom = 10;
        int position = parent.getChildAdapterPosition(view);
        Log.e("sss", position+parent.getChildCount() + "");
        //通过打印发现parent.getChildCount 返回的数是变化的 所以没办法拿到最后一条 方法行不通
//        if (position != parent.getChildCount() - 1) {
//            outRect.bottom = 10;
//        }
        if(position!=0){
            outRect.top=10;
        }
    }

    /**
     * 绘制分割线
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        //在每个item的头部绘制
        int childCount=parent.getChildCount();
        Rect rect=new Rect();
        rect.left=parent.getPaddingLeft();
        rect.right=parent.getWidth()-parent.getPaddingRight();
        for(int i=1;i<childCount;i++){
            //第一个分割线的底部正好是第一个view的头部
            rect.bottom=parent.getChildAt(i).getTop();
            rect.top=parent.getChildAt(i).getTop()-10;
            c.drawRect(rect,mPaint);
        }

    }
}
