package com.fnfh.fragment.grilpage;

import com.fnfh.Bean.GrilData;

import java.util.List;

/**
 * 1. 作用
 * 2. 作者 侯永亮
 * 3. 时间 2016/12/19.
 */

public interface GrilPageFragmentView {
    void showView(List<GrilData.ResultsBean> list);

}
