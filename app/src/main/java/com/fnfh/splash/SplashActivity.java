package com.fnfh.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fnfh.homepage.MainActivity;
import com.fnfh.R;
import com.fnfh.splash.bean.TimeBean;
import com.fnfh.splash.presenter.SplashPresenter;
import com.fnfh.splash.view.ISplashView;

import butterknife.BindView;
import butterknife.ButterKnife;

//import butterknife.BindView;
//import butterknife.ButterKnife;


public class SplashActivity extends AppCompatActivity implements ISplashView {
    @BindView(R.id.img_bg_splash)
    ImageView imgBgSplash;
    @BindView(R.id.tv_time_down_splash)
    TextView tvTimeDownSplash;
    private SplashPresenter splashPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);



        //回调接口
        splashPresenter = new SplashPresenter( this);

        if (splashPresenter != null) {
            splashPresenter.modelStart(getTime());
            tvTimeDownSplash.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    splashPresenter.modelFinsh();
                }
            });
        }

    }


    @Override
    public void timeDown(int time) {
        tvTimeDownSplash.setText((time) + " S");

    }

    @Override
    public void toMainActivity() {
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public TimeBean getTime() {
        if(splashPresenter==null){
           return null;
        }
        return splashPresenter.getTime();
    }

    @Override
    public void setTime(TimeBean timeBean) {
        if(splashPresenter==null){
            return;
        }
         splashPresenter.setTime(timeBean);
    }


}
