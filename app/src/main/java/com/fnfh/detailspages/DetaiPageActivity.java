package com.fnfh.detailspages;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.fnfh.R;
import com.fnfh.homepage.MainActivity;

public class DetaiPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detai_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        CollapsingToolbarLayout mCollapsingToolbarLayout= (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarLayout);
        toolbar.setTitle("This is Title");
        toolbar.setNavigationIcon(R.mipmap.back);
        setSupportActionBar(toolbar);

//        //设置监听
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DetaiPageActivity.this, "setNavigationOnClickListener", Toast.LENGTH_SHORT).show();
            }
        });


        /*设置 CollapsingToolbarLayout 的变化 影响 toolbar*/


    }
    /*设置 toolbar 左侧 图标的点击事件*/
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                Toast.makeText(DetaiPageActivity.this, "onOptionsItemSelected", Toast.LENGTH_SHORT).show();
//
////               startActivity(new Intent(this, MainActivity.class));
////                finish();
//        }
//        return super.onOptionsItemSelected(item);
//    }

}
