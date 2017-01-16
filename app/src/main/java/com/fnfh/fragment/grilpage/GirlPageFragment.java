package com.fnfh.fragment.grilpage;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.fnfh.Bean.GrilData;
import com.fnfh.R;
import com.fnfh.adpter.MyGrilAdapter;
import com.fnfh.base.BaseFragment;
import com.fnfh.utils.DividerGridItemDecoration;
import com.fnfh.utils.RecyclerViewClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.R.id.list;


/**
 * 1. 作用
 * 2. 作者 侯永亮
 * 3. 时间 2016/12/16.
 */

public class GirlPageFragment extends BaseFragment implements GrilPageFragmentView {
    List<GrilData.ResultsBean> results;

    private Boolean isLoading = false;
    private Boolean isReflash= false;
    @BindView(R.id.gril_fg_sfl)
    SwipeRefreshLayout grilFgSfl;
    //    @BindView(R.id.girl_fg_tv_empty)
//    TextView girlFgTvEmpty;
    @BindView(R.id.girl_fltingActBut)
    FloatingActionButton girlFltingActBut;
    private MyGrilAdapter myGrilAdapter;
    @BindView(R.id.rycv_gril)
    RecyclerView rycvGril;
    private GrilPagePresenter mGrilPagePresenter;
    private GridLayoutManager gridLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_girl, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return results.get(position) == null ? 2 : 1;
            }
        });
        rycvGril.setLayoutManager(gridLayoutManager);
        rycvGril.setItemAnimator(new DefaultItemAnimator());
        rycvGril.addItemDecoration(new DividerGridItemDecoration(getActivity()));
        mGrilPagePresenter = new GrilPagePresenter(this);
        mGrilPagePresenter.getData(getActivity(), true);
        /*设置点击事件*/
        rycvGril.addOnItemTouchListener(new RecyclerViewClickListener(getActivity(), rycvGril,
                new RecyclerViewClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        if (myGrilAdapter != null) {
                            mGrilPagePresenter.sendData(getActivity(), view, myGrilAdapter.getBaseDate(), position);
                        }


                    }

                    @Override
                    public void onItemLongClick(View view, int position) {
                        Toast.makeText(getActivity(), "Long Click ", Toast.LENGTH_SHORT).show();
                    }
                }));
    /*recyclerview　设置滑动监听*/
        rycvGril.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!rycvGril.canScrollVertically(1)) {/*判断是否加载到底部*/
                    if (myGrilAdapter != null && !isLoading) {
                        isLoading = true;
                        results.add(null);
                        myGrilAdapter.setDate(results);
                        mGrilPagePresenter.getData(getActivity(), false);
                        Toast.makeText(getActivity(), "滑到底部了", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        /*下拉刷新*/
        grilFgSfl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                grilFgSfl.setRefreshing(false);
                isReflash=true;
//                grilFgSfl.setRefreshing(false);
                //模拟下拉刷新数据操作
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mGrilPagePresenter.getData(getActivity(), true);
                        grilFgSfl.setRefreshing(false);
                    }
                }, 2000);
            }
        });

    /*设置 置顶 floatAB*/
        girlFltingActBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(myGrilAdapter!=null){
                    gridLayoutManager.scrollToPosition(0);
                }

            }
        });


    }

    @Override
    public void showView(List<GrilData.ResultsBean> list) {
        if(isReflash){
            if (myGrilAdapter != null) {
               results=list;
                myGrilAdapter.setDate(results);
            }else{
                myGrilAdapter = new MyGrilAdapter(getActivity(), results);
                rycvGril.setAdapter(myGrilAdapter);
            }
            isReflash=false;
            return;
        }


        if (myGrilAdapter != null) {
            for (int i = 0; i < results.size(); i++) {
                if (results.get(i) == null) {
                    results.remove(i);
                }
            }
            results.addAll(list);
            myGrilAdapter.setDate(results);
        } else {
            results = list;
            myGrilAdapter = new MyGrilAdapter(getActivity(), results);
            rycvGril.setAdapter(myGrilAdapter);
        }
        isLoading = false;
    }


}
