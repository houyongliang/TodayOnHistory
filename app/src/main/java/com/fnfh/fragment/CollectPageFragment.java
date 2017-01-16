package com.fnfh.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.fnfh.Bean.Details;
import com.fnfh.R;
import com.fnfh.adpter.MyCollAdapter;
import com.fnfh.base.BaseFragment;
import com.fnfh.datebase.DetailBaseHelper;
import com.fnfh.detailspages.DetaiPageActivity;
import com.fnfh.helper.OnStartDragListener;
import com.fnfh.helper.SimpleItemTouchHelperCallback;
import com.fnfh.utils.RecyclerViewClickListener;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 1. 作用
 * 2. 作者 侯永亮
 * 3. 时间 2016/12/16.
 */

public class CollectPageFragment extends BaseFragment implements OnStartDragListener {
    @BindView(R.id.collect_reylv)
    RecyclerView collectReylv;
    private DetailBaseHelper helper;
    /*-------------------------------------------*/
    private ItemTouchHelper mItemTouchHelper;
    private List<Details> detailses;
    private MyCollAdapter adapter;

    /*-------------------------------------------*/
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_collect, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        helper = new DetailBaseHelper(getActivity());
        detailses = helper.queryDates();


          /*-------------------------------------------*/
        adapter = new MyCollAdapter(getActivity(), detailses,this);
          /*-------------------------------------------*/
        collectReylv.setLayoutManager(new LinearLayoutManager(getActivity()));
        //设置Item增加、移除动画
        collectReylv.setItemAnimator(new DefaultItemAnimator());
 /*-------------------------------------------*/
        collectReylv.setHasFixedSize(true);
         /*-------------------------------------------*/
        collectReylv.setAdapter(adapter);

        collectReylv.addOnItemTouchListener(new RecyclerViewClickListener(getActivity(), collectReylv, new RecyclerViewClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getActivity(), "点击了"+position, Toast.LENGTH_SHORT).show();
                Details dts = detailses.get(position);
                Intent intent = DetaiPageActivity.getInstanceIntent(getActivity(), dts.getD_id(), dts.getDate());
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));

  /*-------------------------------------------*/
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(collectReylv);

    }
    /*-------------------------------------------*/
    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
      /*-------------------------------------------*/


    @Override
    public void onResume() {
        super.onResume();
        detailses = helper.queryDates();
        adapter.setNewData(detailses);

    }
}
