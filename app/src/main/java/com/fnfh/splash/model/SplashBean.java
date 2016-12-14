package com.fnfh.splash.model;

import android.os.CountDownTimer;

import com.fnfh.splash.bean.ICallBack;
import com.fnfh.splash.view.ISplashView;

/**
 * 1. 作用
 * 2. 作者 侯永亮
 * 3. 时间 2016/12/14.
 */

public class SplashBean extends CountDownTimer implements ISplashBean{
    public SplashBean(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }

    @Override
    public void onTick(long l) {

    }

    @Override
    public void onFinish() {

    }

    @Override
    public void timeDown(int time, ICallBack mIcallBack) {

    }
}
