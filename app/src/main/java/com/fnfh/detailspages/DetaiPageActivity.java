package com.fnfh.detailspages;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.fnfh.Bean.DetaiBean;
import com.fnfh.Bean.Details;
import com.fnfh.DetailGrilActivity;
import com.fnfh.R;
import com.fnfh.datebase.DetailBaseHelper;
import com.fnfh.fragment.HomePageFragment;
import com.fnfh.utils.OkHttp;
import com.fnfh.utils.RequestData;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Request;

import static android.R.attr.value;
import static com.fnfh.R.id.toolbar;

public class DetaiPageActivity extends AppCompatActivity {

private static  final  String ID="DetaiPageActivity_id";
private static  final  String DATE="DetaiPageActivity_date";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.backdrop)
    ImageView backdrop;
    @BindView(R.id.detail_tv)
    TextView detailTv;
    @BindView(R.id.detai_fltActBut)
    FloatingActionButton detaiFltActBut;
    private String id;
    private static final String TAG = "DetaiPageActivity";
    private static final String KEY = "om.fnfh.detailspages";
    private static boolean isSave=false;
    private DetailBaseHelper helper;
    private static Details detail;
    private String date;
    private boolean isColl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detai_page);
        ButterKnife.bind(this);



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }



        helper = new DetailBaseHelper(this);/*获取数据库对象*/
        Intent intent = getIntent();
        id=intent.getStringExtra(ID);
        date= intent.getStringExtra(DATE);
        
        
       /*状态赋予*/
        isSave = helper.isHaveId(id);
        if(isSave){
            detaiFltActBut.setImageResource(R.mipmap.ic_toolbar_like_p);
        }else{
            detaiFltActBut.setImageResource(R.mipmap.ic_toolbar_like_n);
        }







        initView();

        initDate();
        /*设置 CollapsingToolbarLayout 的变化 影响 toolbar*/

    }

    private void initDate() {



        Log.e(TAG, "initDate: " + id);
        Log.e(TAG, "initDate: " + RequestData.getDetaiUrl(id));
        OkHttp.getAsync(RequestData.getDetaiUrl(id), new OkHttp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                Toast.makeText(DetaiPageActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void requestSuccess(String result) throws Exception {
                detail=new Details();
                Log.e(TAG, "requestSuccess: " + result);
                DetaiBean detaiBean = new Gson().fromJson(result, DetaiBean.class);
                List<DetaiBean.ResultBean> list = detaiBean.getResult();
                Log.e(TAG, "list.get(0).getTitle(): " + list.get(0).getTitle());
                collapsingToolbarLayout.setTitle(list.get(0).getTitle());
                DetaiPageActivity.this.setSupportActionBar(toolbar);
                detailTv.setText(list.get(0).getContent());
                if (list.get(0).getPicUrl() != null && list.get(0).getPicUrl().size() > 0) {
                    List<DetaiBean.ResultBean.PicUrlBean> picUrl = list.get(0).getPicUrl();

                    detail.setPicUrl(picUrl.get(0).getUrl());
                    Glide.with(DetaiPageActivity.this).load(picUrl.get(0).getUrl()).into(backdrop);
                }
                detail.setDate(date);
                detail.setD_id(list.get(0).getE_id());
                detail.setTitle(list.get(0).getTitle());
                detail.setContext(list.get(0).getContent());

            }
        });
    }

    private void initView() {

        toolbar.setNavigationIcon(R.mipmap.back);
        setSupportActionBar(toolbar);
        /*设置条目点击事件*/
        detaiFltActBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isSave=!isSave;
                if(isSave){
                    detaiFltActBut.setImageResource(R.mipmap.ic_toolbar_like_p);
                    helper.insertDates(detail);

                }else{
                    detaiFltActBut.setImageResource(R.mipmap.ic_toolbar_like_n);
                    helper.deltDates(detail.getD_id());
                }

            }
        });

    }

    /*设置 toolbar 左侧 图标的点击事件*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                Toast.makeText(DetaiPageActivity.this, "onOptionsItemSelected", Toast.LENGTH_SHORT).show();

//               startActivity(new Intent(this, MainActivity.class));
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public static Intent getInstanceIntent(Context context, String id,String date){
        Intent intent=new Intent(context,DetaiPageActivity.class);
        intent.putExtra(ID,id);
        intent.putExtra(DATE,date);
        return intent;
    }
}

