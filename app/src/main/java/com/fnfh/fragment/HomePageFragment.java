package com.fnfh.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.fnfh.Bean.HistoruData;
import com.fnfh.CalendarActivity;
import com.fnfh.R;
import com.fnfh.adpter.MyAdapter;
import com.fnfh.base.BaseFragment;
import com.fnfh.detailspages.DetaiPageActivity;
import com.fnfh.utils.DateUtil;
import com.fnfh.utils.OkHttp;
import com.fnfh.utils.RecyclerViewClickListener;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Request;

import static com.fnfh.utils.DateUtil.getMonthDate;

/**
 * 1. 作用
 * 2. 作者 侯永亮
 * 3. 时间 2016/12/16.
 */

public class HomePageFragment extends BaseFragment implements View.OnClickListener {
    private static final String KEY = "69a7eeba7869f8bdcdee7b2bc3bb5aa2";
    public static final String TAG = "HomePageFragment";
    public static final String APPID = "APPID";
    public static final String DATE = "DATE";
    @BindView(R.id.reyv_home)
    RecyclerView reyvHome;
    @BindView(R.id.pro)
    TextView pro;
    @BindView(R.id.setTitle)
    TextView setTitle;
    @BindView(R.id.next)
    TextView next;
    @BindView(R.id.home_fltingActButton)
    FloatingActionButton homeFltingActButton;
    @BindView(R.id.home_swipRL)
    SwipeRefreshLayout homeSwipRL;
//    @BindView(R.id.home_tv)
//    TextView homeTv;
    private List<HistoruData.ResultBean> list;
    private MyAdapter myAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        pro.setOnClickListener(this);
        setTitle.setOnClickListener(this);
        next.setOnClickListener(this);
//        homeFltingActButton.setOnClickListener(this);
        homeFltingActButton.setOnClickListener(this);

        /* recyclerview 点击事件*/
        reyvHome.addOnItemTouchListener(new RecyclerViewClickListener(getActivity(), reyvHome,
                new RecyclerViewClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        if (list != null) {
                            Toast.makeText(getActivity(), "Click ", Toast.LENGTH_SHORT).show();
                            Intent intent = DetaiPageActivity.getInstanceIntent(getActivity(), list.get(position).getE_id(), list.get(position).getDate());
//                            Intent intent = new Intent(getActivity(), DetaiPageActivity.class);
//                            intent.putExtra(APPID, list.get(position).getE_id());
//                            intent.putExtra(DATE, list.get(position).getDate());
                            startActivity(intent);
                        } else {
                            Toast.makeText(getActivity(), "没有请求数据 ", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onItemLongClick(View view, int position) {
                        Toast.makeText(getActivity(), "Long Click ", Toast.LENGTH_SHORT).show();
                    }
                }));

        homeSwipRL.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        homeSwipRL.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                homeTv.setText("正在刷新");
                new Handler().postDelayed(new Runnable() {
                                      @Override
                                        public void run() {
                                               // TODO Auto-generated method stub
//                                          homeTv.setText("刷新完成");
                                          homeSwipRL.setRefreshing(false);
                                           }
                                  }, 2000);
                           }
                    });

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        reyvHome.setLayoutManager(new LinearLayoutManager(getActivity()));
        initData();


    }

    @Override
    public void onResume() {
        super.onResume();
        if (CalendarActivity.isSend) {
            Intent intent = getActivity().getIntent();
            String showDate = intent.getStringExtra("showDate");

            setTitle.setText(showDate);
            int mth = Integer.parseInt(DateUtil.getMonth(showDate));
            int dte = Integer.parseInt(DateUtil.getMonthDate(showDate));
            DateUtil.num = 0;/*数据重置*/
            queryData(mth, dte);


        }


    }

    private void initData() {
        initNowDate();

    }

    private void initNowDate() {
        String nowDateStr = DateUtil.getNowDateStr();
        setTitle.setText(nowDateStr);
        queryData(-1, -1);
        DateUtil.num = 0;/*数据重置*/
    }


    private int getMonth() {
        return Calendar.getInstance().get(Calendar.MONTH);
//        Time t=new Time("GMT+8"); // or Time t=new Time("GMT+8"); 加上Time Zone资料。
//        t.setToNow(); // 取得系统时间。
//       return  t.month;

    }

    private int getDate() {
//        Time t=new Time("GMT+8"); // or Time t=new Time("GMT+8"); 加上Time Zone资料。
//        t.setToNow(); // 取得系统时间。
//        return  t.monthDay;
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }

    private void queryData(int month, int data) {
        if (month == -1 || data == -1) {
            month = getMonth() + 1;
        }
        if (data == -1 || data == -1) {
            data = getDate();
        }
        String url = "http://v.juhe.cn/todayOnhistory/queryEvent.php?key=" + KEY + "&date=" + month + "/" + data;

        OkHttp.getAsync(url, new OkHttp.DataCallBack() {


            @Override
            public void requestFailure(Request request, IOException e) {
                Toast.makeText(getActivity(), "请求数据失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void requestSuccess(String result) throws Exception {
                Log.e(TAG, result);
                HistoruData historuData = new Gson().fromJson(result, HistoruData.class);
                list = historuData.getResult();

                if (list != null) {
                    Collections.reverse(list);//反转list
                    myAdapter = new MyAdapter(getActivity(), list);
                    reyvHome.setAdapter(myAdapter);
//                    if (myAdapter == null) {
//                        myAdapter = new MyAdapter(getActivity(), list);
//                        reyvHome.setAdapter(myAdapter);
//                    } else {
//                        myAdapter.notifyDataSetChanged();
//                    }

                }

            }
        });

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.setTitle:
                initNowDate();
                break;
            case R.id.pro:
                DateUtil.num = -1;
                setDate();
                break;
            case R.id.next:
                DateUtil.num = 1;
                setDate();
                break;
            case R.id.home_fltingActButton:
                Log.e(TAG, "onClick: " + "11111");
                Toast.makeText(this.getActivity(), "lll", Toast.LENGTH_SHORT).show();
                Toast.makeText(this.getActivity(), "getActivity()" + getActivity().toString(), Toast.LENGTH_SHORT).show();
                getActivity().startActivity(new Intent(getActivity(), CalendarActivity.class));

                break;
        }

    }

    private void setDate() {

        String dateStr = "";

        if (TextUtils.isEmpty(setTitle.getText())) {
            dateStr = DateUtil.getDateStr(DateUtil.getNowDateStr(), DateUtil.num);
        } else {
            dateStr = DateUtil.getDateStr(setTitle.getText().toString(), DateUtil.num);
        }

        setTitle.setText(dateStr);
        int month = Integer.parseInt(DateUtil.getMonth(dateStr));
        int date = Integer.parseInt(getMonthDate(dateStr));
        queryData(month, date);

    }


}
