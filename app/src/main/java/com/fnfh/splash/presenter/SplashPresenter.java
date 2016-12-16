package com.fnfh.splash.presenter;

import android.os.CountDownTimer;


import com.fnfh.splash.bean.TimeBean;
import com.fnfh.splash.view.ISplashView;


/**
 * 1. 作用
 * 2. 作者 侯永亮
 * 3. 时间 2016/12/14.
 */

public class SplashPresenter implements ISplashPresenter {
    ISplashView mISplashView;
    CountDownTimer mCountDownTimer;
    /*默认初始值*/
    private int time1=3000;
    private int durtime=1000;

    public SplashPresenter(ISplashView mISplashView) {
        this.mISplashView = mISplashView;
    }

    @Override
    public void modelFinsh() {
        mISplashView.toMainActivity();
    }

    @Override
    public void modelStart(TimeBean mTimeBean) {
      if (mTimeBean!=null){
            time1=mTimeBean.time;
            durtime=mTimeBean.durtime;
        }

        if (mCountDownTimer == null) {
            mCountDownTimer = new CountDownTimer(time1, durtime) {
                @Override
                public void onTick(long l) {
                    mISplashView.timeDown((int) (l / 1000));
                }

                @Override
                public void onFinish() {
                    mISplashView.toMainActivity();
                }
            };
        }
        mCountDownTimer.start();
    }

    @Override
    public TimeBean getTime() {
        TimeBean timeBean=new TimeBean();
        timeBean.time=time1;
        timeBean.durtime=durtime;
        return timeBean;
    }

    @Override
    public void setTime(TimeBean timeBean) {
        time1= timeBean.time;
        durtime= timeBean.durtime;
    }




}
