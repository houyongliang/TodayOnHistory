package com.fnfh.splash.presenter;

import com.fnfh.splash.bean.TimeBean;

/**
 * 1. 作用
 * 2. 作者 侯永亮
 * 3. 时间 2016/12/14.
 */

public interface ISplashPresenter {
   void  modelFinsh();
   void  modelStart(TimeBean mTimeBean);
   TimeBean getTime();
   void setTime(TimeBean timeBean);


}
