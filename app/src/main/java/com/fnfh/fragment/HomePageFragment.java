package com.fnfh.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.fnfh.Bean.HistoruData;
import com.fnfh.R;
import com.fnfh.adpter.MyAdapter;
import com.fnfh.base.BaseFragment;
import com.fnfh.utils.OkHttp;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Request;

import static com.fnfh.R.id.toolbar;

/**
 * 1. 作用
 * 2. 作者 侯永亮
 * 3. 时间 2016/12/16.
 */

public class HomePageFragment extends BaseFragment {
    private static final String KEY="69a7eeba7869f8bdcdee7b2bc3bb5aa2";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.reyv_home)
    RecyclerView reyvHome;
    private List<HistoruData.ResultBean> list;
    private MyAdapter myAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        reyvHome.setLayoutManager(new LinearLayoutManager(getActivity()));


        initData();
    }

    private void initData() {


       queryData(0,0);

    }

    private int  getMonth() {
        Time t=new Time(); // or Time t=new Time("GMT+8"); 加上Time Zone资料。
        t.setToNow(); // 取得系统时间。
       return  t.month;

    }

    private int  getDate() {
        Time t=new Time(); // or Time t=new Time("GMT+8"); 加上Time Zone资料。
        t.setToNow(); // 取得系统时间。
        return  t.monthDay;
    }

    private void queryData(int month,int data) {
        if(month==0||month==0){
            month=getMonth();
        }
        if(data==0||data==0){
            data=getMonth();
        }
        String url="http://v.juhe.cn/todayOnhistory/queryEvent.php?key="+KEY+"&date="+month+"/"+data;

        OkHttp.getAsync(url, new OkHttp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                Toast.makeText(getActivity(), "请求数据失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void requestSuccess(String result) throws Exception {
                HistoruData historuData = new Gson().fromJson(result, HistoruData.class);
                list = historuData.getResult();
                if(list!=null){

                    if(myAdapter==null){
                        myAdapter = new MyAdapter(getActivity(), list);
                        reyvHome.setAdapter(myAdapter);
                    }else{
                        myAdapter.notifyDataSetChanged();
                    }

                }

            }
        });

    }




}
