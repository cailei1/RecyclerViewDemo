package com.cailei.recyclerviewdemo.baseUse;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cailei.recyclerviewdemo.GridLayoutItemDecoration;
import com.cailei.recyclerviewdemo.LinerLayoutItemDecoration;
import com.cailei.recyclerviewdemo.R;
import com.cailei.recyclerviewdemo.RecyclerviewItemDecoration;
import com.cailei.recyclerviewdemo.TestAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveAction;

/**
 * @author : cailei
 * @date : 2020-03-28 16:18
 * @description :
 */
public class BaseUseActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TestAdapter testAdapter;
    private List<String> mDatas;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_use);

        initView();
        initData();
    }

    private void initData() {
        mDatas = new ArrayList<>();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("" + (char) i);
        }

        testAdapter = new TestAdapter(mDatas, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(testAdapter);
//        recyclerView.addItemDecoration(new LinerLayoutItemDecoration(this, R.drawable.item_divider));
    }

    private void initView() {
        recyclerView = findViewById(R.id.recycler_view);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.id_action_gridView:
                recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
                recyclerView.addItemDecoration(new GridLayoutItemDecoration(this, R.drawable.item_divider, 3));
                break;
            case R.id.id_action_listView:
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //感觉anddroidstudio 这里有点卡不知道为啥
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //加载布局
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }
}