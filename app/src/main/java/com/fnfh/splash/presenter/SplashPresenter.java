package com.fnfh.splash.presenter;

import android.os.CountDownTimer;


import com.fnfh.splash.view.ISplashView;


/**
 * 1. 作用
 * 2. 作者 侯永亮
 * 3. 时间 2016/12/14.
 */

public class SplashPresenter  extends CountDownTimer implements ISplashPresenter{
    ISplashView mISplashView;



    public SplashPresenter(long millisInFuture, long countDownInterval,ISplashView mISplashView) {
        super(millisInFuture, countDownInterval);
        this.mISplashView=mISplashView;
        this.start();//开启 倒计时
    }

    @Override
    public void modelFinsh() {
        mISplashView.toMainActivity();
    }



    @Override
    public void onTick(long l) {
        mISplashView.timeDown((int) (l/1000));

    }

    @Override
    public void onFinish() {
        mISplashView.toMainActivity();
    }
}
