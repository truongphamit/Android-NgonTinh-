package com.android.truongpq.ngontinh;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.android.truongpq.ngontinh.adapters.NovelsAdapter;
import com.android.truongpq.ngontinh.daos.NovelDAO;
import com.android.truongpq.ngontinh.models.Novel;

import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rvNovels = (RecyclerView) findViewById(R.id.rvNovels);

        NovelsAdapter adapter = new NovelsAdapter(new NovelDAO(this).getList());
        rvNovels.setAdapter(adapter);
        StaggeredGridLayoutManager gridLayoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rvNovels.setLayoutManager(gridLayoutManager);
        rvNovels.setHasFixedSize(true);
    }
}
