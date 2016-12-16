package com.fnfh.splash.view;

import com.fnfh.splash.bean.TimeBean;

/**
 * 1. 作用
 * 2. 作者 侯永亮
 * 3. 时间 2016/12/14.
 */

public interface ISplashView {
    void timeDown(int  time);
    void toMainActivity();
    TimeBean getTime();
    void setTime(TimeBean timeBean);

}
