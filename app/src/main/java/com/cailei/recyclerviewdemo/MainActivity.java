package com.cailei.recyclerviewdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.cailei.recyclerviewdemo.baseUse.BaseUseActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void baseUseClick(View view) {
        Intent intent = new Intent(MainActivity.this, BaseUseActivity.class);
        startActivity(intent);

    }
}
