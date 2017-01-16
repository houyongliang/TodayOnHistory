package com.fnfh;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoView;

public class DetailGrilActivity extends AppCompatActivity {
    private static final String KEY = "com.fnfh.DetailGrilActivity";

    @BindView(R.id.gril_toolBar)
    Toolbar grilToolBar;
    @BindView(R.id.detai_gril_iv)
    PhotoView detaiGrilIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_gril);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String extra = intent.getStringExtra(KEY);
        Glide.with(this).load(extra).into(detaiGrilIv);



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {


            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            // 激活状态栏
            tintManager.setStatusBarTintEnabled(true);
            // enable navigation bar tint 激活导航栏
//            tintManager.setNavigationBarTintEnabled(true);
            //设置系统栏设置颜色
            //tintManager.setTintColor(R.color.red);
            //给状态栏设置颜色
            tintManager.setStatusBarTintResource(R.color.colorAccent); /*设置上面的按钮*/

        }


//        grilToolBar.setNavigationIcon(R.mipmap.back);
        grilToolBar.setTitle("妹子");
        grilToolBar.setTitleTextColor(Color.RED);
        setSupportActionBar(grilToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        grilToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DetailGrilActivity.this, "setNavigationOnClickListener+：toolvar 监听", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        grilToolBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int menuItemId = item.getItemId();
                if (menuItemId == R.id.action_down) {
                    Toast.makeText(DetailGrilActivity.this, "下载软件", Toast.LENGTH_SHORT).show();

                }
                return true;
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.toolbar_menu, menu);//加载menu文件到布局

        return true;
    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                Toast.makeText(DetailGrilActivity.this, "setNavigationOnClickListener+：toolvar 监听", Toast.LENGTH_SHORT).show();
//                finish();
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    public static Intent getIntent(Context context, String value) {
        Intent intent = new Intent(context, DetailGrilActivity.class);
        intent.putExtra(KEY, value);
        return intent;
    }

}
