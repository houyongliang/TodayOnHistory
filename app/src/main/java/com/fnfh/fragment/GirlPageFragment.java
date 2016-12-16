package com.fnfh.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fnfh.R;
import com.fnfh.base.BaseFragment;

/**
 * 1. 作用
 * 2. 作者 侯永亮
 * 3. 时间 2016/12/16.
 */

public class GirlPageFragment extends BaseFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_girl, container, false);
        return view;
    }
}
