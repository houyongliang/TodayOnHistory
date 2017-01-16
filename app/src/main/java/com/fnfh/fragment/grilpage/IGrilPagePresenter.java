package com.fnfh.fragment.grilpage;

import android.content.Context;
import android.view.View;

import com.fnfh.Bean.GrilData;

import java.util.List;

/**
 * 1. 作用
 * 2. 作者 侯永亮
 * 3. 时间 2016/12/19.
 */

public interface IGrilPagePresenter {
    void getData(Context context,Boolean isFirst);

    void sendData(Context context, View view, List<GrilData.ResultsBean> list, int position);

}
