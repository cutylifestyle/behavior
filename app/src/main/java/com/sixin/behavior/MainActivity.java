package com.sixin.behavior;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //TODO 单独设置snap为什么没用
    //TODO app:layout_scrollFlags="scroll|enterAlways"的作用
    //TODO FloatingActionButton  Snaker的使用 配合Behavior的使用
    //TODO CollapsingToolbarLayout源码分析
    //TODO 仿新浪微博
    //TODO https://github.com/Mike-bel/MDStudySamples

    private RecyclerView mRecyclerView;
    private List<String> mData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL));

        for (int i = 0; i < 100; i++) {
            mData.add("" + i);
        }
        mRecyclerView.setAdapter(new RecyclerAdapter(this,mData));
    }
}
